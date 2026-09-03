package ai.blogapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ai.blogapp.model.CategoryModel;
import ai.blogapp.repository.CategoryRepository;
import ai.blogapp.repository.entity.Category;

@Service
public class CategoryService {

private final CategoryRepository crepo;
	
	public CategoryService(CategoryRepository crepo) {
		this.crepo = crepo;
	}
	
	public List<CategoryModel> findAll() {
		return crepo.findAll().stream().map(this::toModel).toList();
	}
	
	public CategoryModel findById(int id) {
		Category entity = crepo.findById(id);
		if(entity == null) {
			return null;
		}
		
		return toModel(entity);
	}
	
	public int add(CategoryModel mcat) {
		return crepo.add(toEntity(mcat));
	}
	
	public int edit(int id, CategoryModel mcat) {
		return crepo.edit(id, toEntity(mcat));
	}
	
	public int delete(int id) {
		return crepo.delete(id);
	}
	
	private CategoryModel toModel(Category ecat) {
		CategoryModel mcat = new CategoryModel(ecat.getId(), ecat.getName(), ecat.getDescription());
		return mcat;
	}
	
	private Category toEntity(CategoryModel mcat) {
		Category ecat = new Category(mcat.getId(), mcat.getName(), mcat.getDescription());
		return ecat;
	}
}
