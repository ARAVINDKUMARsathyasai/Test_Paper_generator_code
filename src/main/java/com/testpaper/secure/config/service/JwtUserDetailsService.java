package com.testpaper.secure.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.testpaper.secure.config.model.UserDto;
import com.testpaper.model.User;
import com.testpaper.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * The JwtUserDetailsService class implements the UserDetailsService interface from Spring Security.
 * It is responsible for loading user-specific data during authentication and registration processes.
 * This class interacts with the UserRepository and PasswordEncoder to perform user-related operations.
*/
@Service
public class JwtUserDetailsService implements UserDetailsService {
	/**
	 * The UserRepository is responsible for interacting with the database
	 * and performing CRUD operations on the User entity.
    */
	@Autowired
	private UserRepository userDao;

	/**
	 * The PasswordEncoder is responsible for encoding passwords
	 * to ensure secure storage and authentication.
    */
	@Autowired
	private PasswordEncoder bcryptEncoder;

	/**
	 * The roles associated with the user.
    */
	private String roles;
	
	/**
	 * Loads the user details based on the provided email.
	 *
	 * @param email The email of the user.
	 * @return The UserDetails object containing the user details.
	 * @throws UsernameNotFoundException if the user with the given email is not found.
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles = null;
		User user = userDao.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
		roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
		System.out.println("Roles : " + roles);
		setRoles(roles.toString());
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
	}

	/**
	 * Retrieves the roles associated with the user.
	 *
	 * @return The roles associated with the user.
	 */
	public String getRoles() {
		return roles;
	}

	/**
	 * Sets the roles associated with the user.
	 *
	 * @param roles The roles to set for the user.
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}

	/**
	 * Saves a new user based on the provided user data.
	 *
	 * @param user The user data to save.
	 * @return The saved User object.
	 */
	public User save(UserDto user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole(user.getRole());
		return userDao.save(newUser);
	}
}
