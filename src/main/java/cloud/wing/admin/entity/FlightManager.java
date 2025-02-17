package cloud.wing.admin.entity;

public class FlightManager {

	private int id;
	private int business_owner_id;
	private String name;
	private String mobile;
	private String email;
	private String password;
	private String gender;
	private String username;
	private String password_salt;
	private String password_hash;
	public boolean isApproved;

	public boolean isApproved() {
		return isApproved;
	}

	public FlightManager(int id, String name, String mobile, String gender, String username) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.gender = gender;
		this.username = username;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public FlightManager(int id, int business_owner_id, String name, String mobile, String email, String gender,
			String username, String password_salt, String password_hash, boolean isApproved) {
		super();
		this.id = id;
		this.business_owner_id = business_owner_id;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.gender = gender;
		this.username = username;
		this.password_salt = password_salt;
		this.password_hash = password_hash;
		this.isApproved = isApproved;
	}

	public String getPassword_salt() {
		return password_salt;
	}

	public void setPassword_salt(String password_salt) {
		this.password_salt = password_salt;
	}

	public String getPassword_hash() {
		return password_hash;
	}

	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}

	public FlightManager() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBusiness_owner_id() {
		return business_owner_id;
	}

	public void setBusiness_owner_id(int business_owner_id) {
		this.business_owner_id = business_owner_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public FlightManager(int business_owner_id, String name, String mobile, String email, String password,
			String gender, String username) {
		super();
		this.business_owner_id = business_owner_id;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.username = username;
	}

	public FlightManager(int id, int business_owner_id, String name, String mobile, String email, String password,
			String gender, String username) {
		super();
		this.id = id;
		this.business_owner_id = business_owner_id;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.username = username;
	}

	public FlightManager(String name, String mobile, String email, String password, String gender, String username) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.username = username;
	}

	@Override
	public String toString() {
		return "FlightManager [id=" + id + ", business_owner_id=" + business_owner_id + ", name=" + name + ", mobile="
				+ mobile + ", email=" + email + ", password=" + password + ", gender=" + gender + ", username="
				+ username + ", password_salt=" + password_salt + ", password_hash=" + password_hash + ", isApproved="
				+ isApproved + "]";
	}

}
