package ai.blogapp.model;

import java.time.LocalDate;

public class PostListModel {
	
	private String id, title, status, categoryName, tagNames, authorName, coverImgName, content, created_user_id;
	private LocalDate createdAt;
	
	public PostListModel() {}
	
//	public PostListModel(String id, String title, String status, String coverImgName, String categoryName, String tagNames, String authorName, LocalDate createdAt) {
//		this.id = id;
//		this.title = title;
//		this.status = status;
//		this.coverImgName = coverImgName;
//		this.categoryName = categoryName;
//		this.tagNames = tagNames;
//		this.authorName = authorName;
//		this.createdAt = createdAt;
//	}
	
	public PostListModel(String id, String title, String status, String coverImgName, String content, String categoryName, String tagNames, LocalDate createdAt, String created_user_id) {
		this.id = id;
		this.title = title;
		this.status = status;
		this.coverImgName = coverImgName;
		this.content = content;
		this.categoryName = categoryName;
		this.tagNames = tagNames;
		this.createdAt = createdAt;
		this.created_user_id = created_user_id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	
	public String getTagNames() {
		return tagNames;
	}

	public void setTagNames(String tagNames) {
		this.tagNames = tagNames;
	}
	
	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCoverImgName() {
		return coverImgName;
	}

	public void setCoverImgName(String coverImgName) {
		this.coverImgName = coverImgName;
	}
	

	public String getCreated_user_id() {
		return created_user_id;
	}

	public void setCreated_user_id(String created_user_id) {
		this.created_user_id = created_user_id;
	}

	@Override
	public String toString() {
		return "PostListModel [id=" + id + ", title=" + title + ", status=" + status + ", categoryName=" + categoryName
				+ ", tagNames=" + tagNames + ", authorName=" + authorName + ", createdAt=" + createdAt + "]";
	}
	
	
	
}
