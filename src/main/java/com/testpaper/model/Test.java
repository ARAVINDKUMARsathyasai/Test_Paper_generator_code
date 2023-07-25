package com.testpaper.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;

/**
 * The Test class represents a test entity in the system.
 * It contains information such as the test ID, title, description, maximum marks,
 * number of questions, active status, subject, and questions associated with the test.
 * 
 * @author TPG team
 * @since 21-06-2023
 * @version 17
*/
@Entity
@DynamicUpdate
@Table(name="test")
public class Test {

	/**
	 * The ID of the test.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tId")
	private Long tId;
	
	/**
	 * The title of the test.
	 */
	@Column(name="title")
	@NotNull(message = "Give some name to this test")
	private String title;
	
	/**
	 * The description of the test.
	 */
	@Column(name="disc")
	@NotNull(message = "Give some short discription about the test")
	private String disc;
	
	/**
	 * The maximum marks of the test.
	 */
	@Column(name="maxMarks")
	@NotNull(message = "Enter the maximum marks planned")
	@Size(min=1)
	private String maxMarks;
	
	/**
	 * The number of questions in the test.
	 */
	@Column(name="noOfQs")
	@NotNull(message = "Enter the number of questions planned")
	@Size(min=1)
	private String numberOFQuestions;
	
	/**
	 * The active status of the test.
	 */
	@Column(name="active")
	@NotNull
	private boolean active = false;
	
	/**
	 * The subject associated with the test.
	 * 
	 * @see Subject
	 */
	@ManyToOne(fetch= FetchType.EAGER)
	@NotNull
	private Subject subject;
	
	/**
	 * The set of questions associated with the test.
	 * 
	 * @see Question
	 */
	@OneToMany(mappedBy="test", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonIgnore
	@NotNull
	private Set<Question> questions = new HashSet<>();
	
	/**
	 * Constructs a new Test object with the specified parameters.
	 *
	 * @param title             The title of the test.
	 * @param disc              The description of the test.
	 * @param maxMarks          The maximum marks of the test.
	 * @param numberOFQuestions The number of questions in the test.
	 * @param active            The active status of the test.
	 * @param subject           The subject associated with the test.
	 */
	public Test(String title, String disc, String maxMarks, String numberOFQuestions, boolean active, Subject subject) {
		super();
		this.title = title;
		this.disc = disc;
		this.maxMarks = maxMarks;
		this.numberOFQuestions = numberOFQuestions;
		this.active = active;
		this.subject = subject;
	}

	/**
	 * Constructs a new Test object with the specified parameters.
	 *
	 * @param qId               The ID of the test.
	 * @param title             The title of the test.
	 * @param disc              The description of the test.
	 * @param maxMarks          The maximum marks of the test.
	 * @param numberOFQuestions The number of questions in the test.
	 * @param active            The active status of the test.
	 * @param subject           The subject associated with the test.
	 * @param questions         The set of questions associated with the test.
	 */
	public Test(Long qId, String title, String disc, String maxMarks, String numberOFQuestions, boolean active,
			Subject subject, Set<Question> questions) {
		super();
		this.tId = qId;
		this.title = title;
		this.disc = disc;
		this.maxMarks = maxMarks;
		this.numberOFQuestions = numberOFQuestions;
		this.active = active;
		this.subject = subject;
		this.questions = questions;
	}

	/**
	 * Default constructor for the Test class.
	 */
	public Test() {
		super();
	}

	/**
	 * Retrieves the ID of the test.
	 *
	 * @return The ID of the test.
	 */
	public Long getqId() {
		return tId;
	}

	/**
	 * Sets the ID of the test.
	 *
	 * @param qId The ID to set for the test.
	 */
	public void setqId(Long qId) {
		this.tId = qId;
	}

	/**
	 * Retrieves the title of the test.
	 *
	 * @return The title of the test.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the test.
	 *
	 * @param title The title to set for the test.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Retrieves the description of the test.
	 *
	 * @return The description of the test.
	 */
	public String getDisc() {
		return disc;
	}

	/**
	 * Sets the description of the test.
	 *
	 * @param disc The description to set for the test.
	 */
	public void setDisc(String disc) {
		this.disc = disc;
	}

	/**
	 * Retrieves the maximum marks of the test.
	 *
	 * @return The maximum marks of the test.
	 */
	public String getMaxMarks() {
		return maxMarks;
	}

	/**
	 * Sets the maximum marks of the test.
	 *
	 * @param maxMarks The maximum marks to set for the test.
	 */
	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}

	/**
	 * Retrieves the number of questions in the test.
	 *
	 * @return The number of questions in the test.
	 */
	public String getNumberOFQuestions() {
		return numberOFQuestions;
	}

	/**
	 * Sets the number of questions in the test.
	 *
	 * @param numberOFQuestions The number of questions to set for the test.
	 */
	public void setNumberOFQuestions(String numberOFQuestions) {
		this.numberOFQuestions = numberOFQuestions;
	}

	/**
	 * Retrieves the active status of the test.
	 *
	 * @return The active status of the test.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the active status of the test.
	 *
	 * @param active The active status to set for the test.
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Retrieves the subject associated with the test.
	 *
	 * @return The subject associated with the test.
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * Sets the subject associated with the test.
	 *
	 * @param subject The subject to set for the test.
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	/**
	 * Retrieves the set of questions associated with the test.
	 *
	 * @return The set of questions associated with the test.
	 */
	public Set<Question> getQuestions() {
		return questions;
	}

	/**
	 * Sets the set of questions associated with the test.
	 *
	 * @param questions The set of questions to set for the test.
	 */
	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	/**
	 * Returns a string representation of the Test object.
	 *
	 * @return A string representation of the Test object.
	 */
	@Override
	public String toString() {
		return "Test [qId=" + tId + ", title=" + title + ", disc=" + disc + ", maxMarks=" + maxMarks
				+ ", numberOFQuestions=" + numberOFQuestions + ", active=" + active + ", subject=" + subject
				+ ", questions=" + questions + "]";
	}
}
