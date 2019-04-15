package pki.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pki.domain.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate,Long> {
	
    @Query(value = "SELECT * FROM certificate WHERE SERIAL_NUMBER = ?1", nativeQuery = true)
    Certificate findBySerialNumber(BigInteger serialNumber);

}
