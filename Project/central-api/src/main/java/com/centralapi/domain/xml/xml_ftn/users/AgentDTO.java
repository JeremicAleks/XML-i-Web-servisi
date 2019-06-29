package com.centralapi.domain.xml.xml_ftn.users;

public class AgentDTO {
	
	private String userToPromote;
	private String address;
	private String businessRegNumber;
	
	public AgentDTO() {}

	public String getUserToPromote() {
		return userToPromote;
	}

	public void setUserToPromote(String userToPromote) {
		this.userToPromote = userToPromote;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBusinessRegNumber() {
		return businessRegNumber;
	}

	public void setBusinessRegNumber(String businessRegNumber) {
		this.businessRegNumber = businessRegNumber;
	}

}
