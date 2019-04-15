package pki.service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pki.domain.Certificate;
import pki.repository.ServerStorage;
import pki.utils.CustomException;

@Service
public class TrustAppService {

	@Autowired
	ServerStorage serverStorage;

	public void generateTrustStorage(Certificate trustStorage, String[] serialNumbers) {
		try {
			X509Certificate[] certificates = serverStorage.findCertificates(serialNumbers, false);
			Path root = Paths.get("src", "main", "resources");
			File f;
			if (trustStorage.getCA()) {
				f = new File(root + "\\CA\\" + trustStorage.getName() + "\\" + trustStorage.getSerialNumber().toString()
						+ "\\storages");
			} else {
				f = new File(root + "\\leafs\\"+ trustStorage.getSerialNumber().toString() + "\\storages");
			}

			KeyPair key = generateKeyPair();
			KeyStore keyStore = KeyStore.getInstance("jks");
			keyStore.load(null, null);

			keyStore.setKeyEntry("certs", key.getPrivate(), "MegaTravel".toCharArray(), certificates);
			keyStore.store(new FileOutputStream(f.getPath()+"\\trustApps.jks"), "MegaTravel".toCharArray());

		} catch (Exception e) {
			 throw new CustomException("Error occurred while updating trust storage!",HttpStatus.INTERNAL_SERVER_ERROR);
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

}
