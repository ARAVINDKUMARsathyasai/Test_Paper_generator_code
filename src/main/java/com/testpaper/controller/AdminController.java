package com.testpaper.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testpaper.Exception.UserNotFoundException;
import com.testpaper.helper.TestCountResponse;
import com.testpaper.model.User;
import com.testpaper.repository.QuestionRepository;
import com.testpaper.repository.SubjectRepository;
import com.testpaper.repository.TestRepository;
import com.testpaper.repository.UserRepository;
import com.testpaper.service.EmailService;
import com.testpaper.service.UserService;

/**
 * This class represents the RESTful API controller for the Admin functionalities.
 * It handles various HTTP requests related to managing users, tests, questions, subjects,
 * and provides endpoints for retrieving statistics and counts.
 * The endpoints in this controller require authentication and are accessible only to the Admin role.
 * 
 * @author TPG team
 * @since 21-06-2023
 * @version 17
*/
@RestController
@RequestMapping("/Admin")
@CrossOrigin("http://localhost:3000/")
public class AdminController {
	
	/**
	 * The UserService is responsible for handling user-related operations,
	 * such as creating users, retrieving users, and deleting users.
    */
	@Autowired
	private UserService userService;
	
	/**
	 * The UserRepository is responsible for interacting with the database
	 * and performing CRUD operations on the User entity.
    */
	@Autowired
	private UserRepository userRepo;
	
	/**
	 * The TestRepository is responsible for interacting with the database
	 * and performing CRUD operations on the Test entity.
    */
	@Autowired
	private TestRepository testRepo;
	
	/**
	 * The QuestionRepository is responsible for interacting with the database
	 * and performing CRUD operations on the Question entity.
    */
	@Autowired
	private QuestionRepository questionRepo;
	
	/**
	 * The SubjectRepository is responsible for interacting with the database
	 * and performing CRUD operations on the Subject entity.
    */
	@Autowired
	private SubjectRepository subjectRepo;
	
	/**
	 * The EmailService is responsible for sending emails to users,
	 * such as sending password reset emails or registration confirmation emails.
    */
	@Autowired
	private EmailService emailService;
	
	/**
	 * Adds a new user with the provided user object.
	 * Generates a random password for the user and sends an email with the details.
	 *
	 * @param user The user object containing user details.
	 * @return The username of the created user.
	 */
	 @PostMapping("/user")
	 public String addUser(@RequestBody User user) {
	     // Generate random password
	     String password = generateRandomPassword();

	     // Set user details
	     user.setRole("USER");
	     user.setPassword(password);
	     
	     // Create the user in the database
	     this.userService.createUser(user);
	        
	     // Send email notification with user details
	     sendMail(user.getUsername(),user.getEmail(),password);

	     // Return the username of the newly added user
	     return user.getUsername();
	 }
	    
	    /**
	     * Sends an email with the user's username, email, and password.
	     *
	     * @param username The username of the user.
	     * @param email    The email of the user.
	     * @param password The generated password for the user.
	     */	    
	    public void sendMail(String username,String email, String password) {
	    	// Invoke the email service to send an email
	    	this.emailService.sendMail(username,email,password);
	    }
	    
	    /**
	     * Generates a random password.
	     *
	     * @return The generated random password.
	     */
	    private String generateRandomPassword() {
	    	 // Define character categories for password generation
	        String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        String smallLetters = "abcdefghijklmnopqrstuvwxyz";
	        String specialCharacters = "!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";
	        String numbers = "0123456789";

	        StringBuilder password = new StringBuilder();
	        Random random = new Random();

	        // Ensure at least one character from each category
	        password.append(getRandomCharacter(capitalLetters, random)); // Add a random capital letter
	        password.append(getRandomCharacter(smallLetters, random)); // Add a random small letter
	        password.append(getRandomCharacter(specialCharacters, random)); // Add a random special character
	        password.append(getRandomCharacter(numbers, random)); // Add a random number

	        // Generate remaining characters
	        for (int i = 4; i < 8; i++) {
	        	// Select a random category for the remaining characters
	            String category = getRandomCategory(random);
	            
	            // Get the characters for the selected category
	            String characters = getCategoryCharacters(category);

	            // Add a random character from the selected category to the password
	            password.append(getRandomCharacter(characters, random));
	        }

	        // return the generated password 
	        return password.toString();
	    }

	    /**
	     * Retrieves a random character from the given character set.
	     *
	     * @param characters The character set to choose from.
	     * @param random     The random object used for generating random indexes.
	     * @return A random character from the character set.
	     */
	    private char getRandomCharacter(String characters, Random random) {
	    	// Get a random index within the range of the characters string
	        int index = random.nextInt(characters.length());
	        
	        // Return the character at the randomly generated index
	        return characters.charAt(index);
	    }

	    /**
	     * Retrieves a random category.
	     *
	     * @param random The random object used for generating random indexes.
	     * @return A random category ("capital", "small", "special", "number").
	     */
	    private String getRandomCategory(Random random) {
	    	// Predefined list of categories
	        String[] categories = {"capital", "small", "special", "number"};
	        
	        // Generate a random index within the range of categories array
	        int index = random.nextInt(categories.length);
	        
	        // Return the category at the randomly generated index
	        return categories[index];
	    }

	    /**
	     * Retrieves the character set for the given category.
	     *
	     * @param category The category for which to retrieve the character set.
	     * @return The character set corresponding to the given category.
	     */
	    private String getCategoryCharacters(String category) {
	    	// Determine the category and return the corresponding characters
	        switch (category) {
	            case "capital":
	            	// Category: Capital letters (A-Z)
	                return "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	            case "small":
	            	 // Category: Small letters (a-z)
	                return "abcdefghijklmnopqrstuvwxyz";
	            case "special":
	            	 // Category: Special characters (!@#$%^&*()-_=+[{]}\|;:'",<.>/?)
	                return "!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";
	            case "number":
	            	// Category: Numbers (0-9)
	                return "0123456789";
	            default:
	            	// Invalid category, return an empty string
	                return "";
	        }
	    }

