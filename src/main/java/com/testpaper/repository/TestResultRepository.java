package com.testpaper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testpaper.model.TestResult;

/**
 * The TestResultRepository interface extends the JpaRepository interface
 * and provides methods for performing CRUD operations on TestResult entities.
 * 
 * @see org.springframework.data.jpa.repository.JpaRepository
 * 
 * @author TPG team
 * @since 21-06-2023
 * @version 17
*/
public interface TestResultRepository extends JpaRepository<TestResult, Long> {

	/**
	 * Retrieves a list of TestResult objects by the user ID and test ID.
	 *
	 * @param userId The ID of the user.
	 * @param testId The ID of the test.
	 * @return A list of TestResult objects matching the user ID and test ID.
	 */
	public List<TestResult> findByUserIdAndTestId(Long userId, Long testId);

	/**
	 * Retrieves a list of TestResult objects by the test ID.
	 *
	 * @param testId The ID of the test.
	 * @return A list of TestResult objects matching the test ID.
	 */
	public List<TestResult> findByTestId(Long testId);
}

