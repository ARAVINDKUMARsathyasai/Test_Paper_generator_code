package com.testpaper.Exception;

/**
 * The UserNotFoundException is an exception that is thrown when a user with the specified ID is not found.
 * It is a runtime exception, indicating a critical error in finding a user.
 * 
 * @see com.testpaper.model.User
 * 
 * @author TPG team
 * @since 21-06-2023
 * @version 17
*/
public class UserNotFoundException extends RuntimeException{
	
	/**
	 * Constructs a UserNotFoundException with the specified user ID.
	 * @param id The ID of the user that was not found.
    */
	public UserNotFoundException(Long id) {
		super("Could not found the user with id "+id);
	}
}
