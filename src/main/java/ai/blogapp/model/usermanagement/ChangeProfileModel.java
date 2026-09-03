package ai.blogapp.model.usermanagement;

public class ChangeProfileModel {
	
	private String id, username, phone, password;
	private byte[] photo;
	
	public ChangeProfileModel() {}

	public ChangeProfileModel(String id, String username, String phone, byte[] photo, String password) {
		this.id = id;
		this.username = username;
		this.phone = phone;
		this.photo = photo;
		this.password = password;
	}
	
	public ChangeProfileModel(String id, String username, String phone, byte[] photo) {
		this.id = id;
		this.username = username;
		this.phone = phone;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
