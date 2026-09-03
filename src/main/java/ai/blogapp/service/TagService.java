package ai.blogapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ai.blogapp.model.TagModel;
import ai.blogapp.repository.TagRepository;
import ai.blogapp.repository.entity.Tag;

@Service
public class TagService {
	
	private final TagRepository trepo;
	
	public TagService(TagRepository trepo) {
		this.trepo = trepo;
	}
	
	public List<TagModel> findAll() {
		return trepo.findAll().stream().map(this::toModel).toList();
	}
	
	public TagModel findById(int id) {
		System.out.println("here11111");
		//return toModel(trepo.findById(id));
		Tag entity = trepo.findById(id);
		if(entity == null) {
			return null;
		}
		
		return toModel(entity);
	}
	
	public int add(TagModel mtag) {
		return trepo.add(toEntity(mtag));
	}
	
	public int edit(int id, TagModel mtag) {
		return trepo.edit(id, toEntity(mtag));
	}
	
	public int delete(int id) {
		return trepo.delete(id);
	}
	
	private TagModel toModel(Tag etag) {
		TagModel mtag = new TagModel(etag.getId(), etag.getName(), etag.getDescription());
		return mtag;
	}
	
	private Tag toEntity(TagModel mtag) {
		Tag etag = new Tag(mtag.getId(), mtag.getName(), mtag.getDescription());
		return etag;
	}

}
