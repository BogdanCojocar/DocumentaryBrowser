package org.web.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	private static final int MIN_SIZE_USERNAME = 5;
	private static final int MIN_SIZE_PASSWORD = 5;

	@Id
	@Column(nullable = false)
	@Size(min = MIN_SIZE_USERNAME)
	private String username;

	@Column(nullable = false)
	@Size(min = MIN_SIZE_PASSWORD)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
}
