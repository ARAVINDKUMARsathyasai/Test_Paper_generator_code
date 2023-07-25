package com.testpaper.service;

import java.util.Set;

import com.testpaper.model.Subject;

/**
 * The SubjectService interface provides methods for managing subjects.
 * 
 * It defines operations such as adding a new subject, updating a subject,
 * retrieving subjects, getting subject details by subject ID, and deleting a subject.
*/
public interface SubjectService {
	
	/**
	 * Adds a new subject.
	 *
	 * @param subject The subject object to add.
	 * @return The added subject.
	 */
	public Subject addSubject(Subject subject);

	/**
	 * Updates a subject.
	 *
	 * @param subject The subject object to update.
	 * @return The updated subject.
	 */
	public Subject updateSubject(Subject subject);

	/**
	 * Retrieves all subjects.
	 *
	 * @return A set of all subjects.
	 */
	public Set<Subject> getSubjects();

	/**
	 * Retrieves the details of a subject by subject ID.
	 *
	 * @param subjectId The ID of the subject.
	 * @return The subject with the specified ID.
	 */
	public Subject getSubject(Long subjectId);

	/**
	 * Deletes a subject by subject ID.
	 *
	 * @param subjectId The ID of the subject to delete.
	 */
	public void deleteSubject(Long subjectId);
}
