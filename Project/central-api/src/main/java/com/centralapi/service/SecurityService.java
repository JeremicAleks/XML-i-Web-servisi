package com.centralapi.service;

public interface SecurityService {
	
	/**
	 * Metoda vraca da li korisnik ima zadati authority
	 * 
	 * @param authority
	 * @return true ili false u zavisnosti da li ima korisnik ima zadati authority
	 */
	public Boolean hasProtectedAccess(String role);


}
