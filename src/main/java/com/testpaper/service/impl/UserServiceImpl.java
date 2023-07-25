package com.testpaper.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testpaper.model.User;
import com.testpaper.repository.UserRepository;
import com.testpaper.service.UserService;

/**
 * The UserServiceImpl class implements the UserService interface and provides
 * the implementation for managing user-related operations.
*/
@Service
public class UserServiceImpl implements UserService {
	
	/**
	 * The repository for accessing user in the database.
	 */
	@Autowired
	private UserRepository userRepo;
	
	/**
	 * Creates a new user and saves it in the database.
	 * If a user with the same email already exists, a RuntimeException is thrown.
	 *
	 * @param user The user object to create and save.
	 * @return The created user object.
	 * @throws RuntimeException if a user with the same email already exists.
	 */
	@Override
	public User createUser(User user) {
		User local=this.userRepo.findByEmail(user.getEmail());
		if(local!=null) {
			System.out.println("Email with ");
			throw new RuntimeException("User with this mail exist");
		}
		else {
			local = this.userRepo.save(user);
		}
		return local;
	}

	/**
	 * Retrieves a user by their username.
	 * If a user with the specified username does not exist, the email is used to search.
	 *
	 * @param username The username of the user.
	 * @return The user object if found, otherwise null.
	 */
	@Override
	public User findByUsername(String username) {
		User user;
		user = this.userRepo.findByUsername(username);
		if(user !=null)
			return user;
		else
			return this.findByEmail(username);
	}
	
	/**
	 * Retrieves a user by their email.
	 * If a user with the specified email does not exist, the phone number is used to search.
	 *
	 * @param email The email of the user.
	 * @return The user object if found, otherwise null.
	 */
	public User findByEmail(String email) {
		User user; 
		user= this.userRepo.findByEmail(email);
		if(user !=null)
			return user;
		else
			return this.findByPhoneNo(email);
	}

	/**
	 * Retrieves a user by their phone number.
	 *
	 * @param phoneNo The phone number of the user.
	 * @return The user object if found, otherwise null.
	 */
	public User findByPhoneNo(String phoneNo) {
		User user;
		user= this.userRepo.findByPhoneNo(phoneNo);
	    return user;
	}
  
	/**
	 * Deletes a user with the specified user ID.
	 *
	 * @param userId The ID of the user to delete.
	 */
	@Override
	public void deleteUser(Long userId) {
		this.userRepo.deleteById(userId);
	}

	/**
	 * Retrieves a user by their user ID.
	 *
	 * @param userID The ID of the user.
	 * @return The optional user object.
	 */
	@Override
	public Optional<User> findByUserId(Long userID) {

		return this.userRepo.findById(userID);
	}

	/**
	 * Retrieves all users.
	 *
	 * @return A list of all users.
	 */
	@Override
	public List<User> getAllUsers() {
		return  userRepo.findAll();
	}

	/**
	 * Retrieves a user by their email.
	 *
	 * @param email The email of the user.
	 * @return The user object if found, otherwise null.
	 */
	@Override
	public User getUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}
}
