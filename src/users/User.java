package users;

public class User {

	private String id;
	private String email;
	private String firstName;
	private String lastName;
	private String ip;
	
	public User(String id, String email, String firstName, String lastName, String ip) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.ip = ip;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFullName()
	{
		return firstName + " " + lastName;
	}
	
	
}
