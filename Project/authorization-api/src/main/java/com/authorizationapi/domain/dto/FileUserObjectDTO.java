package com.authorizationapi.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.authorizationapi.domain.PrivilegeEnum;
import com.authorizationapi.domain.acl.AclPrivilegeEnum;

public class FileUserObjectDTO {

	private String sid;
	private List<AclPrivilegeEnum> privileges;

	public FileUserObjectDTO() {
		// TODO Auto-generated constructor stub
	}

	public FileUserObjectDTO(String sid) {
		super();
		this.sid = sid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public List<AclPrivilegeEnum> getPrivileges() {
		if (this.privileges == null) {
			this.privileges = new ArrayList<>();
		}
		return privileges;
	}

	public void setPrivileges(List<AclPrivilegeEnum> privileges) {
		this.privileges = privileges;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileUserObjectDTO other = (FileUserObjectDTO) obj;
		if (sid == null) {
			if (other.sid != null)
				return false;
		} else if (!sid.equals(other.sid))
			return false;
		return true;
	}
	
	

}
