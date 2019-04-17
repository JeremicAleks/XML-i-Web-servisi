package com.authorizationapi.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class FileDTO {

	private String path;
	private List<FileUserObjectDTO> object;

	public FileDTO(String path) {
		this.path = path;
	}

	public FileDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<FileUserObjectDTO> getObject() {
		if(this.object == null) {
			this.object = new ArrayList<>();
		}
		return object;
	}


	public boolean equals(FileUserObjectDTO obj) {
		
		if(obj == null) {
			return false;
		}
		if(object == null) {
			return false;
		}
		for (FileUserObjectDTO fileUserObjectDTO : object) {
			
			if(obj.equals(fileUserObjectDTO)) {
				return true;
			}
			
		}
		return false;

	}

	public void setObject(List<FileUserObjectDTO> object) {
		this.object = object;
	}

}
