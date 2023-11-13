package user;

public class User 
{
	private int ID;
	private String name, email, password;
	
	public User() {
		this(null, null, null);
	}
	
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "ПІБ: " + name + " Email: " + email;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
}
