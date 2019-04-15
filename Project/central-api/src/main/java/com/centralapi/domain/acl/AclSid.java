package com.centralapi.domain.acl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "AclSid")
public class AclSid {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
    @Column(name="Sid")
	private Long sid;
	// 0 - username 1 - Role
    @Column(name="Principal")
	private boolean principal;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public boolean isPrincipal() {
		return principal;
	}
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	
}
