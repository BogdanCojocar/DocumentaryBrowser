package org.web.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "documentaries")
public class Documentary {

	private final static int MIN_SIZE = 5;
	private final static int MAX_SIZE = 40;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Size(min = MIN_SIZE, max = MAX_SIZE)
	private String name;

	@Size(min = MIN_SIZE, max = MAX_SIZE)
	private String category;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Documentary [id=" + id + ", name=" + name + ", category="
				+ category + "]";
	}
}
