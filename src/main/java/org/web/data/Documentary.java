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

	private final static int MIN_SIZE_NAME = 5;
	private final static int MAX_SIZE_NAME = 40;
	private final static int MIN_SIZE_CATEGORY = 2;
	private final static int MAX_SIZE_CATEGORY = 10;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Size(min = MIN_SIZE_NAME, max = MAX_SIZE_NAME, message = "{Size.documentary.name}")
	private String name;

	@Size(min = MIN_SIZE_CATEGORY, max = MAX_SIZE_CATEGORY, message = "{Size.documentary.category}")
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
