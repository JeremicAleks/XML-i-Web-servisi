package pki.controller;

import java.util.List;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pki.domain.Certificate;
import pki.domain.enums.CertificateType;
import pki.dto.CertificateRequestDTO;
import pki.service.CertificateService;

@RestController
@RequestMapping("/api/cer")
public class CertificateController {
	
	@Autowired
	private CertificateService certificateService;
	
	
	@PostMapping(produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generateCertificate(@RequestBody CertificateRequestDTO request) {
		
		System.out.println(request.getSubject().getCn());
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
	    builder.addRDN(BCStyle.CN, request.getSubject().getCn());
	    builder.addRDN(BCStyle.SURNAME, request.getSubject().getSurname());
	    builder.addRDN(BCStyle.GIVENNAME, request.getSubject().getGivenname());
	    builder.addRDN(BCStyle.O, request.getSubject().getO());
	    builder.addRDN(BCStyle.OU, request.getSubject().getOu());
	    builder.addRDN(BCStyle.C, request.getSubject().getC());
	    builder.addRDN(BCStyle.E, request.getSubject().getE());
	    //UID (USER ID) je ID korisnika
	    builder.addRDN(BCStyle.UID, request.getSubject().getUid());
	    
		if(request.getType()==CertificateType.ROOT) {
			certificateService.generateRootCertificate(builder.build(), request.getNumberOfYears());		
		}
		else {
			certificateService.generateCertificate(builder.build(),request.getNumberOfYears(), request.getType(), request.getIssuer(), request.getPathForStore());
			
		}

        return new ResponseEntity<>("Certificate successfully created!", HttpStatus.OK);
    }
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Certificate>> findCertificates() {
		
        return new ResponseEntity<>(certificateService.findAllCertificates(), HttpStatus.OK);
    }
	
	
	@DeleteMapping(value = "/revoke/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> revokeCertificate(@PathVariable String id) {
		
		certificateService.revokeCertificate(Long.parseLong(id));
        return new ResponseEntity<>("Certificate successfully deleted!", HttpStatus.OK);
	}
	

}
