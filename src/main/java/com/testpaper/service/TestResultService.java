package com.testpaper.service;

import java.util.List;

import com.testpaper.model.TestResult;

/**
 * The TestResultService interface defines the contract for performing operations related to test results.
 * It provides methods for retrieving test results by ID, user ID, and user ID and test ID.
 * 
 * @see TestResult
*/
public interface TestResultService {
	
	/**
	 * Retrieves a test result by its ID.
	 * 
	 * @param id The ID of the test result.
	 * @return The test result with the specified ID.
	 * @see TestResult
    */
	public TestResult getTestResultById(Long id);

	/**
	 * Retrieves a list of test results for a specific user.
	 * 
	 * @param userId The ID of the user.
	 * @return A list of test results associated with the specified user.
	 * @see TestResult
    */
	public List<TestResult> getTestResultsByUserId(Long userId);

	/**
	 * Retrieves a list of test results for a specific user and test.
	 * 
	 * @param userId The ID of the user.
	 * @param testId The ID of the test.
	 * @return A list of test results associated with the specified user and test.
	 * @see TestResult
    */
	public List<TestResult> getTestResultsByUserIdAndTestId(Long userId, Long testId);
}
