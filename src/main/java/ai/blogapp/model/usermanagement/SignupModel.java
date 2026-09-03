package ai.blogapp.model.usermanagement;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupModel {
	
	private String username, phone;
	@NotBlank(message = "Enter an Email")
	@Email(message = "Enter an Valid Email")
	private String email;
	@NotBlank(message = "Enter a Password")
	@Size (min = 3, message = "Password must have at least 3 characters")
	private String password;
	private String password2;
	private byte[] photo;
	
	
	public SignupModel() {}

	public SignupModel(String username, String email, String phone, String password, String password2) {
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.password2 = password2;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
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

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	

}
