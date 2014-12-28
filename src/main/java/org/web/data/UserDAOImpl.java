package org.web.data;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

	private static final String QUERY_USERNAME = "from User where username = :name";

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByUserName(String username) {

		@SuppressWarnings("unchecked")
		List<User> users = sessionFactory.getCurrentSession()
				.createQuery(QUERY_USERNAME).setString("name", username).list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

}
