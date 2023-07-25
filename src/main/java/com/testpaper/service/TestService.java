package com.testpaper.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.testpaper.model.Subject;
import com.testpaper.model.Test;

/**
 * The TestService interface defines the contract for performing operations related to tests.
 * It provides methods for adding a test, updating a test, retrieving tests, and deleting tests.
 * Additionally, it includes methods for retrieving the count of quizzes and getting tests of a specific subject.
 * 
 * @see com.testpaper.model.Subject
 * @see com.testpaper.model.Test
*/
public interface TestService {
	
	/**
	 * Adds a new test.
	 * 
	 * @param test The test object to be added.
	 * @return The added test.
    */
	public Test addTest(Test test);
	
	/**
	 * Updates an existing test.
	 * 
	 * @param test The test object to be updated.
	 * @return The updated test.
    */
	public Test updateTest(Test test);
	
	/**
	 * Retrieves a set of all tests.
	 * 
	 * @return A set containing all tests.
    */
	public Set<Test> getTestes();
	
	/**
	 * Retrieves a test by its ID.
	 * 
	 * @param testId The ID of the test to retrieve.
	 * @return The test with the specified ID.
    */
	public Test getTest(Long testId);
	
	/**
	 * Deletes a test by its ID.
	 * @param testId The ID of the test to delete.
    */
	public void deleteTest(Long testId);

	/**
	 * Retrieves the count of quizzes.
	 * @return The count of quizzes.
    */
	public int getQuizCount();

	/**
	 * Retrieves a list of tests for a specific subject.
	 * 
	 * @param sub The sub for which to retrieve the tests.
	 * @return A list of tests belonging to the specified subject.
	 * @see com.testpaper.model.Subject
    */
	public List<Test> getTestsOfSubject(Subject sub);

}
