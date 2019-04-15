package pki.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.springframework.http.HttpStatus;

import pki.domain.CertificateAndKey;

public class CertificateUtils {

	 public static X509Certificate[] toChain(CertificateAndKey[] certificates) {
		 
	        X509Certificate[] certificateChain = new X509Certificate[certificates.length];
	        for (int i = 0; i < certificates.length; i++) {
	        	certificateChain[i] = certificates[i].getCertificate();
	        }
	        return certificateChain;
	    }
	 
	    public static CertificateAndKey[] toChain(Certificate[] certificates,PrivateKey privateKey) {
	    	
	        CertificateAndKey[] certificateChain = new CertificateAndKey[certificates.length];
	        for (int i = 0; i < certificates.length; i++) {
	            if (i==0) {
	            	certificateChain[i] = new CertificateAndKey((X509Certificate) certificates[i], privateKey);
	            } else {
	            	certificateChain[i] = new CertificateAndKey((X509Certificate) certificates[i], null);
	            }
	        }
	        return certificateChain;
	    }
	    
	    private X509Certificate readCertificate(String certificateName){
	        FileInputStream fis;
			try {
				fis = new FileInputStream(certificateName);
		        BufferedInputStream bis = new BufferedInputStream(fis);

		        CertificateFactory cf = CertificateFactory.getInstance("X.509");
		        Certificate cert = cf.generateCertificate(bis);
		        return (X509Certificate) cert;
			} catch (Exception e) {
				throw new CustomException("Error occurred while reading certificate!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
	    }
}
