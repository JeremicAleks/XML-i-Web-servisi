package com.authorizationapi.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authorizationapi.domain.User;
import com.authorizationapi.domain.acl.AclEntry;
import com.authorizationapi.domain.acl.AclPrivilegeEnum;
import com.authorizationapi.domain.acl.AclSid;
import com.authorizationapi.repo.AclEntryRepository;
import com.authorizationapi.repo.AclObjectRepository;
import com.authorizationapi.repo.AclSidRepository;
@Service
public class AclServiceCon implements AclService {
	
	@Autowired
	AclSidRepository aclSidRepo;
	
	@Autowired
	AclEntryRepository aclEntryRepo;
	
	@Autowired
	AclObjectRepository aclObjRepo;
	

	final static Path rootDir = Paths.get("src", "main", "resources", "root");

	@Override
	public List<Path> findFiles(User user) {
		List<Path> lista = new ArrayList<>();
		AclSid acls1 = aclSidRepo.findBySid(user.getUsername());
		AclSid acls2 = aclSidRepo.findBySid(user.getRole().getName());
		List<AclSid> acls = new ArrayList<>();
		if(acls1 != null) {
			acls.add(acls1);
		}
		if(acls2 != null) {
			acls.add(acls2);
		}
		System.out.println("acls size: " + acls.size());
		walk(rootDir.toString(),lista,acls);
		System.out.println(lista.size());
		return lista;
	}

	public void walk(String path,List<Path> lista,List<AclSid> acls) {

		File root = new File(path);
		File[] list = root.listFiles();

		if (list == null)
			return;

		for (File f : list) {
			if (f.isDirectory()) {
				walk(f.getAbsolutePath(),lista,acls);
				System.out.println("Dir:" + f.getAbsoluteFile());
				
			} else {
				System.out.println("File:" + f.getAbsoluteFile());
			}

			boolean succ= false;
			for(AclSid a: acls) {
				System.out.println("Usao u sid!");
				List<AclEntry> entry = aclEntryRepo.findEntryForSid(a.getId());
				
				for(AclEntry e:entry) {
					
					String s = e.getObject().getPath().substring(0, e.getObject().getPath().length()-1);
					String ff = f.getAbsolutePath().replace("\\","/");
					System.out.println(s);
					System.out.println(ff);
					System.out.println(e.getPrivilege());
					if(ff.equals(s) && e.getPrivilege()==AclPrivilegeEnum.READ) {
						succ=true;
						lista.add(f.getAbsoluteFile().toPath());
						break;
					}
				}
				if(succ==true) {
					break;
				}
			}	
		}
	}

}
