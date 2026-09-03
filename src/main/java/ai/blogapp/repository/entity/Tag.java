package ai.blogapp.repository.entity;

public class Tag {
	
	private int id;
	private String name, description;
	
	public Tag() {}
	public Tag(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public Tag(int id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
