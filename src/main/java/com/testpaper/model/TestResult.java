package com.testpaper.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * The TestResult class represents the result of a test taken by a user.
 * It contains information such as the ID, test ID, user ID, number of correct answers,
 * number of wrong answers, number of unanswered questions, and the set of selected options.
 * 
 * @author TPG team
 * @since 21-06-2023
 * @version 17
*/
@Entity
@DynamicUpdate
@Table(name = "test_results")
public class TestResult {

	/**
	 * The ID of the test result.
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The ID of the test associated with the result.
     */
	@NotNull(message = "test id is mandatory")
    private Long testId;
    
    /**
     * The ID of the user who took the test.
     */
	@NotNull(message = "user id is mandatory")
    private Long userId;

    /**
     * The number of correct answers in the test.
     */

	@NotNull(message = "total correct answers")
    private int correctAnswers;
    
    /**
     * The number of wrong answers in the test.
     */
	@NotNull(message = "total wrong answers")
    private int wrongAnswers;
    
    /**
     * The number of unanswered questions in the test.
     */
	@NotNull(message = "total un-answered questions")
    private int unansweredQuestions;

    /**
     * The set of selected options by the user.
     */
    @ElementCollection
    private Set<QuestionOption> selectedOptions = new HashSet<>();

    /**
     * Retrieves the ID of the test result.
     *
     * @return The ID of the test result.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the test result.
     *
     * @param id The ID to set for the test result.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the ID of the test associated with the result.
     *
     * @return The ID of the test.
     */
    public Long getTestId() {
        return testId;
    }

    /**
     * Sets the ID of the test associated with the result.
     *
     * @param testId The ID of the test to set.
     */
    public void setTestId(Long testId) {
        this.testId = testId;
    }

    /**
     * Retrieves the ID of the user who took the test.
     *
     * @return The ID of the user.
     */
    public int getCorrectAnswers() {
        return correctAnswers;
    }

    /**
     * Sets the ID of the user who took the test.
     *
     * @param correctAnswers The correct answer of the user to set.
     */
    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    /**
     * Retrieves the number of correct answers in the test.
     *
     * @return The number of correct answers.
     */
    public int getWrongAnswers() {
        return wrongAnswers;
    }

    /**
     * Sets the number of correct answers in the test.
     *
     * @param wrongAnswers The number of wrong answers to set.
     */
    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    /**
     * Retrieves the number of wrong answers in the test.
     *
     * @return The number of wrong answers.
     */
    public int getUnansweredQuestions() {
        return unansweredQuestions;
    }

    /**
     * Sets the number of wrong answers in the test.
     *
     * @return userId returns the userId.
     */
    public Long getUserId() {
		return userId;
	}

    /**
     * Retrieves the number of unanswered questions in the test.
     *
     * @param userId is passed to the method
     */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Sets the number of unanswered questions in the test.
	 *
	 * @param unansweredQuestions The number of unanswered questions to set.
	 */
	public void setUnansweredQuestions(int unansweredQuestions) {
        this.unansweredQuestions = unansweredQuestions;
    }

	/**
	 * Retrieves the set of selected options by the user.
	 *
	 * @return The set of selected options.
	 */
    public Set<QuestionOption> getSelectedOptions() {
        return selectedOptions;
    }

    /**
     * Sets the set of selected options by the user.
     *
     * @param selectedOptions The set of selected options to set.
     */
    public void setSelectedOptions(Set<QuestionOption> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    /**
     * Returns a string representation of the TestResult object.
     *
     * @return A string representation of the TestResult object.
     */
	@Override
	public String toString() {
		return "TestResult [id=" + id + ", testId=" + testId + ", userId=" + userId + ", correctAnswers="
				+ correctAnswers + ", wrongAnswers=" + wrongAnswers + ", unansweredQuestions=" + unansweredQuestions
				+ ", selectedOptions=" + selectedOptions + "]";
	}
}