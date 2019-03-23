package pki.dto;

import java.util.Random;

public class SubjectDataDTO {

	String cn;
	String surname;
	String givenname;
	String o;
	String ou;
	String c;
	String e;
	String uid;
	
	
	public SubjectDataDTO() {
		
	}


	public String getCn() {
		return cn;
	}


	public void setCn(String cn) {
		this.cn = cn;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getGivenname() {
		return givenname;
	}


	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}


	public String getO() {
		return o;
	}


	public void setO(String o) {
		this.o = o;
	}


	public String getOu() {
		return ou;
	}


	public void setOu(String ou) {
		this.ou = ou;
	}


	public String getC() {
		return c;
	}


	public void setC(String c) {
		this.c = c;
	}


	public String getE() {
		return e;
	}


	public void setE(String e) {
		this.e = e;
	}


	public String getUid() {
		return "123456";
	}


	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
}