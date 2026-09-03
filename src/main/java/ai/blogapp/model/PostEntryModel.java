package ai.blogapp.model;

import java.util.List;

import jakarta.validation.constraints.*;

public class PostEntryModel {

	private String id, title, content, cover_img_path, createduser_id, coverImgName;
	@Min(value = 1, message = "Please Select at least one Category.")
	private int category_id;
	private Status status;
	
	private List<Integer> tagIds;
	
	public PostEntryModel() {}
	
	public PostEntryModel(String id, String title, String content, String cover_img_path, String status, String createduser_id, int category_id, List<Integer> tagIds) {
		
		this.id = id;
		this.title = title;
		this.content = content;
		this.cover_img_path = cover_img_path;
		if (status.isEmpty() || status.equals(null)) {
			this.status = null;
		} else {
			this.status = Status.valueOf(status.toUpperCase());
		}
		this.createduser_id = createduser_id;
		this.category_id = category_id;
		this.tagIds = tagIds;
		
	}
	
	public PostEntryModel(String id, String title, String content, String cover_img_path, String coverImgName, String status, String createduser_id, int category_id, List<Integer> tagIds) {
		
		this.id = id;
		this.title = title;
		this.content = content;
		this.cover_img_path = cover_img_path;
		if (status.isEmpty() || status.equals(null)) {
			this.status = null;
		} else {
			this.status = Status.valueOf(status.toUpperCase());
		}
		this.createduser_id = createduser_id;
		this.category_id = category_id;
		this.tagIds = tagIds;
		this.coverImgName = coverImgName;
		
	}
	
	public PostEntryModel(String status) {
		if (status.isEmpty() || status.equals(null)) {
			this.status = null;
		} else {
			this.status = Status.valueOf(status.toUpperCase());
		}
	}

	public String getCoverImgName() {
		return coverImgName;
	}

	public void setCoverImgName(String coverImgName) {
		this.coverImgName = coverImgName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCover_img_path() {
		return cover_img_path;
	}

	public void setCover_img_path(String cover_img_path) {
		this.cover_img_path = cover_img_path;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getCreateduser_id() {
		return createduser_id;
	}

	public void setCreateduser_id(String createduser_id) {
		this.createduser_id = createduser_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public List<Integer> getTagIds() {
		return tagIds;
	}

	public void setTagIds(List<Integer> tagIds) {
		this.tagIds = tagIds;
	}
	
	
}
