package pki.domain;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class CertificateAndKey {
	
	private X509Certificate certificate;
    private PrivateKey privateKey;
    
    public CertificateAndKey() {
    	
    }
    
	public CertificateAndKey(X509Certificate certificate, PrivateKey privateKey) {
		super();
		this.certificate = certificate;
		this.privateKey = privateKey;
	}
	public X509Certificate getCertificate() {
		return certificate;
	}
	public void setCertificate(X509Certificate certificate) {
		this.certificate = certificate;
	}
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
    

}
