package org.web.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "info")
public class DocumentaryInfo {

	@Id
	private String title;

	@Lob
	private String description;
	
	@Column(name="imagepath")
	private String imagePath;
	private int rating;
	
	@Column(name="videolink")
	private String videoLink;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="id")
	private List<Comment> comments;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	@Override
	public String toString() {
		return "DocumentaryInfo [title=" + title + ", description="
				+ description + ", imagePath=" + imagePath + ", rating="
				+ rating + ", videoLink=" + videoLink + "]";
	}

}
