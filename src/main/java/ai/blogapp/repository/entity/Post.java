package ai.blogapp.repository.entity;

import java.time.LocalDate;
import java.util.List;

public class Post {
	
	//Normal Data Fields
	private String id, title, content, cover_img_path, createduser_id;
	private LocalDate createdat, updatedat;
	private String status;
	private boolean isdeleted;
	private int category_id;
	private List<Integer> tagsIds;
	
	//Relation Data Fields
	private Category category; //For one to many - Post ----> Category
	private List<Tag> tags; //For many to many - Post --PostTags--> Tags

	public Post() {}
	
	public Post(String id, String title, String content, String cover_img_path, String createduser_id, LocalDate createdat, LocalDate updatedat, String status, boolean isdeleted, int category_id) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.cover_img_path = cover_img_path;
		this.createduser_id = createduser_id;
		this.createdat = createdat;
		this.updatedat = updatedat;
		this.status = status;
		this.isdeleted = isdeleted;
		this.category_id = category_id;
	}
	
	public Post(String id, String title, String content, String cover_img_path, String createduser_id, String status, int category_id, List<Integer> tagsIds) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.cover_img_path = cover_img_path;
		this.createduser_id = createduser_id;
		this.status = status;
		this.category_id = category_id;
		this.tagsIds = tagsIds;
	}

	public List<Integer> getTagsIds() {
		return tagsIds;
	}

	public void setTagsIds(List<Integer> tagsIds) {
		this.tagsIds = tagsIds;
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

	public String getCreateduser_id() {
		return createduser_id;
	}

	public void setCreateduser_id(String createduser_id) {
		this.createduser_id = createduser_id;
	}

	public LocalDate getCreatedat() {
		return createdat;
	}

	public void setCreatedat(LocalDate createdat) {
		this.createdat = createdat;
	}

	public LocalDate getUpdatedat() {
		return updatedat;
	}

	public void setUpdatedat(LocalDate updatedat) {
		this.updatedat = updatedat;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", cover_img_path=" + cover_img_path
				+ ", createduser_id=" + createduser_id + ", createdat=" + createdat + ", updatedat=" + updatedat
				+ ", status=" + status + ", isdeleted=" + isdeleted + ", category_id=" + category_id + "]";
	}
	
	
}
