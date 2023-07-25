package com.testpaper.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testpaper.model.TestResult;
import com.testpaper.repository.TestResultRepository;

/**
 * The TestResultService class provides implementation for managing test results.
 * It interacts with the TestResultRepository to perform CRUD operations on test results.
*/
@Service
public class TestResultService implements com.testpaper.service.TestResultService {

	/**
	 * The repository for accessing test results in the database.
	 */
	private final TestResultRepository testResultRepository;

	/**
	 * Constructs a new TestResultService with the specified TestResultRepository.
	 *
	 * @param testResultRepository The repository for accessing test results.
	 */
    @Autowired
    public TestResultService(TestResultRepository testResultRepository) {
        this.testResultRepository = testResultRepository;
    }

    /**
     * Retrieves a test result by its ID.
     *
     * @param id The ID of the test result.
     * @return The test result with the specified ID.
     * @throws NoSuchElementException if the test result with the given ID does not exist.
     */
    public TestResult getTestResultById(Long id) {
        Optional<TestResult> testResultOptional = testResultRepository.findById(id);
        return testResultOptional.orElseThrow(NoSuchElementException::new);
    }

    /**
     * Retrieves all test results associated with a specific user.
     *
     * @param testId The ID of the user.
     * @return A list of test results associated with the user.
     */
    public List<TestResult> getTestResultsByUserId(Long testId) {
        return testResultRepository.findByTestId(testId);
    }

    /**
     * Retrieves all test results associated with a specific user and test.
     *
     * @param userId The ID of the user.
     * @param testId The ID of the test.
     * @return A list of test results associated with the user and test.
     */
	@Override
	 public List<TestResult> getTestResultsByUserIdAndTestId(Long userId, Long testId) {
        return testResultRepository.findByUserIdAndTestId(userId, testId);
    }
}
