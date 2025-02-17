package cloud.wing.passenger.entity;

import java.sql.Date;

import cloud.wing.loyaltypoints.entity.LoyaltyPoints;


public class Passenger {

	private int passengerId;
	private String name;
	private String email;
	private String username;
	private String gender;
	private String dateOfBirth;
	private String mobileNumber;
	private String passportNumber;
	private Date passport_issue_date;
	
	private Date passport_expiry_date;
	private String password_hash;
	private String passport_salt;
	private String password;
	
	private LoyaltyPoints loyaltyPoints;


	public Passenger() {
		super();
	}
	
	
	
	

	public Passenger(int passengerId, String email, String username, String gender, String dateOfBirth,
			String mobileNumber) {
		super();
		this.passengerId = passengerId;
		this.email = email;
		this.username = username;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
	}




	public Passenger(String name, String email, String username, String gender, String dateOfBirth, String mobileNumber,
			String password) {
		super();
		this.name = name;
		this.email = email;
		this.username = username;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.password = password;
	}




	public Passenger( int passengerId, String name, String username, String gender, String dateOfBirth,
			String mobileNumber, String passportNumber) {
		super();
		this.passengerId = passengerId;
		this.name = name;
		this.username = username;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.passportNumber = passportNumber;
		
	}

	public Passenger(String name, String email, String username, String gender, String dateOfBirth,
			String mobileNumber,String passportNumber, Date passport_issue_date, Date passport_expiry_date,String password) {
		super();
		this.name = name;
		this.email = email;
		this.username = username;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.passportNumber = passportNumber;
		this.passport_issue_date = passport_issue_date;
		this.passport_expiry_date = passport_expiry_date;
		this.password = password;
		
	}

	

	public Passenger(int passengerId, String name, String email, String username, String gender, String dateOfBirth,
			String mobileNumber, String passportNumber, Date passport_issue_date, Date passport_expiry_date,
			 String password_hash, String passport_salt) {
		super();
		this.passengerId = passengerId;
		this.name = name;
		this.email = email;
		this.username = username;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.passportNumber = passportNumber;
		this.passport_issue_date = passport_issue_date;
		this.passport_expiry_date = passport_expiry_date;
		this.password_hash = password_hash;
		this.passport_salt = passport_salt;

	}

	
	
	

	public Passenger(int passengerId, String name, String email, String username, String gender, String dateOfBirth,
			String mobileNumber, String passportNumber, String password_hash, String passport_salt) {
		super();
		this.passengerId = passengerId;
		this.name = name;
		this.email = email;
		this.username = username;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.passportNumber = passportNumber;
		this.password_hash = password_hash;
		this.passport_salt = passport_salt;
	}

	

	public int PassengerId() {
		return passengerId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getGender() {
		return gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public Date getPassport_issue_date() {
		return passport_issue_date;
	}

	public Date getPassport_expiry_date() {
		return passport_expiry_date;
	}

	

	public String getPassword_hash() {
		return password_hash;
	}

	public String getPassport_salt() {
		return passport_salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public void setPassport_issue_date(Date passport_issue_date) {
		this.passport_issue_date = passport_issue_date;
	}

	public void setPassport_expiry_date(Date passport_expiry_date) {
		this.passport_expiry_date = passport_expiry_date;
	}

	

	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}

	public void setPassport_salt(String passport_salt) {
		this.passport_salt = passport_salt;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoyaltyPoints getLoyaltyPoints() {
		return loyaltyPoints;
	}





	public void setLoyaltyPoints(LoyaltyPoints loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}





	public int getPassengerId() {
		return passengerId;
	}





	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", name=" + name + ", email=" + email + ", username="
				+ username + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", mobileNumber=" + mobileNumber
				+ ", passportNumber=" + passportNumber + ", passport_issue_date=" + passport_issue_date
				+ ", passport_expiry_date=" + passport_expiry_date + ", password_hash=" + password_hash
				+ ", passport_salt=" + passport_salt + ", password=" + password + "]";
	}

	

}
