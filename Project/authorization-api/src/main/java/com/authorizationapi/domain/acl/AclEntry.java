package com.authorizationapi.domain.acl;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class AclEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private AclObjectIdentity object;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private AclSid sid;
	@Enumerated
	private AclPrivilegeEnum privilege;

	public AclEntry() {
		// TODO Auto-generated constructor stub
	}

	public AclEntry(AclObjectIdentity object, AclSid sid, AclPrivilegeEnum privilege) {
		super();
		this.object = object;
		this.sid = sid;
		this.privilege = privilege;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AclObjectIdentity getObject() {
		return object;
	}

	public void setObject(AclObjectIdentity object) {
		this.object = object;
	}

	public AclSid getSid() {
		return sid;
	}

	public void setSid(AclSid sid) {
		this.sid = sid;
	}

	public AclPrivilegeEnum getPrivilege() {
		return privilege;
	}

	public void setPrivilege(AclPrivilegeEnum privilege) {
		this.privilege = privilege;
	}

}