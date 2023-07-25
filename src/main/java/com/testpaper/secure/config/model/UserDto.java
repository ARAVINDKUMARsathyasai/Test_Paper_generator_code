package com.testpaper.secure.config.model;

import jakarta.persistence.Column;

/**
 * The UserDto class represents a Data Transfer Object (DTO) for the User entity.
 * It is used to transfer user data between different layers of the application.
 * 
 * @see com.testpaper.model.User
*/
public class UserDto {
	
	/**
	 * The ID of the user.
	 *
	 * @see com.testpaper.model.User#getId()
	 * @see com.testpaper.model.User#setId(Long)
	 */
    private Long id;
	
    /**
     * The username of the user.
     *
     * @see com.testpaper.model.User#getUsername()
     * @see com.testpaper.model.User#setUsername(String)
     */
	private String username;
	
	/**
	 * The email of the user.
	 *
	 * @see com.testpaper.model.User#getEmail()
	 * @see com.testpaper.model.User#setEmail(String)
	 */
	private String email;
	
	/**
	 * The password of the user.
	 *
	 * @see com.testpaper.model.User#getPassword()
	 * @see com.testpaper.model.User#setPassword(String)
	 */
	private String password;
	
	/**
	 * The phone number of the user.
	 *
	 * @see com.testpaper.model.User#getPhoneNo()
	 * @see com.testpaper.model.User#setPhoneNo(String)
	 */
	private String phoneNo;
	
	/**
	 * The role of the user.
	 *
	 * @see com.testpaper.model.User#getRole()
	 * @see com.testpaper.model.User#setRole(String)
	 */
	private String role;
	
	/**
	 * The checked status of the user.
	 *
	 * @see com.testpaper.model.User#isChecked()
	 * @see com.testpaper.model.User#setChecked(boolean)
	 */
	private boolean checked=true;

	/**
	 * Default constructor for the UserDto class.
	 */
	public UserDto() {
		super();
	}

	/**
	 * Constructs a new UserDto object with the specified parameters.
	 *
	 * @param id       The ID of the user.
	 * @param username The username of the user.
	 * @param email    The email of the user.
	 * @param password The password of the user.
	 * @param phoneNo  The phone number of the user.
	 * @param role     The role of the user.
	 * @param checked  The checked status of the user.
	 */
	public UserDto(Long id, String username, String email, String password, String phoneNo, String role,
			boolean checked) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNo = phoneNo;
		this.role = role;
		this.checked = checked;
	}

	/**
	 * Constructs a new UserDto object with the specified parameters.
	 *
	 * @param username The username of the user.
	 * @param email    The email of the user.
	 * @param password The password of the user.
	 * @param phoneNo  The phone number of the user.
	 * @param role     The role of the user.
	 * @param checked  The checked status of the user.
	 */
	public UserDto(String username, String email, String password, String phoneNo, String role, boolean checked) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNo = phoneNo;
		this.role = role;
		this.checked = checked;
	}

	/**
	 * Retrieves the ID of the user.
	 *
	 * @return The ID of the user.
	 *
	 * @see com.testpaper.model.User#getId()
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the ID of the user.
	 *
	 * @param id The ID to set for the user.
	 *
	 * @see com.testpaper.model.User#setId(Long)
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retrieves the username of the user.
	 *
	 * @return The username of the user.
	 *
	 * @see com.testpaper.model.User#getUsername()
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username of the user.
	 *
	 * @param username The username to set for the user.
	 *
	 * @see com.testpaper.model.User#setUsername(String)
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Retrieves the email of the user.
	 *
	 * @return The email of the user.
	 *
	 * @see com.testpaper.model.User#getEmail()
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the user.
	 *
	 * @param email The email to set for the user.
	 *
	 * @see com.testpaper.model.User#setEmail(String)
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retrieves the password of the user.
	 *
	 * @return The password of the user.
	 *
	 * @see com.testpaper.model.User#getPassword()
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user.
	 *
	 * @param password The password to set for the user.
	 *
	 * @see com.testpaper.model.User#setPassword(String)
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Retrieves the phone number of the user.
	 *
	 * @return The phone number of the user.
	 *
	 * @see com.testpaper.model.User#getPhoneNo()
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * Sets the phone number of the user.
	 *
	 * @param phoneNo The phone number to set for the user.
	 *
	 * @see com.testpaper.model.User#setPhoneNo(String)
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * Retrieves the role of the user.
	 *
	 * @return The role of the user.
	 *
	 * @see com.testpaper.model.User#getRole()
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role of the user.
	 *
	 * @param role The role to set for the user.
	 *
	 * @see com.testpaper.model.User#setRole(String)
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Retrieves the checked status of the user.
	 *
	 * @return The checked status of the user.
	 *
	 * @see com.testpaper.model.User#isChecked()
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * Sets the checked status of the user.
	 *
	 * @param checked The checked status to set for the user.
	 *
	 * @see com.testpaper.model.User#setChecked(boolean)
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
