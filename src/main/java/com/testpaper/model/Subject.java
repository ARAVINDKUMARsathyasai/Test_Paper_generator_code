package com.testpaper.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.LinkedHashSet; 

/**
 * The Subject class represents a subject entity in the system.
 * It contains information such as the subject ID, title, description,
 * and a set of tests associated with the subject.
 * 
 * @author TPG team
 * @since 21-06-2023
 * @version 17
*/
@Entity
@DynamicUpdate
@Table(name="subject")
public class Subject {
	
	/**
	 * The ID of the subject.
    */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="subId")
	private Long subId;
	
	/**
	 * The title of the subject.
    */
	@Column(name="title")
	@NotNull(message = "Subject title is mandatory")
	private String title;
	
	/**
	 * The description of the subject.
    */
	@Column (name="discription")
	@NotNull(message = "Give short briefing about the subject")
	private String discription;

	/**
	 * The set of tests associated with the subject.
	 * 
	 * @see Test
    */
	@OneToMany(mappedBy="subject", fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Test> tests = new LinkedHashSet<>();
	
	/**
	 * Default constructor for the Subject class.
    */
	public Subject() {
		super();
	}

	/**
	 * Constructs a new Subject object with the specified parameters.
	 * 
	 * @param subId The ID of the subject.
	 * @param title The title of the subject.
	 * @param discription The description of the subject.
    */
	public Subject(Long subId, String title, String discription) {
		super();
		this.subId = subId;
		this.title = title;
		this.discription = discription;
	}

	/**
	 * Constructs a new Subject object with the specified parameters.
	 * 
	 * @param title The title of the subject.
	 * @param discription The description of the subject.
    */
	public Subject(String title, String discription) {
		super();
		this.title = title;
		this.discription = discription;
	}

	/**
	 * Retrieves the ID of the subject.
	 * 
	 * @return The ID of the subject.
    */
	public Long getSubId() {
		return subId;
	}

	/**
	 * Sets the ID of the subject.
	 * 
	 * @param subId The ID to set for the subject.
    */
	public void setSubId(Long subId) {
		this.subId = subId;
	}

	/**
	 * Retrieves the title of the subject.
	 * 
	 * @return The title of the subject.
    */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the subject.
	 * 
	 * @param title The title to set for the subject.
    */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Retrieves the description of the subject.
	 * 
	 * @return The description of the subject.
    */
	public String getDiscription() {
		return discription;
	}

	/**
	 * Sets the description of the subject.
	 * 
	 * @param discription The description to set for the subject.
    */
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	
	/**
	 * Retrieves the set of tests associated with the subject.
	 * 
	 * @return The set of tests associated with the subject.
	 * @see Test
    */
	public Set<Test> getTests() {
		return tests;
	}

	/**
	 * Sets the set of tests associated with the subject.
	 * 
	 * @param tests The set of tests to associate with the subject.
	 * @see Test
    */
	public void setTests(Set<Test> tests) {
		this.tests = tests;
	}

	/**
	 * Returns a string representation of the Subject object.
	 * 
	 * @return A string representation of the Subject object.
    */
	@Override
	public String toString() {
		return "Subject [subId=" + subId + ", title=" + title + ", discription=" + discription + "]";
	}
}
