package com.testpaper.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

/**
 * The QuestionOption class represents an option selected for a question.
 * It contains the question ID and the selected option.
 * @author TPG team
 * @since 21-06-2023
 * @version 17 
 * @see Question
*/
@Embeddable
public class QuestionOption {

	/**
	 * The ID of the question.
	 * 
	 * @see Question
    */
	@NotNull(message = "Question ID is mandatory")
    private Long questionId;
    
    /**
     * The selected option for the question.
     * 
     * @see Question
    */
    private String selectedOption;

    /**
     * Retrieves the ID of the question.
     * 
     * @return The ID of the question.
     * @see Question
    */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * Sets the ID of the question.
     * 
     * @param questionId The ID of the question.
     * @see Question
    */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * Retrieves the selected option for the question.
     * 
     * @return The selected option for the question.
     * 
     * @see Question
    */
    public String getSelectedOption() {
        return selectedOption;
    }

    /**
     * Sets the selected option for the question.
     * 
     * @param selectedOption The selected option for the question.
     * @see Question
    */
    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }
}
