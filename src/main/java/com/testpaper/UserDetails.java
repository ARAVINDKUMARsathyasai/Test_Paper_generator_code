package com.testpaper;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.testpaper.model.User;

/**
 * The UserDetails class implements the Spring Security UserDetails interface
 * and represents the details of a user for authentication and authorization purposes.
 * It encapsulates the user's username, password, enabled status, and granted authorities.
 * 
 * @author TPG team
 * @since 21-06-2023
 * @version 17
*/
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

	/**
	 * The username of the user.
	 */
	private String userName;
	/**
     * The password of the user.
     */
	private String password;
	/**
     * Indicates whether the user account is enabled or disabled.
     */
	private boolean isChecked;
	/**
     * The authorities granted to the user.
     * These authorities determine the user's access privileges.
     */
	private List<GrantedAuthority> authorities;
	
	/**
     * Constructs a new UserRegistrationDetails object based on the provided User entity.
     *
     * @param user the User entity from which to extract the user details
     */
	public UserDetails(User user) {
		super();
		this.userName = user.getEmail();
		this.password = user.getPassword();
		this.isChecked = user.isChecked();
		this.authorities = Arrays.stream(user.getRole()
				                 .split(","))
				                 .map(SimpleGrantedAuthority::new)
				                 .collect(Collectors.toList());
	}

	/**
     * {@inheritDoc}
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     * 
     * @return The authorities granted to the user.
     */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	
	/**
     * {@inheritDoc}
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     * 
     * @return The password of the user.
     */
	@Override
	public String getPassword() {
		return password;
	}

	/**
     * {@inheritDoc}
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     * 
     * @return The username of the user.
     */
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	/**
     * {@inheritDoc}
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     * 
     * @return true if the user account is not expired, false otherwise.
     */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
     * {@inheritDoc}
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     * 
     * @return true if the user account is not locked, false otherwise.
     */
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
     * {@inheritDoc}
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     * 
     * @return true if the user account credentials are not expired, false otherwise.
     */
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
     * {@inheritDoc}
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     * 
     * @return true if the user account is enabled, false otherwise.
     */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isChecked;
	}
}
