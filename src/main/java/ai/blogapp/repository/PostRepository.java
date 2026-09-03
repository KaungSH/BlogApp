package ai.blogapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ai.blogapp.repository.entity.Post;
import ai.blogapp.repository.mapper.*;

@Repository
public class PostRepository {

	private final JdbcTemplate jdbcTemplate;
	
	public PostRepository (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//Here, Posts are Entity Posts, not Model Posts
	public List<Post> findAll() {
		String sql = "SELECT * FROM posts WHERE isdeleted = false";
		return jdbcTemplate.query(sql, new PostMapper());
	}
	
	public List<Post> findDetailAll() {
		String sql = "SELECT p.*, c.*, t.* FROM posts p INNER JOIN categories c ON p.category_id = c.id LEFT JOIN posts_tags pt ON pt.post_id = p.id LEFT JOIN tags t ON pt.tag_id = t.id WHERE p.isdeleted = false ORDER BY p.createdat";
		return jdbcTemplate.query(sql, new PostResultSetExtractor());
	}
	
	public List<Post> findDeletedAll() {
		String sql = "SELECT p.*, c.*, t.* FROM posts p INNER JOIN categories c ON p.category_id = c.id LEFT JOIN posts_tags pt ON pt.post_id = p.id LEFT JOIN tags t ON pt.tag_id = t.id WHERE p.isdeleted = true ORDER BY p.createdat";
		return jdbcTemplate.query(sql, new PostResultSetExtractor());
	}
	
	public List<Post> findPublishedAll() {
		String sql = "SELECT p.*, c.*, t.* FROM posts p INNER JOIN categories c ON p.category_id = c.id LEFT JOIN posts_tags pt ON pt.post_id = p.id LEFT JOIN tags t ON pt.tag_id = t.id WHERE p.isdeleted = false AND p.status = 'PUBLISHED' ORDER BY p.createdat";
		return jdbcTemplate.query(sql, new PostResultSetExtractor());
	}
	
	public Post findById(String id) {
		String sql = "SELECT * FROM posts WHERE id = ? AND isdeleted = false";
		List<Post> entities = jdbcTemplate.query(sql, new PostMapper(), id);
		return entities.isEmpty()?null:entities.get(0);
	}
	
	public Post findDetailById(String id) {
		String sql = "SELECT p.*, c.*, t.* FROM posts p INNER JOIN categories c ON p.category_id = c.id LEFT JOIN posts_tags pt ON pt.post_id = p.id LEFT JOIN tags t ON pt.tag_id = t.id WHERE p.isdeleted = false AND p.id = ?";
		List<Post> entities = jdbcTemplate.query(sql, new PostResultSetExtractor(), id);
		return entities.isEmpty()?null:entities.get(0);
	}
	
	public Post findDeletedById(String id) {
		String sql = "SELECT p.*, c.*, t.* FROM posts p INNER JOIN categories c ON p.category_id = c.id LEFT JOIN posts_tags pt ON pt.post_id = p.id LEFT JOIN tags t ON pt.tag_id = t.id WHERE p.isdeleted = true AND p.id = ?";
		List<Post> entities = jdbcTemplate.query(sql, new PostResultSetExtractor(), id);
		return entities.isEmpty()?null:entities.get(0);
	}
	
	public int add(Post post) {
		String sql = "INSERT INTO posts(id, title, content, cover_img_path, createdat, updatedat, createduser_id, status, category_id, isdeleted) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, false);";
		jdbcTemplate.update(sql, post.getId(), post.getTitle(), post.getContent(), post.getCover_img_path(), LocalDate.now(), LocalDate.now(), post.getCreateduser_id(), post.getStatus(), post.getCategory_id());
		return addPostTags(post.getId(), post.getTagsIds());
	}
	
	public int edit(String id, Post post) {
		String sql = "UPDATE posts SET title = ?, content = ?, cover_img_path = ?, updatedat = ?, status = ?, category_id = ? WHERE id = ?;";
		deletePostTags(id); addPostTags(post.getId(), post.getTagsIds());
		return jdbcTemplate.update(sql, post.getTitle(), post.getContent(), post.getCover_img_path(), LocalDate.now(), post.getStatus(), post.getCategory_id(), post.getId());
	}
	
	//SoftDelete
	public int delete(String id) {
		String sql = "UPDATE posts SET isdeleted = true WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
	public int recover(String id) {
		String sql = "UPDATE posts SET isdeleted = false WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
	private int addPostTags (String postId, List<Integer>tagIds){
		int i = 0;
		for(int t : tagIds) {
			i += jdbcTemplate.update("INSERT INTO posts_tags(post_id, tag_id) VALUES (?, ?);", postId, t);
		}
		return i;
	}
	
	private int deletePostTags (String id) {
		return jdbcTemplate.update("DELETE FROM posts_tags WHERE post_id = ?", id);
	}
}

