package ai.blogapp.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ai.blogapp.repository.entity.Tag;
import ai.blogapp.repository.mapper.TagMapper;

@Repository
public class TagRepository {

	private final JdbcTemplate jdbcTemplate;
	
	public TagRepository (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//Here, Tags are Entity Tags, not Model Tags
	public List<Tag> findAll() {
		String sql = "SELECT * FROM tags";
		return jdbcTemplate.query(sql, new TagMapper());
	}
	
	public Tag findById(int id) {
		String sql = "SELECT * FROM tags WHERE id = ?";
		List<Tag> entities = jdbcTemplate.query(sql, new TagMapper(), id);
		return entities.isEmpty()?null:entities.get(0);
	}
	
	public int add(Tag tag) {
		String sql = "INSERT INTO tags(name, description) VALUES(?, ?)";
		return jdbcTemplate.update(sql, tag.getName(), tag.getDescription());
	}
	
	public int edit(int id, Tag tag) {
		String sql = "UPDATE tags SET name = ?, description = ? WHERE id = ?";
		return jdbcTemplate.update(sql, tag.getName(), tag.getDescription(), tag.getId());
	}
	
	public int delete(int id) {
		String sql = "DELETE FROM tags WHERE id = ?";
		deletePostTags(id);
		return jdbcTemplate.update(sql, id);
	}
	
	private int deletePostTags (int id) {
		return jdbcTemplate.update("DELETE FROM posts_tags WHERE tag_id = ?", id);
	}
}
