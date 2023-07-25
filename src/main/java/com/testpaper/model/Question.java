package com.testpaper.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * The Question class represents a question entity in the system.
 * It contains information such as the question ID, question text, image (if any),
 * answer options, correct answer, and the test to which the question belongs.
 * 
 * @author TPG team
 * @since 21-06-2023
 * @version 17
*/
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="question")
public class Question {
	
	/**
	 * The ID of the question.
    */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="quesId")
	private Long quesId;
	
	/**
	 * The text of the question.
    */
	@Column(length=5000)
	@NotNull(message="Question is mandatory")
	private String question;
	
	/**
	 * The image associated with the question.
    */
	private String image;
	
	/**
	 * The first answer option for the question.
    */
	@NotNull(message = "Option1 is mandatory")
	private String option1;
	
	/**
	 * The second answer option for the question.
    */
	@NotNull(message = "Option2 is mandatory")
	private String option2;
	
	/**
	 * The third answer option for the question.
    */
	//this is optional for true or false type questions
	private String option3;
	
	/**
	 * The fourth answer option for the question.
    */
	//this is optional for true or false type questions
	private String option4;
	
	/**
	 * The correct answer for the question.
    */
	@NotNull(message = "answer is mandatory")
	private String answer;
	
	/**
	 * The test to which the question belongs.
	 * 
	 * @see Test
    */
	@NotNull(message = "Test id is mandatory")
	@ManyToOne(fetch=FetchType.EAGER)
	private Test test;

	/**
	 * Default constructor for the Question class.
    */
	public Question() {
		super();
	}

	/**
	 * Constructs a new Question object with the specified parameters.
	 * @param quesId The ID of the question.
	 * @param question The text of the question.    
	 * @param image The image associated with the question.    
	 * @param option1 The first answer option for the question.    
	 * @param option2 The second answer option for the question.    
	 * @param option3 The third answer option for the question.    
	 * @param option4 The fourth answer option for the question.    
	 * @param answer The correct answer for the question.    
	 * @param test The test to which the question belongs.    
	 * 
	 * @see Test
    */
	public Question(Long quesId, String question, String image, String option1, String option2, String option3,
			String option4, String answer, Test test) {
		super();
		this.quesId = quesId;
		this.question = question;
		this.image = image;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.answer = answer;
		this.test = test;
	}

	/**
	 * Constructs a new Question object with the specified parameters.
	 * 
	 * @param question The text of the question.    
	 * @param image The image associated with the question.    
	 * @param option1 The first answer option for the question.    
	 * @param option2 The second answer option for the question.    
	 * @param option3 The third answer option for the question.    
	 * @param option4 The fourth answer option for the question.    
	 * @param answer The correct answer for the question.    
	 * @param test The test to which the question belongs.    
	 * 
	 * @see Test
    */
	public Question(String question, String image, String option1, String option2, String option3, String option4,
			String answer, Test test) {
		super();
		this.question = question;
		this.image = image;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.answer = answer;
		this.test = test;
	}

	/**
	 * Retrieves the ID of the question.
	 * 
	 * @return The ID of the question.
    */
	public Long getQuesId() {
		return quesId;
	}

	/**
	 * Sets the ID of the question.
	 * 
	 * @param quesId The ID to set for the question.
    */
	public void setQuesId(Long quesId) {
		this.quesId = quesId;
	}

	/**
	 * Retrieves the text of the question.
	 * 
	 * @return The text of the question.
    */
	public String getQuestion() {
		return question;
	}

	/**
	 * Sets the text of the question.
	 * 
	 * @param question The text to set for the question.
    */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * Retrieves the image associated with the question.
	 * 
	 * @return The image associated with the question.
    */
	public String getImage() {
		return image;
	}

	/**
	 * Sets the image associated with the question.
	 * 
	 * @param image The image to set for the question.
    */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Retrieves the first answer option for the question.
	 * 
	 * @return The first answer option for the question.
    */
	public String getOption1() {
		return option1;
	}

	/**
	 * Sets the first answer option for the question.
	 * 
	 * @param option1 The first answer option to set for the question.
    */
	public void setOption1(String option1) {
		this.option1 = option1;
	}

	/**
	 * Retrieves the second answer option for the question.
	 * 
	 * @return The second answer option for the question.
    */
	public String getOption2() {
		return option2;
	}

	/**
	 * Sets the second answer option for the question.
	 * 
	 * @param option2 The second answer option to set for the question.
    */
	public void setOption2(String option2) {
		this.option2 = option2;
	}

	/**
	 * Retrieves the third answer option for the question.
	 * 
	 * @return The third answer option for the question.
    */
	public String getOption3() {
		return option3;
	}

	/**
	 * Sets the third answer option for the question.
	 * 
	 * @param option3 The third answer option to set for the question.
    */
	public void setOption3(String option3) {
		this.option3 = option3;
	}

	/**
	 * Retrieves the fourth answer option for the question.
	 * 
	 * @return The fourth answer option for the question.
    */
	public String getOption4() {
		return option4;
	}

	/**
	 * Sets the fourth answer option for the question.
	 * 
	 * @param option4 The fourth answer option to set for the question.
    */
	public void setOption4(String option4) {
		this.option4 = option4;
	}

	/**
	 * Retrieves the correct answer for the question.
	 * 
	 * @return The correct answer for the question.
    */
	public String getAnswer() {
		return answer;
	}

	/**
	 * Sets the correct answer for the question.
	 * 
	 * @param answer The correct answer to set for the question.
    */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * Retrieves the test to which the question belongs.
	 * 
	 * @return The test to which the question belongs.
	 * 
	 * @see Test
    */
	public Test getTest() {
		return test;
	}

	/**
	 * Sets the test to which the question belongs.
	 * 
	 * @param test The test to set for the question.
	 * 
	 * @see Test
    */
	public void setTest(Test test) {
		this.test = test;
	}

	/**
	 * Returns a string representation of the Question object.
	 * 
	 * @return A string representation of the Question object.
    */
	@Override
	public String toString() {
		return "Question [quesId=" + quesId + ", question=" + question + ", image=" + image + ", option1=" + option1
				+ ", option2=" + option2 + ", option3=" + option3 + ", option4=" + option4 + ", answer=" + answer
				+ ", test=" + test + "]";
	}
}
