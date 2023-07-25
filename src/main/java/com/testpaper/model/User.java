package com.testpaper.model;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * The User class represents a user entity in the system.
 * It contains information such as the user's ID, username, email, password, 
 * phone number, role, and checked status.
 * 
 * @author TPG team
 * @since 21-06-2023
 * @version 17
*/
@Entity
@DynamicUpdate
@Table(name="user")
public class User {
	
	/**
	 * The ID of the user.
    */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="userId")
	private Long id;
	
	/**
	 * The username of the user.
    */
	@Column(name="username")
	@NotNull(message = "user name is mandatory")
	private String username;
	
	/**
	 * The email of the user.
    */
	@Column(name="email", unique=true)
	@NotNull(message = "email is mandatory")
	@Email(message="Enter the email in proper email format")
	private String email;
	
	/**
	 * The password of the user.
    */
	@Column(name="password")
	@Size(min = 8)
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).+$")
	@NotNull(message="Password is mandatory")
	private String password;
	
	/**
	 * The phone number of the user.
    */
	@Column(name="phone_no", unique=true)
	@Size(min=10,max=10,message="Enter a valid mobile number")
	@NotNull(message="Phone number is mandatory")
	private String phoneNo;
	
	/**
	 * The role of the user.
    */
	@NotNull(message="Role data is needed")
	@Column(name="role")
	private String role;
	
	/**
	 * The checked status of the user.
    */
	@NotNull(message="This is always auto updated")
	@Column(name="checked")
	private boolean checked=true;

	/**
	 * Constructs a new User object with the specified parameters.
	 *
	 * @param id       The ID of the user.
	 * @param username The username of the user.
	 * @param email    The email of the user.
	 * @param password The password of the user.
	 * @param phoneNo  The phone number of the user.
	 * @param role     The role of the user.
	 * @param checked  The checked status of the user.
	 */
	public User(Long id, String username, String email, String password, String phoneNo, String role, boolean checked) {
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
	 * Constructs a new User object with the specified parameters.
	 *
	 * @param username The username of the user.
	 * @param email    The email of the user.
	 * @param password The password of the user.
	 * @param phoneNo  The phone number of the user.
	 * @param role     The role of the user.
	 * @param checked  The checked status of the user.
	 */
	public User(String username, String email, String password, String phoneNo, String role, boolean checked) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNo = phoneNo;
		this.role = role;
		this.checked = checked;
	}

	/**
	 * Default constructor for the User class.
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Retrieves the ID of the user.
	 *
	 * @return The ID of the user.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the ID of the user.
	 *
	 * @param id The ID to set for the user.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retrieves the username of the user.
	 *
	 * @return The username of the user.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username of the user.
	 *
	 * @param username The username to set for the user.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Retrieves the email of the user.
	 *
	 * @return The email of the user.
	 */
	public String getEmail() {
		return email;
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
		return password;
	}

	/**
	 * Sets the password of the user.
	 *
	 * @param password The password to set for the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Retrieves the phone number of the user.
	 *
	 * @return The phone number of the user.
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * Sets the phone number of the user.
	 *
	 * @param phoneNo The phone number to set for the user.
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * Retrieves the role of the user.
	 *
	 * @return The role of the user.
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role of the user.
	 *
	 * @param role The role to set for the user.
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Retrieves the checked status of the user.
	 *
	 * @return The checked status of the user.
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * Sets the checked status of the user.
	 *
	 * @param checked The checked status to set for the user.
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * Returns a string representation of the User object.
	 *
	 * @return A string representation of the User object.
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", phoneNo=" + phoneNo + ", role=" + role + ", checked=" + checked + "]";
	}
}
