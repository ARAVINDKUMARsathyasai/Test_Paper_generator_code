package com.testpaper.service;

import java.util.List;
import java.util.Set;

import com.testpaper.model.Question;
import com.testpaper.model.Test;

/**
 * The QuestionService interface defines the operations that can be performed on questions.
*/
public interface QuestionService {
	
	/**
	 * Adds a new question to the system.
	 * 
	 * @param question The question to be added.
	 * @return The added question.
    */
	public Question addQuestion(Question question);
	
	/**
	 * Updates an existing question in the system.
	 * 
	 * @param question The question to be updated.
	 * @return The updated question.
    */
	public Question updateQuestion(Question question);
	
	/**
	 * Retrieves all questions from the system.
	 * 
	 * @return A set of all questions.
    */
	public Set<Question> getQuestions();
	
	/**
	 * Retrieves a question by its ID.
	 * 
	 * @param QuestionId The ID of the question.
	 * @return The question with the specified ID.
	 * @see Question
    */
	public Question getQuestion(Long QuestionId);
	
	/**
	 * Retrieves all questions belonging to a specific test.
	 * 
	 * @param test The test for which to retrieve the questions.
	 * @return A set of questions belonging to the specified test.
	 * @see Test
    */
	public Set<Question> getQuestionsOfTest(Test test);
	
	/**
	 * Deletes a question from the system.
	 * 
	 * @param quesId The ID of the question to be deleted.
    */
	public void deleteQuestion(Long quesId);

	/**
	 * Retrieves all questions from the system.
	 * 
	 * @return A list of all questions.
    */
	public List<Question> getAllQuestions();
}
