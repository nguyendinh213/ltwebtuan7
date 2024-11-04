package vn.iotstar.entity;

import java.io.Serializable;

//import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "videos")
@NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")
public class Video implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "VideoId")
	private int videoId;
	@Column(name = "Active")
	private int active;
	@Column(name = "Description", columnDefinition = "NVARCHAR(MAX) NOT NULL")
	private String description;
	@Column(name = "Poster", columnDefinition = "NVARCHAR(500) NOT NULL")
	private String poster;
	@Column(name = "Title", columnDefinition = "NVARCHAR(MAX) NOT NULL")
	private String title;
	@Column(name = "Views")
	private int views;
	@ManyToOne
	@JoinColumn(name = "CategoryId")
	private Category category;

	public Video() {
	}

	public int getVideoId() {
		return this.videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getViews() {
		return this.views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}