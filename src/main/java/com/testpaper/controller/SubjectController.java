package com.testpaper.controller;

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

import com.testpaper.model.Subject;
import com.testpaper.service.SubjectService;

/**
 * The SubjectController class represents the RESTful API controller for managing subjects.
 * It handles various HTTP requests related to subjects, such as adding a subject, retrieving subjects,
 * updating a subject, and deleting a subject.
 * This controller interacts with the SubjectService to perform the necessary operations.
 * 
 * @author TPG team
 * @since 21-06-2023
 * @version 17
*/
@RestController
@RequestMapping("/subject")
@CrossOrigin("http://localhost:3000/")
public class SubjectController {
	
	/**
	 * The SubjectService used for subject-related operations.
	 * 
	 * @see com.testpaper.service.SubjectService
    */
	@Autowired
	private SubjectService subjectService;
	
	/**
	 * Adds a new subject with the provided subject object.
	 * 
	 * @param subject The subject object containing subject details.
	 * @return The ResponseEntity with the added subject.
    */
	@PostMapping("/")
	public ResponseEntity<Subject> addSubject(@RequestBody Subject subject){
		// Call the subjectService to add the subject
		Subject sub = this.subjectService.addSubject(subject);
		
		// Return the added subject as the response with HTTP status 200 OK
		return ResponseEntity.ok(sub);
	}
	
	/**
	 * Retrieves a subject by its ID.
	 * 
	 * @param subId The ID of the subject to retrieve.
	 * @return The subject with the specified ID.
	 * @see com.testpaper.model.Subject
    */
	@GetMapping("/{subjectId}")
	public Subject getSubject(@PathVariable("subjectId") Long subId) {
		// Retrieve the subject with the specified subjectId
		return this.subjectService.getSubject(subId);
	}
	
	/**
	 * Retrieves all subjects.
	 * 
	 * @return The ResponseEntity with the list of all subjects.
	 * @see com.testpaper.model.Subject
    */
	//get all categories
	@GetMapping("/")
	public ResponseEntity<?> getSubjects(){
		// Return the subjects in the response body with a success status code
		return ResponseEntity.ok(this.subjectService.getSubjects());
	}
	
	/**
	 * Updates a subject with the provided subject object.
	 * 
	 * @param subject The updated subject object.
	 * @return The updated subject.
	 * @see com.testpaper.model.Subject
    */
	//update subject
	@PutMapping("/")
	public Subject updateSubject(@RequestBody Subject subject) {
		//updates the data into database
		return this.subjectService.updateSubject(subject);
	}
	
	/**
	 * Deletes a subject with the specified ID.
	 * 
	 * @param subId The ID of the subject to delete.
	 * @see com.testpaper.model.Subject
    */
	//Delete subject
	@DeleteMapping("/{subjectId}")
	public void deleteSubject(@PathVariable("subjectId") Long subId) {
		//delete the data from the subject table
		this.subjectService.deleteSubject(subId);
	}
}
