package ai.blogapp.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ai.blogapp.model.PostEntryModel;
import ai.blogapp.model.PostListModel;
import ai.blogapp.repository.entity.Post;
import ai.blogapp.repository.PostRepository;
import ai.blogapp.repository.entity.*;

@Service
public class PostService {
	
	private final PostRepository prepo;
	
	public PostService (PostRepository prepo) {
		this.prepo = prepo;
	}
	
	public List<PostListModel> findAll() {
		return prepo.findDetailAll().stream().map(this::toListModel).toList();
	}
	
	public List<PostListModel> findPublishedAll() {
		return prepo.findPublishedAll().stream().map(this::toListModel).toList();
	}
	
	public List<PostListModel> findDeletedAll() {
		return prepo.findDeletedAll().stream().map(this::toListModel).toList();
	}
	
	public int add(PostEntryModel mentry) {
		mentry.setId(UUID.randomUUID().toString());
		return prepo.add(toEntity(mentry));
	}
	
	public PostEntryModel findById(String id) {
		return (prepo.findDetailById(id) == null) ? null : toEntryModel2(prepo.findDetailById(id));
	}
	
	public PostListModel findDetailById(String id) {
		return (prepo.findDetailById(id) == null) ? null : toListModel(prepo.findDetailById(id));
	}
	
	public PostListModel findDeletedById(String id) {
		return (prepo.findDeletedById(id) == null) ? null : toListModel(prepo.findDeletedById(id));
	}
	
	public int edit(PostEntryModel mentry) {
		return prepo.edit(mentry.getId(), toEntity(mentry));
	}
	
	public int delete(String id) {
		return prepo.delete(id);
	}
	
	public int recover(String id) {
		return prepo.recover(id);
	}

	private PostListModel toListModel(Post epost) {
		return new PostListModel(epost.getId(), epost.getTitle(), epost.getStatus(), 
								 getCoverImageFileName(epost.getCover_img_path()), epost.getContent(), epost.getCategory().getName(), 
								 epost.getTags().stream().map(Tag::getName).collect(Collectors.joining(" | ")), 
								 epost.getCreatedat(), epost.getCreateduser_id());
	}
	
//	private PostEntryModel toEntryModel(Post epost) {
//		return new PostEntryModel(epost.getId(), epost.getTitle(), epost.getContent(), epost.getCover_img_path(), 
//								  epost.getStatus(), epost.getCreateduser_id(), epost.getCategory_id(), 
//								  epost.getTags().stream().map(Tag::getId).toList());
//	}
	
	private PostEntryModel toEntryModel2(Post epost) {
		return new PostEntryModel(epost.getId(), epost.getTitle(), epost.getContent(), epost.getCover_img_path(), getCoverImageFileName(epost.getCover_img_path()), 
								  epost.getStatus(), epost.getCreateduser_id(), epost.getCategory_id(), 
								  epost.getTags().stream().map(Tag::getId).toList());
	}
	
	private Post toEntity(PostEntryModel mentry) {
		return new Post(mentry.getId(), mentry.getTitle(), mentry.getContent(), mentry.getCover_img_path(), mentry.getCreateduser_id(),mentry.getStatus().toString(), mentry.getCategory_id(), mentry.getTagIds());
	}
	
	private String getCoverImageFileName(String cover_img_path) {
	    if (cover_img_path == null || cover_img_path.isEmpty()) {
	        return null;
	    }
	    // Extracts just the file name from the full path string
	    return java.nio.file.Paths.get(cover_img_path).getFileName().toString();
	}
}
