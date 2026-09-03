package ai.blogapp.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import ai.blogapp.repository.entity.Post;

public class PostMapper implements RowMapper<Post>{

	@Override
	public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Post(rs.getString("id"), rs.getString("title"),rs.getString("content"),
				   		rs.getString("cover_img_path"), rs.getString("createduser_id"),rs.getObject("createdat", LocalDate.class),
				   		rs.getObject("updatedat", LocalDate.class),rs.getString("status"), rs.getBoolean("isDeleted"), rs.getInt("category_id"));
	}

}
