package pki.dto;

public class TrustAppStorageRequestDTO {
	
	private String targerSerialNumber;
	private String[] SerialNumbers;
	
	public TrustAppStorageRequestDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public TrustAppStorageRequestDTO(String targerSerialNumber, String[] serialNumbers) {
		super();
		this.targerSerialNumber = targerSerialNumber;
		SerialNumbers = serialNumbers;
	}

	public String getTargerSerialNumber() {
		return targerSerialNumber;
	}
	public void setTargerSerialNumber(String targerSerialNumber) {
		this.targerSerialNumber = targerSerialNumber;
	}
	public String[] getSerialNumbers() {
		return SerialNumbers;
	}
	public void setSerialNumbers(String[] serialNumbers) {
		SerialNumbers = serialNumbers;
	}
	
	

}
