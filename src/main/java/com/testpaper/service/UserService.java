package com.testpaper.service;

import java.util.List;
import java.util.Optional;

import com.testpaper.model.User;

/**
 * The UserService interface provides methods for managing user-related operations.
 * It defines functionality for creating users, retrieving users, deleting users, and more.
*/
public interface UserService {
	
	/**
	 * Creates a new user with the provided user object.
	 * 
	 * @param user The user object containing user details.
	 * @return The created user.
    */
	//creating the new user
	public User createUser(User user);
	
	/**
	 * Retrieves a user by their username.
	 * 
	 * @param username The username of the user to retrieve.
	 * @return The user with the specified username.
	 * @see com.testpaper.model.User
    */
	//getting the user by username
	public User findByUsername(String username);
	
	/**
	 * Retrieves all users.
	 * 
	 * @return A list of all users.
	 * @see com.testpaper.model.User
    */
	public List<User> getAllUsers();
	
	/**
	 * Deletes a user with the specified ID.
	 * 
	 * @param userId The ID of the user to delete.
    */
	//delete user by id
	public void deleteUser(Long userId);
	
	/**
	 * Retrieves a user by their ID.
	 * 
	 * @param userID The ID of the user to retrieve.
	 * @return An Optional containing the user with the specified ID, or empty if not found.
	 * @see com.testpaper.model.User
    */
	//find by id
	public Optional<User> findByUserId(Long userID);

	/**
	 * Retrieves a user by their email address.
	 * 
	 * @param email The email address of the user to retrieve.
	 * @return The user with the specified email address.
	 * @see com.testpaper.model.User
    */
	public User getUserByEmail(String email);
	
}
