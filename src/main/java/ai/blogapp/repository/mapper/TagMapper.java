package ai.blogapp.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ai.blogapp.repository.entity.Tag;

public class TagMapper implements RowMapper<Tag>{
	
	@Override
	public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Tag (rs.getInt("id"), rs.getString("name"), rs.getString("description"));
	}

}
