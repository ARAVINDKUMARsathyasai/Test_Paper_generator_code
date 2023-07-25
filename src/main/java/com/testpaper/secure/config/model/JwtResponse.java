package com.testpaper.secure.config.model;

import java.io.Serializable;

/**
 * The JwtResponse class represents the response object containing the JWT token.
 * It implements the Serializable interface.
*/
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	/**
	 * The JWT token.
	 */
	private final String jwttoken;
	
	/**
	 * The role of the user associated with the JWT token.
	 */
    private String role;
    
    /**
     * Constructs a new JwtResponse object with the specified JWT token.
     *
     * @param jwttoken The JWT token.
     */
	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	
	/**
	 * Constructs a new JwtResponse object with the specified JWT token and role.
	 *
	 * @param jwttoken The JWT token.
	 * @param role     The role of the user.
	 */
	public JwtResponse(String jwttoken, String role) {
		this.jwttoken = jwttoken;
		this.role = role;
	}

	/**
	 * Retrieves the JWT token.
	 *
	 * @return The JWT token.
	 */
	public String getToken() {
		return this.jwttoken;
	}

	/**
	 * Retrieves the role of the user associated with the JWT token.
	 *
	 * @return The role of the user.
	 */
	public String getRole() {
		return role;
	}
	
}
