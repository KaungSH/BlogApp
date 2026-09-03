package ai.blogapp.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import ai.blogapp.repository.entity.Category;
import ai.blogapp.repository.entity.Post;
import ai.blogapp.repository.entity.Tag;

public class PostResultSetExtractor implements ResultSetExtractor<List<Post>>{

	@Override
	public List<Post> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<String, Post> postMap = new LinkedHashMap<>();
		
		while(rs.next()) {
			String postId = rs.getString("p.id");
			Post post = postMap.get(postId);
			if(post == null) {
				post = new Post(rs.getString("p.id"), rs.getString("p.title"),rs.getString("p.content"),
				   				rs.getString("p.cover_img_path"), rs.getString("p.createduser_id"),rs.getObject("p.createdat", LocalDate.class),
				   				rs.getObject("p.updatedat", LocalDate.class),rs.getString("p.status"), rs.getBoolean("p.isDeleted"), rs.getInt("p.category_id"));
				post.setCategory(new Category(rs.getInt("c.id"), rs.getString("c.name")));
				post.setTags(new ArrayList<Tag>());
			}
			
			Integer tagId = rs.getInt("t.id");
			if (tagId != null) {
				Tag tag = new Tag(rs.getInt("t.id"), rs.getString("t.name"));
				post.getTags().add(tag);
			}
			
			postMap.put(postId, post);
		}
		return new ArrayList<Post>(postMap.values());
	}

}
