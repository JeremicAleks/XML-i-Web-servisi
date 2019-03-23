package pki.domain;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/**
 * Klasa koja sluzi da predtavi certifikate u bazi
 * @author Kiriyaga
 *
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class Certificate implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private BigInteger serialNumber;
	
	@Column(nullable = false)
	private BigInteger ownerNumber;
	
	@Column
	private String name;
	
	@Column(nullable = false)
	private Boolean active;
		
	@Column
	private Boolean CA;
	
	public Certificate() {
		
	}

	public Certificate(BigInteger serialNumber, BigInteger ownerNumber, Boolean active, Boolean cA,String name) {
		super();
		this.serialNumber = serialNumber;
		this.ownerNumber = ownerNumber;
		this.active = active;
		this.CA = cA;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigInteger getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(BigInteger serialNumber) {
		this.serialNumber = serialNumber;
	}

	public BigInteger getOwnerNumber() {
		return ownerNumber;
	}

	public void setOwnerNumber(BigInteger ownerNumber) {
		this.ownerNumber = ownerNumber;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getCA() {
		return CA;
	}

	public void setCA(Boolean cA) {
		CA = cA;
	}
	
	
	
}
