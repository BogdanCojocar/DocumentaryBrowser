package org.web.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "comments")
public class Comment {
	
	private final static int MIN_SIZE = 5;
	private final static int MAX_SIZE = 40;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "documentary_title")
	@Size(min = MIN_SIZE, max = MAX_SIZE, message = "{Size.documentaryInfo.title}")
	private String documentaryTitle;

	@Size(min = MIN_SIZE, max = MAX_SIZE, message = "{Size.comment.author}")
	private String author;

	@Email
	private String email;

	@Lob
	@Size(min = MIN_SIZE, message = "{Size.comment.text}")
	private String text;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDocumentaryTitle() {
		return documentaryTitle;
	}

	public void setDocumentaryTitle(String documentaryTitle) {
		this.documentaryTitle = documentaryTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", documentaryTitle=" + documentaryTitle
				+ ", author=" + author + ", email=" + email + ", text=" + text
				+ "]";
	}

}
