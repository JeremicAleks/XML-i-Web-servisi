package pki.service;

import java.security.cert.X509Certificate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pki.repository.CertificateRepository;

@Service
public class VerificationService {
	
    @Autowired
    private CertificateRepository certificateRepository;

    private boolean checkDate(X509Certificate cert) {
        Date startDate = cert.getNotBefore();
        Date endDate = cert.getNotAfter();
        Date today = new Date();

        return today.after(startDate) && today.before(endDate);
    }

    private boolean verifyCertificate(X509Certificate cert){
        boolean isActive = certificateRepository.findBySerialNumber(cert.getSerialNumber()).getActive();

        boolean isValidDate = checkDate(cert);
        return isActive && isValidDate;
    }

    private boolean verifySignature(X509Certificate certificate, X509Certificate parentCertificate) {
        try {
            certificate.verify(parentCertificate.getPublicKey());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyCertificateChain(X509Certificate[] certificateList) {
 
        boolean valid = true;
        for (int i = 0; i < certificateList.length - 1; i++) {
            X509Certificate cert = certificateList[i];
            if (!verifyCertificate(cert) || !verifySignature(cert, certificateList[i + 1]) )

                valid = false;
        }
        return valid;
    }

}
