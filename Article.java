package example.jdbc.bean;
import java.util.Date;

public class Article {
	private int Id;
	private String name;
	private Category category;
	Date dateCreated = new Date();
	private String creatorName;
	public Article(int id, String name, Category category, Date dateCreated, String creatorName) {
		super();
		Id = id;
		this.name = name;
		this.category = category;
		this.dateCreated = dateCreated;
		this.creatorName = creatorName;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	@Override
	public String toString() {
		return "Article [Id=" + Id + ", name=" + name + ", category=" + category + ", dateCreated=" + dateCreated
				+ ", creatorName=" + creatorName + "]";
	}
	
	
	 
	 
}
