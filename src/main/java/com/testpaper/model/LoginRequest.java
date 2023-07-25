package com.testpaper.model;

/**
 * The LoginRequest class represents a login request sent by the client.
 * It contains the email and password fields for authentication.
 * 
 * @author TPG team
 * @since 21-06-2023
 * @version 17
*/
public class LoginRequest {
	/**
	 * The email address provided in the login request.
	 */
	private String email;

	/**
	 * The password provided in the login request.
	 */
	private String password;

	/**
	 * Constructs an empty LoginRequest object.
	 */
	public LoginRequest() {
		super();
	}

	/**
	 * Retrieves the email address from the login request.
	 *
	 * @return The email address.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address in the login request.
	 *
	 * @param email The email address to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retrieves the password from the login request.
	 *
	 * @return The password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password in the login request.
	 *
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns a string representation of the LoginRequest object.
	 *
	 * @return A string representation of the LoginRequest object.
	 */
	@Override
	public String toString() {
		return "LoginRequest [email=" + email + ", password=" + password + "]";
	}
}
