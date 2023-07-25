package com.testpaper.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.testpaper.model.TestResult;
import com.testpaper.repository.TestResultRepository;
import com.testpaper.service.TestResultService;

/**
 * The TestResultController class represents the RESTful API controller for managing test results.
 * It provides endpoints for submitting test results, retrieving test results by ID, retrieving test results by user ID,
 * and retrieving test results by user ID and test ID.
 * 
 * @author TPG team
 * @since 21-06-2023
 * @version 17
*/
@RestController
@RequestMapping("/test-results")
@CrossOrigin("http://localhost:3000/")
public class TestResultController {

	/**
	 * The repository for accessing and performing CRUD operations on test results.
    */
    private final TestResultRepository testResultRepository;
    
    /**
     * The service for handling test result-related operations.
    */
    @Autowired
    private TestResultService testResultService;

    /**
     * Constructs a new TestResultController with the specified TestResultRepository.
     * 
     * @param testResultRepository The repository for test results.
    */
    @Autowired
    public TestResultController(TestResultRepository testResultRepository) {
        this.testResultRepository = testResultRepository;
    }

    /**
     * Submits a test result by saving it to the database.
     * 
     * @param testResult The test result to submit.
     * @return The ResponseEntity containing the saved test result.
     * @see TestResult
    */
    @PostMapping
    public ResponseEntity<TestResult> submitTestResult(@RequestBody TestResult testResult) {
        TestResult savedTestResult = testResultRepository.save(testResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTestResult);
    }
    
    /**
     * Retrieves a test result by its ID.
     * 
     * @param id The ID of the test result to retrieve.
     * @return The ResponseEntity containing the retrieved test result.
     *         Returns ResponseEntity.notFound() if the test result does not exist.
     * @see TestResult
    */
    @GetMapping("/{id}")
    public ResponseEntity<TestResult> getTestResultById(@PathVariable("id") Long id) {
      try {
        TestResult testResult = testResultService.getTestResultById(id);
        return ResponseEntity.ok(testResult);
      } catch (NoSuchElementException e) {
        return ResponseEntity.notFound().build();
      }
    }
    
    /**
     * Retrieves all test results for a specific user by their user ID.
     * 
     * @param userId The ID of the user.
     * @return The ResponseEntity containing the retrieved test results.
     *             Returns ResponseEntity.notFound() if the user does not have any test results.
     * @see TestResult
    */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TestResult>> getTestResultsByUserId(@PathVariable("userId") Long userId) {
      try {
        List<TestResult> testResults = testResultService.getTestResultsByUserId(userId);
        return ResponseEntity.ok(testResults);
      } catch (NoSuchElementException e) {
        return ResponseEntity.notFound().build();
      }
    }
    
    /**
     * Retrieves all test results for a specific user and test combination
     * by their user ID and test ID.
     * 
     * @param userId The ID of the user.
     * @param testId The ID of the test.
     * @return The ResponseEntity containing the retrieved test results.
     * 		       Returns ResponseEntity.notFound() if the user and test combination does not have any test results.
     * @see TestResult
    */
    @GetMapping("/user/{userId}/test/{testId}")
    public ResponseEntity<List<TestResult>> getTestResultsByUserIdAndTestId(
            @PathVariable("userId") Long userId,
            @PathVariable("testId") Long testId) {
        try {
            List<TestResult> testResults = testResultService.getTestResultsByUserIdAndTestId(userId, testId);
            return ResponseEntity.ok(testResults);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}