package ai.blogapp.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ai.blogapp.repository.entity.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new User (rs.getString("id"), rs.getString("username"),rs.getString("email"), rs.getString("phone"), rs.getString("status"), rs.getString("password"), rs.getString("role"), rs.getBytes("photo"));
	}

}
