package cloud.wing.flight.entity;

public class Airline {
	private int airlineId;
	private String name;
	private String iataCode;
	private String country;
	private String contactEmail;

	public Airline() {
		super();
	}

	public Airline(int airlineId, String name, String iataCode, String country, String contactEmail) {
		super();
		this.airlineId = airlineId;
		this.name = name;
		this.iataCode = iataCode;
		this.country = country;
		this.contactEmail = contactEmail;
	}

	public Airline(int airlineId) {
		super();
		this.airlineId = airlineId;
	}

	public int getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(int airlineId) {
		this.airlineId = airlineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIataCode() {
		return iataCode;
	}

	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

}
