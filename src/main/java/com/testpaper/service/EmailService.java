package com.testpaper.service;

/**
 * The EmailService interface defines the contract for sending emails.
 * Implementing classes should provide the implementation for sending emails.
*/
public interface EmailService {

	/**
	 * Sends an email with the user's username, email, and password.
	 * 
	 * @param username The username of the user.
	 * @param email The email of the user.
	 * @param password The generated password for the user.
    */
   public void sendMail(String username, String email, String password);

}
