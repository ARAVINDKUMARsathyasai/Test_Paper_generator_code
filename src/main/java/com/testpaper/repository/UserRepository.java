package com.testpaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testpaper.model.User;

/**
 * The UserRepository interface extends JpaRepository to provide CRUD operations for User entities.
 * 
 * @see JpaRepository
 * @author TPG team
 * @since 21-06-2023
 * @version 17
*/
public interface UserRepository extends JpaRepository<User,Long> {

	/**
	 * Retrieves a user by their email.
	 *
	 * @param email The email of the user.
	 * @return The user with the specified email.
	 */
   public User findByEmail(String email);

   /**
    * Retrieves a user by their username.
    *
    * @param username The username of the user.
    * @return The user with the specified username.
    */
   public User findByUsername(String username);

   /**
    * Retrieves a user by their phone number.
    *
    * @param phoneNo The phone number of the user.
    * @return The user with the specified phone number.
    */
   public User findByPhoneNo(String phoneNo);

   /**
    * Counts the number of users with the specified checked status.
    *
    * @param b The checked status of the user.
    * @return The count of users with the specified checked status.
    */
   public long countByChecked(boolean b);
}
