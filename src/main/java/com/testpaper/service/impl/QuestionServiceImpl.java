package com.testpaper.service.impl;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testpaper.model.Question;
import com.testpaper.model.Test;
import com.testpaper.repository.QuestionRepository;
import com.testpaper.service.QuestionService;

/**
 * The QuestionServiceImpl class implements the QuestionService interface
 * and provides the implementation for managing questions.
*/
@Service
public class QuestionServiceImpl implements QuestionService {
	
	/**
	 * to connect the object of the QuestionRepository for creating the CRUD operations 
	 */
	@Autowired
	private QuestionRepository questionRepo;

	/**
	 * Adds a new question.
	 *
	 * @param question The question to add.
	 * @return The added question.
	 */
	@Override
	public Question addQuestion(Question question) {
		return this.questionRepo.save(question);
	}

	/**
	 * Updates an existing question.
	 *
	 * @param question The question to update.
	 * @return The updated question.
	 */
	@Override
	public Question updateQuestion(Question question) {
		return this.questionRepo.save(question);
	}

	/**
	 * Retrieves a set of all questions.
	 *
	 * @return A set of all questions.
	 */
	@Override
	public Set<Question> getQuestions() {
		return new HashSet<>(this.questionRepo.findAll());
	}

	/**
	 * Retrieves a question by its ID.
	 *
	 * @param QuestionId The ID of the question to retrieve.
	 * @return The question with the specified ID.
	 */
	@Override
	public Question getQuestion(Long QuestionId) {
		return this.questionRepo.findById(QuestionId).get();
	}

	/**
	 * Retrieves a set of questions belonging to a specific test.
	 *
	 * @param test The test to retrieve questions for.
	 * @return A set of questions belonging to the specified test.
	 */
	@Override
	public Set<Question> getQuestionsOfTest(Test test) {
		return this.questionRepo.findByTest(test);
	}

	/**
	 * Deletes a question by its ID.
	 *
	 * @param quesId The ID of the question to delete.
	 */
	@Override
	public void deleteQuestion(Long quesId) {
		Question question = new Question();
		question.setQuesId(quesId);
		this.questionRepo.delete(question);
	}

	/**
	 * Retrieves a list of all questions.
	 *
	 * @return A list of all questions.
	 */
	@Override
	public List<Question> getAllQuestions() {
		return questionRepo.findAll();
	}
}
