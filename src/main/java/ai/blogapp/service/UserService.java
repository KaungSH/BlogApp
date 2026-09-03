package ai.blogapp.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ai.blogapp.model.Role;
import ai.blogapp.model.UserModel;
import ai.blogapp.model.UserStatus;
import ai.blogapp.model.usermanagement.ChangePasswordModel;
import ai.blogapp.model.usermanagement.ChangeProfileModel;
import ai.blogapp.model.usermanagement.LoginModel;
import ai.blogapp.model.usermanagement.SignupModel;
import ai.blogapp.repository.UserRepository;
import ai.blogapp.repository.entity.User;

@Service
public class UserService {
	
	private final UserRepository urepo;
	private final PasswordService pws;
	
	public UserService(UserRepository urepo, PasswordService pws) {
		this.urepo = urepo;
		this.pws = pws;
	}
	
	public List<UserModel> findAll() {
		return urepo.findAll().stream().map(this::toModel).toList();
	}
	
	public List<UserModel> findDeletedAll() {
		return urepo.findDeletedAll().stream().map(this::toModel).toList();
	}
	
	public UserModel findById(String id) {
		return (urepo.findById(id) == null) ? null : toModel(urepo.findById(id));
	}
	
	public UserModel findByEmail(String email) {
		return (urepo.findByEmail(email) == null) ? null : toModel(urepo.findByEmail(email));
	}
	
	public UserModel login(LoginModel luser) {
		if(urepo.findByEmail(luser.getEmail()) == null) {System.out.println("if method"); return null;}
		return (pws.matches(luser.getPassword(), urepo.findByEmail(luser.getEmail()).getPassword())) ? toModel(urepo.findByLogin(luser.getEmail(), urepo.findByEmail(luser.getEmail()).getPassword())) : null;
	}
	
	public int signup(SignupModel suser) {
		return urepo.add(toEntity(suser));
	}
	
	public int changePassword(ChangePasswordModel cpuser) {
		if(urepo.findById(cpuser.getId()) == null) return 0;
		return (urepo.findById(cpuser.getId()).getPassword().equals(cpuser.getOldpassword())) ? urepo.changePassword(cpuser.getId(), cpuser.getNewpassword()) : 0;
	}
	
	public int changeProfile(ChangeProfileModel cfuser) {
		if(urepo.findById(cfuser.getId()) == null) {System.out.println("if method"); return 0;}
		if(cfuser.getPhoto() == null) {System.out.println("this333!!!!");}
		return (pws.matches(cfuser.getPassword(), urepo.findById(cfuser.getId()).getPassword())) ? urepo.changeProfile(cfuser.getId(), cfuser.getUsername(), cfuser.getPhone(), cfuser.getPhoto()) : 0;
	}
	
	public int changeUserStatus(UserModel muser) {
		return (urepo.findById(muser.getId()) == null) ? 0 : urepo.changeStatus(muser.getId(), muser.getStatus());
	}
	
	public int changeUserRole(UserModel muser) {
		return (urepo.findById(muser.getId()) == null) ? 0 : urepo.changeRole(muser.getId(), muser.getRole());
	}
	
	private UserModel toModel(User euser) {
		return new UserModel(euser.getId(), euser.getUsername(), euser.getEmail(), euser.getPhone(), euser.getRole(), euser.getStatus(), euser.getPhoto());
	}
	
	private User toEntity(SignupModel suser) {
		return new User(UUID.randomUUID().toString(), suser.getUsername(), suser.getEmail(), suser.getPhone(), UserStatus.NORMAL.toString(), pws.encode(suser.getPassword()), Role.USER.toString(), suser.getPhoto());
	}

}
