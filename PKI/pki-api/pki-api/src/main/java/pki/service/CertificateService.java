package pki.service;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pki.domain.Certificate;
import pki.domain.CertificateAndKey;
import pki.domain.enums.CertificateType;
import pki.repository.CertificateRepository;
import pki.repository.ServerStorage;
import pki.utils.CustomException;

@Service
public class CertificateService {

	@Autowired
	private CertificateRepository certificateRepository;

	@Autowired
	private ServerStorage serverStorage;

	public void generateRootCertificate(X500Name principal, int numberOfYears) {

		KeyPair key = generateKeyPair();
		try {

			JcaContentSignerBuilder builder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");
			builder = builder.setProvider("BC");
			ContentSigner contentSigner = builder.build(key.getPrivate());
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			Date startDate = c.getTime();
			c.add(Calendar.YEAR, numberOfYears);
			Date endDate = c.getTime();
			X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(principal, generateSerialNumber(),
					startDate, endDate, principal, key.getPublic());

			X509CertificateHolder certHolder = certGen.build(contentSigner);
			JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
			certConverter = certConverter.setProvider("BC");
			CertificateAndKey certAndKey = new CertificateAndKey(certConverter.getCertificate(certHolder),
					key.getPrivate());
			certificateRepository.save(
					new Certificate(certHolder.getSerialNumber(), certHolder.getSerialNumber(), true, true, "ROOT"));
			// Treba jos napraviti ovaj sertifikat i smestiti ga gde treba D:
			serverStorage.store("privateKeys", "MegaTravel", "keyPass", new CertificateAndKey[] { certAndKey },
					CertificateType.ROOT, "ROOT");
		} catch (Exception e) {
			throw new CustomException("Error occurred while generating certificate!", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public void generateCertificate(X500Name subject, int numberOfYears, CertificateType type, String issuer,
			String pathForStore) {

		CertificateAndKey[] certificateChain = serverStorage.upload("privateKeys", "MegaTravel", "keyPass",
				CertificateType.ROOT, issuer);
		// bitan nam je prvi element
		X509Certificate cert = certificateChain[0].getCertificate();
		PrivateKey privateKey = certificateChain[0].getPrivateKey();

		KeyPair key = generateKeyPair();
		try {
			JcaContentSignerBuilder builder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");
			builder = builder.setProvider("BC");
			ContentSigner contentSigner = builder.build(privateKey);
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			Date startDate = c.getTime();
			c.add(Calendar.YEAR, numberOfYears);
			Date endDate = c.getTime();
			X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(
					new X500Name(cert.getSubjectX500Principal().toString()), generateSerialNumber(), startDate, endDate,
					subject, key.getPublic());

			X509CertificateHolder certHolder = certGen.build(contentSigner);
			JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
			certConverter = certConverter.setProvider("BC");
			CertificateAndKey certAndKey = new CertificateAndKey(certConverter.getCertificate(certHolder),
					key.getPrivate());
			// expend chain and save
			certificateChain[0].setPrivateKey(null);
			CertificateAndKey[] expendedChain = new CertificateAndKey[certificateChain.length + 1];
			expendedChain[0] = certAndKey;
			System.arraycopy(certificateChain, 0, expendedChain, 1, certificateChain.length);

			if (type == CertificateType.INTERMEDIATE)
				certificateRepository.save(new Certificate(certHolder.getSerialNumber(), cert.getSerialNumber(), true,
						true, pathForStore));
			else
				certificateRepository
						.save(new Certificate(certHolder.getSerialNumber(), cert.getSerialNumber(), true, false, null));
			serverStorage.store("privateKeys", "MegaTravel", "keyPass", expendedChain, type, pathForStore);
		} catch (Exception e) {
			throw new CustomException("Error occurred while generating certificate!", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private KeyPair generateKeyPair() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.initialize(2048, random);
			return keyGen.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
		return null;
	}

	private BigInteger generateSerialNumber() {
		BigInteger bigInteger = new BigInteger("2000000000000");
		BigInteger min = new BigInteger("1000000000");
		BigInteger bigInteger1 = bigInteger.subtract(min);
		Random rnd = new Random();
		int maxNumBitLength = bigInteger.bitLength();

		BigInteger aRandomBigInt;

		aRandomBigInt = new BigInteger(maxNumBitLength, rnd);
		if (aRandomBigInt.compareTo(min) < 0)
			aRandomBigInt = aRandomBigInt.add(min);
		if (aRandomBigInt.compareTo(bigInteger) >= 0)
			aRandomBigInt = aRandomBigInt.mod(bigInteger1).add(min);

		return aRandomBigInt;
	}

	public ArrayList<Certificate> findAllCertificates() {

		return (ArrayList<Certificate>) certificateRepository.findAll();

	}

	public void revokeCertificate(Long id) {

		Optional<Certificate> opt = certificateRepository.findById(id);
		Certificate c = opt.get();
		c.setActive(false);
		certificateRepository.save(c);

	}

}
