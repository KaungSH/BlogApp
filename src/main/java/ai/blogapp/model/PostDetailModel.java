package ai.blogapp.model;

import java.time.LocalDate;

public class PostDetailModel {
	
	private String id, title, content, categoryName, tagNames, authorName;
	private LocalDate createdAt;

	public PostDetailModel() {}
	
	public PostDetailModel(String id, String title, String content, String categoryName, String tagNames, String authorName, LocalDate createdAt) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.categoryName = categoryName;
		this.tagNames = tagNames;
		this.authorName = authorName;
		this.createdAt = createdAt;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getTagNames() {
		return tagNames;
	}

	public void setTagNames(String tagNames) {
		this.tagNames = tagNames;
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

	@Override
	public String toString() {
		return "PostDetailModel [id=" + id + ", title=" + title + ", content=" + content + ", categoryName="
				+ categoryName + ", tagNames=" + tagNames + ", authorName=" + authorName + ", createdAt=" + createdAt
				+ "]";
	}
	
	
}
