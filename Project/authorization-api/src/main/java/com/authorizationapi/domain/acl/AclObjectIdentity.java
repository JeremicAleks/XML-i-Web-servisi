package com.authorizationapi.domain.acl;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AclObjectIdentity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String path;
	@Column(name = "IsFile")
	private boolean isFile;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private AclSid owner;
	
	public AclObjectIdentity() {
		// TODO Auto-generated constructor stub
	}
	

	public AclObjectIdentity(String path, boolean isFile, AclSid owner) {
		super();
		this.path = path;
		this.isFile = isFile;
		this.owner = owner;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isFile() {
		return isFile;
	}

	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}

	public AclSid getOwner() {
		return owner;
	}

	public void setOwner(AclSid owner) {
		this.owner = owner;
	}

}