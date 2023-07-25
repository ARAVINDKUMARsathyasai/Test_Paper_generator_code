package com.testpaper.secure.config.model;

import java.io.Serializable;

/**
 * The JwtRequest class represents a request object used for JWT authentication.
 * It contains the user's email and password for authentication purposes.
 * This class is used for JSON parsing.
 * 
 * @see com.testpaper.secure.config.model.JwtResponse
*/
public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	/**
	 * The email of the user for authentication.
	 */
	private String email;
	/**
	 * The password of the user for authentication.
	 */
	private String password;
	
	/**
	 * Default constructor for JSON parsing.
	 */
	//default constructor for JSON Parsing
	public JwtRequest()
	{
		super();
	}

	/**
	 * Constructs a new JwtRequest object with the specified email and password.
	 *
	 * @param email    The email of the user.
	 * @param password The password of the user.
	 */
	public JwtRequest(String email, String password) {
		this.setEmail(email);
		this.setPassword(password);
	}

	/**
	 * Retrieves the email of the user.
	 *
	 * @return The email of the user.
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email of the user.
	 *
	 * @param email The email to set for the user.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retrieves the password of the user.
	 *
	 * @return The password of the user.
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password of the user.
	 *
	 * @param password The password to set for the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
