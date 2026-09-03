package ai.blogapp.model;

public class UserModel {
	
	private String id, username, email, phone;
	private UserStatus status;
	private Role role;
	private byte[] photo;
	
	public UserModel() {}
	
	public UserModel (String id, String username, String email, String phone, Role role, UserStatus status, byte[] photo) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.status = status;
		this.photo = photo;
	}
	
	public UserModel (String id, String username, String email, String phone, String role, String status, byte[] photo) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.phone = phone;
		if (role.isEmpty() || role.equals(null)) {
			this.role = null;
		} else {
			this.role = Role.valueOf(role.toUpperCase());
		}
		if (status.isEmpty() || status.equals(null)) {
			this.status = null;
		} else {
			this.status = UserStatus.valueOf(status.toUpperCase());
		}
		this.photo = photo;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}
	
	

}
