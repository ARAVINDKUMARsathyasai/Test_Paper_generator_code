package com.testpaper.controller;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

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

import com.testpaper.model.Question;
import com.testpaper.model.QuestionOption;
import com.testpaper.service.QuestionService;
import com.testpaper.service.TestResultService;
import com.testpaper.service.TestService;
import com.testpaper.model.Test;
import com.testpaper.model.TestResult;
import com.testpaper.repository.QuestionRepository;

/**
 * The QuestionController class represents the RESTful API controller for managing questions.
 * It handles various HTTP requests related to creating, updating, retrieving, and deleting questions.
 * It also provides endpoints for fetching questions for a test and retrieving all questions of a test.
 * This controller works in conjunction with the QuestionService, TestService, and TestResultService.
 * 
 * @see com.testpaper.service.QuestionService
 * @see com.testpaper.service.TestService
 * @see com.testpaper.service.TestResultService
 * 
 * @author TPG team
 * @since 21-06-2023
 * @version 17
*/
@RestController
@RequestMapping("/question")
@CrossOrigin("http://localhost:3000/")
public class QuestionController {

	/**
	 * The QuestionService is responsible for handling operations related to questions,
	 * such as adding a question, updating a question, retrieving questions, and deleting questions.
    */
	@Autowired
	private QuestionService questionService;
	
	/**
	 * The QuestionRepository is responsible for interacting with the database
	 * and performing CRUD operations on the Question entity.
    */
	@Autowired
	private QuestionRepository qessRepo;
	
	/**
	 * The TestService is responsible for handling operations related to tests,
	 * such as retrieving a test, adding questions to a test, and retrieving questions of a test.
    */
	@Autowired
	private TestService testService;
	
	/**
	 * The TestResultService is responsible for handling operations related to test results,
	 * such as retrieving test results for a specific user and test.
    */
	@Autowired
    private TestResultService testResultService;
	
	/**
	 * Adds a new question to the system.
	 * 
	 * @param question The question to add.
	 * @return The ResponseEntity containing the added question.
    */
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question){
		 // Return the added question with a successful response
		return ResponseEntity.ok(this.questionService.addQuestion(question));
	}
	
	/**
	 * Updates the data of an existing question in the system.
	 * 
	 * @param question The question to update.
	 * @return The ResponseEntity containing the updated question.
    */
	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
		// Return the updated question with a successful response
		return ResponseEntity.ok(this.questionService.updateQuestion(question));
	}
	
	/**
	 * Retrieves all questions from the system.
	 * 
	 * @return The ResponseEntity containing the list of all questions.
    */
	@GetMapping("/")
    public ResponseEntity<List<Question>> getAllQuestions() {
		// Retrieve all questions from the questionService
        List<Question> allQuestions = this.questionService.getAllQuestions();
        // Return the list of questions in the response with HTTP status 200 (OK)
        return ResponseEntity.ok(allQuestions);
    }
		
	/**
	 * Retrieves all questions of a specific test from the system.
	 * 
	 * @param tId The ID of the test.
	 * @return The ResponseEntity containing the set of all questions for the test.
    */
	@GetMapping("/test/all/{tId}")
	public ResponseEntity<?> getAllQuestionsOfQuiz(@PathVariable("tId") Long tId){		

		// Create a Test object with the provided tId
		Test test = new Test();
		test.setqId(tId);
		
		// Retrieve the set of questions associated with the provided test
		Set<Question> questionOfQuiz = this.questionService.getQuestionsOfTest(test);
		
	    // Return the set of questions as the response
	   return ResponseEntity.ok(questionOfQuiz);
	}
	
	/**
	 * Retrieves the total number of questions for a specific test.
	 * 
	 * @param tId The ID of the test.
	 * @return The size of all questions for the test.
	 * @see #getAllQuestionsOfQuiz(Long)
    */
	public int getAllQuestionSize(Long tId){		
		
		// Create a Test object with the given test ID
		Test test = new Test();
		test.setqId(tId);
		
		// Retrieve the set of questions belonging to the test
		Set<Question> questionOfQuiz = this.questionService.getQuestionsOfTest(test);
		
		// Return the size of the question set, which represents the total number of questions in the test.
	   return questionOfQuiz.size();
	}
	
	/**
	 * Retrieves a single question by its ID.
	 * 
	 * @param Id The ID of the question.
	 * @return The question with the specified ID.
    */
	//get single question
	@GetMapping("/{quesId}")
	public Question getQuestion(@PathVariable("quesId") Long Id) {
		// Retrieve the question with the specified quesId using the questionService
		return this.questionService.getQuestion(Id);
	}
	
	/**
	 * Deletes a question with the specified ID.
	 * 
	 * @param Id The ID of the question to delete.
    */
	//delete question
	@DeleteMapping("/{quesId}")
	public void deleteQuestion(@PathVariable("quesId") Long Id) {
		// Call the questionService to delete the question with the provided Id
		this.questionService.deleteQuestion(Id);
	}
	
	
	/**
	 * Fetches the required number of questions randomly from the database for a specific test and user.
	 * 
	 * @param tId The ID of the test.
	 * @param userId The ID of the user.
	 * @return The ResponseEntity containing the randomly selected questions for the test and user.
    */
	@GetMapping("/test/{tId}/{userId}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("tId") Long tId, @PathVariable("userId") Long userId) {
	    // Retrieve the test based on the provided test ID
	    Test test = this.testService.getTest(tId);
	    // Get all the questions associated with the test
	    Set<Question> allQuestions = test.getQuestions();

	    // Get the questions already generated for the specific user and test
	    List<TestResult> userTestResults = testResultService.getTestResultsByUserIdAndTestId(userId, tId);

	    // Collect the IDs of the questions already generated, considering the last two test result question IDs
	    Set<Long> generatedQuestionIds = new HashSet<>();
	    int skippedTestResultsCount = Math.max(0, userTestResults.size() - 2); // Skip the last two test results
	    for (int i = skippedTestResultsCount; i < userTestResults.size(); i++) {
	        TestResult testResult = userTestResults.get(i);
	        generatedQuestionIds.addAll(testResult.getSelectedOptions().stream()
	                .map(QuestionOption::getQuestionId)
	                .collect(Collectors.toList()));
	    }

	    // Filter out already generated questions from all questions
	    Set<Question> availableQuestions = allQuestions.stream()
	            .filter(question -> !generatedQuestionIds.contains(question.getQuesId()))
	            .collect(Collectors.toSet());

	    // Convert the available questions set to a list and shuffle the order
	    List<Question> randomQuestions = new ArrayList<>(availableQuestions);
	    Collections.shuffle(randomQuestions);

	    // Determine the number of questions required for the test
	    int requiredQuestionCount = Integer.parseInt(test.getNumberOFQuestions());
	    // If there are more questions available than required, limit the list to the required number
	    if (randomQuestions.size() > requiredQuestionCount) {
	        randomQuestions = randomQuestions.subList(0, requiredQuestionCount);
	    }

	    // Return the randomly selected questions as the response
	    return ResponseEntity.ok(randomQuestions);
	}
}