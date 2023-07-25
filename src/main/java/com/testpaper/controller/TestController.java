package com.testpaper.controller;

import java.util.List;

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

import com.testpaper.model.Test;
import com.testpaper.model.Subject;
import com.testpaper.repository.TestRepository;
import com.testpaper.service.TestService;


/**
 * The TestController class handles HTTP requests related to tests.
 * It is responsible for adding, updating, disabling, retrieving, and deleting tests.
 * 
 * @see Test
 * @see Subject
 * @see TestRepository
 * @see TestService
 * 
 * @author TPG team
 * @since 21-06-2023
 * @version 17
*/
@RestController
@RequestMapping("/test")
@CrossOrigin("http://localhost:3000/")
public class TestController {
	
	/**
	 * The TestService handles test-related operations such as adding and updating tests.
	 * 
	 * @see TestService
    */
	@Autowired
	private TestService testService;
	
	/**
	 * The TestRepository is responsible for interacting with the database and performing CRUD operations on tests.
	 * 
	 * @see TestRepository
    */
	@Autowired
	private TestRepository testRepo;
	
	/**
	 * Adds a new test.
	 * 
	 * @param test The test object to add.
	 * @return The response entity containing the added test.
	 * @see Test
    */
	//add test service
	@PostMapping("/")
	public ResponseEntity<Test> addTest(@RequestBody Test test){
	    // Returns an HTTP 200 OK response with the added test entity
		return ResponseEntity.ok(this.testService.addTest(test));
	}
	
	/**
	 * Updates the data of a test.
	 * 
	 * @param test The test object to update.
	 * @return The response entity containing the updated test.
	 * @see Test
    */
	//update the required test data
	@PutMapping("/")
	public ResponseEntity<Test> updateTest(@RequestBody Test test){
		// Calls the updateTest() method of the testService to update the Test object
		return ResponseEntity.ok(this.testService.updateTest(test));
	}
	
	/**
	 * Disables or enables a test with the specified ID.
	 * If the test is disabled, it will not be available for users to take.
	 * If the test is enabled, it will be available for users to take.
	 * 
	 * @param testId The ID of the test to disable or enable.
	 * @return The response entity containing the updated test if found, or not found status if the test is not found.
	 * 
	 * @see Test
    */
	@PutMapping("/disable/{id}")
	public ResponseEntity<?> disableUser(@PathVariable("id") Long testId) {
		// Retrieve the test entity with the given ID from the repository
	    Test test = this.testRepo.findById(testId).orElse(null);

	    // Check if the test entity exists
	    if (test == null) {
	    	// Return a 'Not Found' response if the test is not found
	        return ResponseEntity.notFound().build();
	    }

	    // Get the number of questions and total questions for the test
	    int numberOfQuestions = Integer.parseInt(test.getNumberOFQuestions());
	    int totalQuestions = test.getQuestions().size();
	    
	    // Check if the number of questions exceeds the available questions
	    if (numberOfQuestions > totalQuestions) {
	    	// Return a 'Bad Request' response with an error message if the number of questions is too high
	        return ResponseEntity.badRequest().body("Number of questions exceeds the available questions");
	    }

	    // Toggle the active status of the test entity
	    test.setActive(!test.isActive());
	    
	    // Save the updated test entity in the repository
	    Test updatedTest = this.testRepo.save(test);

	    // Return an 'OK' response with the updated test entity
	    return ResponseEntity.ok(updatedTest);
	}
	
	/**
	 * Retrieves all tests.
	 * 
	 * @return The response entity containing a list of all tests.
	 * @see Test
    */
	//get all tests 
	@GetMapping("/")
	public ResponseEntity<?> Testes(){
		// Calls the getTestes() method from the testService to retrieve the test data.
		return ResponseEntity.ok(this.testService.getTestes());
	}
	
	/**
	 * Retrieves a test by its ID.
	 * 
	 * @param testId The ID of the test to retrieve.
	 * @return The test with the specified ID.
	 * 
	 * @see Test
    */
	@GetMapping("/{tId}")
	public Test test(@PathVariable("tId") Long testId) {
		return this.testService.getTest(testId);
	}
	
	/**
	 * Deletes a test with the specified ID.
	 * 
	 * @param testId The ID of the test to delete.
	 * @see Test
    */
	@DeleteMapping("/{tId}")
	public void delete(@PathVariable("tId") Long testId) {
		this.testService.deleteTest(testId); 
	}
	
	/**
	 * Retrieves all tests of a specific subject.
	 * 
	 * @param subId The ID of the subject to retrieve tests for.
	 * @return A list of tests belonging to the specified subject.
	 * @see Subject
    */
	@GetMapping("/subject/{subId}")
	public List<Test> getTestsOfCategory(@PathVariable("subId") Long subId){
		Subject sub = new Subject();
		sub.setSubId(subId);
		return this.testService.getTestsOfSubject(sub);
	}
}
