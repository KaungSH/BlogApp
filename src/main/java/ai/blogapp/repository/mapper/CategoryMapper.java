package ai.blogapp.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ai.blogapp.repository.entity.Category;

public class CategoryMapper implements RowMapper<Category> {

	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Category (rs.getInt("id"), rs.getString("name"), rs.getString("description"));
	}

}