	    /**
	     * Retrieves a user by their ID.
	     *
	     * @param id The ID of the user to retrieve.
	     * @return The user with the specified ID.
	     * @throws UserNotFoundException if the user with the given ID does not exist.
	     */
	    @GetMapping("/user/{id}")
	    public User getUserById(@PathVariable("id") Long id) {
	    	// Find the user with the specified ID
	    	return this.userRepo.findById(id)
	    			// If the user is found, return it
	                .orElseThrow(() -> new UserNotFoundException(id));
	    }

	    /**
	     * Retrieves all users.
	     *
	     * @return A list of all users.
	     */
	    @GetMapping("/users")
	    public List<User> getAllUsers() {
	    	// Return the list of users as the response to the client
	        return this.userService.getAllUsers();
	    }

	    /**
	     * Updates a user with the provided user object.
	     *
	     * @param update The updated user object.
	     * @param id     The ID of the user to update.
	     * @return The updated user.
	     * @throws UserNotFoundException if the user with the given ID does not exist.
	     */
	    @PutMapping("/user/{id}")
	    public User updateUser(@RequestBody User update, @PathVariable("id") Long id) {
	    	 // Retrieve the user with the specified ID from the database
	    	return userRepo.findById(id)
	                .map(user -> {
	                	// Update the user with the provided data
	                    user.setUsername(update.getUsername());
	                    user.setEmail(update.getEmail());
	                    user.setPassword(update.getPassword());
	                    user.setPhoneNo(update.getPhoneNo());
	                    user.setRole("USER");
	                    user.setChecked(true);
	                    
	                    // Save and return the updated user
	                    return userRepo.save(user);
	                })
	                 // Throw UserNotFoundException if user with the specified ID is not found
	                .orElseThrow(() -> new UserNotFoundException(id));
	    }


	/**
	 * Deletes a user with the specified ID.
	 *
	 * @param userId The ID of the user to delete.
	 * @return A string indicating the success of the operation.
	 */
	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable("id") Long userId) {
		// Delete the user with the specified ID
		this.userService.deleteUser(userId);
		
		// Return a success message indicating that the user has been deleted
		return "deleted";
	}
	
	/**
	 * Disables a user with the specified ID.
	 *
	 * @param userId The ID of the user to disable.
	 * @return The updated user object with the checked field set to false.
	 */
	@PutMapping("/{id}")
	public User disableUser(@PathVariable("id") Long userId) {
		// Find the user by the provided userId
		Optional<User> user = this.userService.findByUserId(userId);
		User u1 = user.get();
		// Set the 'checked' property of the user to false to disable the user
		u1.setChecked(false);
		
		// Save the updated user in the repository
		this.userRepo.save(u1);
		
		// Return the updated user
		return u1;
	}
	
	/**
	 * Retrieves the count of active and total tests.
	 *
	 * @return The response entity containing the test count.
	 */
	 @GetMapping("/testCount")
	  public ResponseEntity<TestCountResponse> getQuizCount() {
		// Count the number of active tests
		 long activeCount = testRepo.countByActive(true);
		 
		// Count the total number of tests
		 long totalCount = testRepo.count();
		 
		// Create a TestCountResponse object with the counts
		 TestCountResponse response = new TestCountResponse(activeCount, totalCount);
		 
		 // Return the response with HTTP status 200 (OK)
		 return ResponseEntity.ok(response);
	  }
	 
	 /**
	  * Retrieves the count of total users and checked users.
	  *
	  * @return A map containing the user counts.
	  */
	 @GetMapping("/userCount")
	 public Map<String, Long> getUserCount() {
	   // Get the total count of users	 
	   long totalCount = userRepo.count();
	   
	   // Get the count of users who are checked (assuming there is a "checked" field in the user entity)
	   long checkedCount = userRepo.countByChecked(true);

	   // Create a map to store the count values
	   Map<String, Long> countMap = new HashMap<>();
	   
	   // Add the total count to the map with the key "totalCount"
	   countMap.put("totalCount", totalCount);
	   
	   // Add the checked count to the map with the key "checkedCount"
	   countMap.put("checkedCount", checkedCount);

	   // Return the count map containing the total count and checked count
	   return countMap;
	 }
	 
	 /**
	  * Retrieves the count of subjects.
	  *
	  * @return A map containing the subject count.
	  */
	 @GetMapping("/subjectCount")
	 public Map<String, Long> getSubjectCount() {
		// Retrieve the count of subjects from the repository
	   long subjectCount = subjectRepo.count();

	   // Create a new map to store the count
	   Map<String, Long> countMap = new HashMap<>();
	   
	   // Add the subject count to the map
	   countMap.put("subjectCount", subjectCount);

	   // Return the count map
	   return countMap;
	 }

	 /**
	  * Retrieves the count of questions.
	  *
	  * @return A map containing the question count.
	  */
	 @GetMapping("/questionCount")
	 public Map<String, Long> getQuestionCount() {
	   // Retrieve the count of questions from the question repository
	   long questionCount = questionRepo.count();

	   // Create a map to store the count of questions
	   Map<String, Long> countMap = new HashMap<>();
	   
	   // Add the question count to the map
	   countMap.put("questionCount", questionCount);
 
       // Return the count map
	   return countMap;
	 }
}