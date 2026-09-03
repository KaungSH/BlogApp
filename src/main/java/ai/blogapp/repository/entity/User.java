package ai.blogapp.repository.entity;

public class User {
	
	private String id, username, email, phone, password, role, status;
	private byte[] photo;
	
	public User() {}
	
	public User (String id, String username, String email, String phone, String status, String password, String role,  byte[] photo) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.role = role;
		this.status = status;
		this.photo = photo;
	}
	
	public User (String id, String username, String email, String phone, String role, String status, byte[] photo) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.status = status;
		this.photo = photo;
	}
	
	public User (String username, String email, String phone, String status, String password, String role) {
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
