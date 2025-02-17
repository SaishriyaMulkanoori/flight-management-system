package cloud.wing.admin.entity;

public class BussinessOwner {

	private int id;
	private String name;
	private String email;
	private String username;
	private String gender;
	private String mobile;
	private String password;
	private String password_salt;
	private String password_hash;

	public BussinessOwner(int id, String name, String email, String username, String gender, String mobile,
			String password, String password_salt, String password_hash) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.gender = gender;
		this.mobile = mobile;
		this.password = password;
		this.password_salt = password_salt;
		this.password_hash = password_hash;
	}

	public BussinessOwner(int id, String name, String email, String username, String gender, String mobile,
			String password_salt, String password_hash) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.gender = gender;
		this.mobile = mobile;
		this.password_salt = password_salt;
		this.password_hash = password_hash;
	}

	@Override
	public String toString() {
		return "BussinessOwner [id=" + id + ", name=" + name + ", email=" + email + ", username=" + username
				+ ", gender=" + gender + ", mobile=" + mobile + ", password=" + password + ", password_salt="
				+ password_salt + ", password_hash=" + password_hash + "]";
	}

	public BussinessOwner(int id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}
