package ai.blogapp.model.usermanagement;

public class ChangePasswordModel {
	
	private String id, oldpassword, newpassword;
	
	public ChangePasswordModel() {}

	public ChangePasswordModel(String id, String oldpassword, String newpassword) {
		this.id = id;
		this.oldpassword = oldpassword;
		this.newpassword = newpassword;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	
	
	
}
