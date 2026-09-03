package ai.blogapp.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ai.blogapp.repository.entity.Category;
import ai.blogapp.repository.mapper.CategoryMapper;

@Repository
public class CategoryRepository {

	private final JdbcTemplate jdbcTemplate;
	
	public CategoryRepository (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//Here, Categories are Entity Categories, not Model Categories
	public List<Category> findAll() {
		String sql = "SELECT * FROM categories";
		return jdbcTemplate.query(sql, new CategoryMapper());
	}
	
	public Category findById(int id) {
		String sql = "SELECT * FROM categories WHERE id = ?";
		List<Category> entities = jdbcTemplate.query(sql, new CategoryMapper(), id);
		
		return entities.isEmpty()?null:entities.get(0);
	}
	
	public int add(Category category) {
		String sql = "INSERT INTO categories(name, description) VALUES(?, ?)";
		return jdbcTemplate.update(sql, category.getName(), category.getDescription());
	}
	
	public int edit(int id, Category category) {
		String sql = "UPDATE categories SET name = ?, description = ? WHERE id = ?";
		return jdbcTemplate.update(sql, category.getName(), category.getDescription(), category.getId());
	}
	
	public int delete(int id) {
		String sql = "DELETE FROM categories WHERE id = ?";
		deletePostCats(id);
		return jdbcTemplate.update(sql, id);
	}
	
	private int deletePostCats (int id) {
		return jdbcTemplate.update("UPDATE posts SET category_id = 0 WHERE category_id = ?", id);
	}
}

