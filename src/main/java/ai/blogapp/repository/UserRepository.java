package ai.blogapp.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ai.blogapp.model.Role;
import ai.blogapp.model.UserStatus;
import ai.blogapp.repository.entity.User;
import ai.blogapp.repository.mapper.UserMapper;

@Repository
public class UserRepository {

	private final JdbcTemplate jdbcTemplate;
	
	public UserRepository (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//Here, Users are Entity Users, not Model Users
	public List<User> findAll() {
		String sql = "SELECT * FROM users where status != 'DELETED' and role != 'SUPERADMIN'";
		return jdbcTemplate.query(sql, new UserMapper());
	}
	
	public List<User> findDeletedAll() {
		String sql = "SELECT * FROM users where status = 'DELETED'";
		return jdbcTemplate.query(sql, new UserMapper());
	}
	
	public User findById(String id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		List<User> entities = jdbcTemplate.query(sql, new UserMapper(), id);
		return entities.isEmpty()?null:entities.get(0);
	}
	
	public User findByEmail(String email) {
		String sql = "SELECT * FROM users WHERE email = ?";
		List<User> entities = jdbcTemplate.query(sql, new UserMapper(), email);
		return entities.isEmpty()?null:entities.get(0);
	}
	
	public User findByLogin(String email, String password) {
		String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
		List<User> entities = jdbcTemplate.query(sql, new UserMapper(), email, password);
		return entities.isEmpty()?null:entities.get(0);
	}
	
	public int add(User user) {
		String sql = "INSERT INTO users(id, username, email, phone, password, role, status, photo) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, user.getId(), user.getUsername(), user.getEmail(), user.getPhone(), user.getPassword(), user.getRole(), user.getStatus(), user.getPhoto());
	}
	
	public int changeUsername(String id, String username) {
		String sql = "UPDATE users SET username = ? WHERE id = ?";
		return jdbcTemplate.update(sql, username, id);
	}
	
	public int changeProfile(String id, String username, String phone, byte[] photo) {
		if (photo != null) {
			System.out.println("this222!!!");
		}
		if(photo == null) {
			String sql = "UPDATE users SET username = ?, phone = ? WHERE id = ?";
			return jdbcTemplate.update(sql, username, phone, id);
		}
		String sql = "UPDATE users SET username = ?, phone = ?, photo = ? WHERE id = ?";
		return jdbcTemplate.update(sql, username, phone, photo, id);
	}
	
	public int changePassword(String id, String password) {
		String sql = "UPDATE users SET password = ? WHERE id = ?";
		return jdbcTemplate.update(sql, password, id);
	}
	
	public int changePhoto(String id, byte[] photo) {
		String sql = "UPDATE users SET photo = ? WHERE id = ?";
		return jdbcTemplate.update(sql, photo, id);
	}
	
	public int changeStatus(String id, UserStatus status) {
		System.out.println(status);
		String sql = "UPDATE users SET status = ? WHERE id = ?";
		return jdbcTemplate.update(sql, status.toString(), id);
	}
	
	public int changeRole(String id, Role role) {
		String sql = "UPDATE users SET role = ? WHERE id = ?";
		return jdbcTemplate.update(sql, role.toString(), id);
	}
	
}
