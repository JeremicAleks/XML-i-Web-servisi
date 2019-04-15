package pki.repository;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import pki.domain.CertificateAndKey;
import pki.domain.enums.CertificateType;
import pki.utils.CertificateUtils;
import pki.utils.CustomException;

@Repository
public class ServerStorage {

	@Autowired
	CertificateRepository certificateRepository;

	public void store(String alias, String password, String cpassword, CertificateAndKey[] certs, CertificateType type,
			String pathForStore) {

		Path certificateStorage, storage;
		CertificateAndKey leaf = certs[0];
		if (type == CertificateType.LEAF) {
			certificateStorage = Paths.get("src", "main", "resources", "leafs",
					leaf.getCertificate().getSerialNumber().toString());
			storage = Paths.get("src", "main", "resources", "leafs", leaf.getCertificate().getSerialNumber().toString(),
					"storages");
		} else {
			certificateStorage = Paths.get("src", "main", "resources", "CA", pathForStore,
					leaf.getCertificate().getSerialNumber().toString());
			storage = Paths.get("src", "main", "resources", "CA", pathForStore, "storages");
		}

		try {
			Files.createDirectories(storage);
			Files.createDirectories(certificateStorage);
			// File f = new
			// File((Paths.get(certificateStorage.toString()).toString()+"\\"+leaf.getCertificate().getSerialNumber()+".cer"));
			FileOutputStream out = new FileOutputStream(Paths.get(certificateStorage.toString(),
					"certificate_" + leaf.getCertificate().getSerialNumber() + ".cer").toString());
			// FileOutputStream out = new FileOutputStream(f);
			out.write(leaf.getCertificate().getEncoded());
			out.close();
			// Store certificate chain
			KeyStore keyStore = KeyStore.getInstance("jks");
			keyStore.load(null, null);

			keyStore.setKeyEntry(alias, leaf.getPrivateKey(), cpassword.toCharArray(), CertificateUtils.toChain(certs));
			keyStore.store(new FileOutputStream(Paths.get(storage.toString(), "storage.jks").toString()),
					password.toCharArray());
		} catch (Exception e) {
			throw new CustomException("Error occurred while storing certificate!", HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	public CertificateAndKey[] upload(String alias, String password, String cpassword, CertificateType type,
			String pathForStore) {
		try {

			KeyStore keyStore = KeyStore.getInstance("jks");
			Path storage;
			if (type == CertificateType.LEAF) {
				storage = Paths.get("src", "main", "resources", "leafs", pathForStore, "storages", "storage.jks");
			} else {
				storage = Paths.get("src", "main", "resources", "CA", pathForStore, "storages", "storage.jks");
			}
			keyStore.load(new FileInputStream(storage.toString()), password.toCharArray());
			PrivateKey key = (PrivateKey) keyStore.getKey(alias, cpassword.toCharArray());
			Certificate[] certs = keyStore.getCertificateChain(alias);
			return CertificateUtils.toChain(certs, key);
		} catch (Exception e) {
			throw new CustomException("Error occurred while reading certificate!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public X509Certificate[] findCertificates(String[] serialNumber, Boolean all){

		ArrayList<pki.domain.Certificate> certs;
		List<X509Certificate> result = new ArrayList<>();
		if (all == true) {
			certs = (ArrayList<pki.domain.Certificate>) certificateRepository.findAll();
		} else {
			certs = new ArrayList<>();
			for (int i = 0; i < serialNumber.length; i++) {
				pki.domain.Certificate c = certificateRepository.findBySerialNumber(new BigInteger(serialNumber[i]));
				if (c != null) {
					certs.add(c);
				}
			}
		}

		for (int i = 0; i < certs.size(); i++) {
			Path root = Paths.get("src", "main", "resources");
			File f;
			if (certs.get(i).getCA()) {
				f = new File(
						root + "\\CA\\" + certs.get(i).getName() + "\\" + certs.get(i).getSerialNumber().toString());
			} else {
				f = new File(
						root + "\\leafs\\" + certs.get(i).getName() + "\\" + certs.get(i).getSerialNumber().toString());
			}

			try {
				result.add(readCertificate(f.getPath() + "\\" + "certificate_" + certs.get(i).getSerialNumber() + ".cer"));
			} catch (FileNotFoundException | CertificateException e) {
				
				throw new CustomException("Error occurred while reading certificate!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return result.toArray(new X509Certificate[0]);

	}

	private X509Certificate readCertificate(String name) throws FileNotFoundException, CertificateException {
		FileInputStream fis = new FileInputStream(name);
		BufferedInputStream bis = new BufferedInputStream(fis);

		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		Certificate cert = cf.generateCertificate(bis);
		return (X509Certificate) cert;
	}

}
