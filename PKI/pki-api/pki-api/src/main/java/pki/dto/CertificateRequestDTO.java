package pki.dto;

import pki.domain.enums.CertificateType;

public class CertificateRequestDTO {
	
	SubjectDataDTO subject;
	int numberOfYears;
	CertificateType type;
	String issuer;
	String pathForStore;
	
	public CertificateRequestDTO() {
	}
	
	public SubjectDataDTO getSubject() {
		return subject;
	}
	public void SubjectDataDTO(SubjectDataDTO subject) {
		this.subject = subject;
	}
	public int getNumberOfYears() {
		return numberOfYears;
	}
	public void setNumberOfYears(int numberOfYears) {
		this.numberOfYears = numberOfYears;
	}
	public CertificateType getType() {
		return type;
	}
	public void setType(CertificateType type) {
		this.type = type;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getPathForStore() {
		return pathForStore;
	}
	public void setPathForStore(String pathForStore) {
		this.pathForStore = pathForStore;
	}
	
	

}
