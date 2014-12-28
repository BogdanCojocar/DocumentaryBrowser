package org.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web.data.User;
import org.web.data.UserDAO;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	private final static String ROLE_USER = "ROLE_USER";

	@Autowired
	private UserDAO userDAO;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {

		User user = userDAO.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		} else {
			List<GrantedAuthority> authRoles = buildUserAuthority(ROLE_USER);
			return new org.springframework.security.core.userdetails.User(
					user.getUsername(), user.getPassword(), true, true, true,
					true, authRoles);
		}
	}

	private List<GrantedAuthority> buildUserAuthority(String role) {
		List<GrantedAuthority> authRoles = new ArrayList<GrantedAuthority>();
		authRoles.add(new SimpleGrantedAuthority(role));
		return authRoles;
	}

}
