package pki.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pki.domain.Certificate;
import pki.dto.TrustAppStorageRequestDTO;
import pki.repository.CertificateRepository;
import pki.service.TrustAppService;

@RestController
@RequestMapping("/api/trust")
public class TrustAppStorageController {
	
	@Autowired
	TrustAppService trustService;
	
	@Autowired
	CertificateRepository certRepository;
	
	@PostMapping(produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generateTrustAppStorage(@RequestBody TrustAppStorageRequestDTO request) {
	
		Certificate targetCert= certRepository.findBySerialNumber(new BigInteger(request.getTargerSerialNumber()));
		trustService.generateTrustStorage(targetCert, request.getSerialNumbers());		
        return new ResponseEntity<>("TrustAppStorage successfully updated!", HttpStatus.OK);
    }

}
