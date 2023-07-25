package com.testpaper.service.impl;

import java.util.Set;
import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testpaper.model.Subject;
import com.testpaper.repository.SubjectRepository;
import com.testpaper.service.SubjectService;

/**
 * The SubjectServiceImpl class implements the SubjectService interface and provides
 * the implementation for managing subjects in the system.
*/
@Service
public class SubjectServiceImpl implements SubjectService {
	/**
	 * The SubjectRepository is responsible for interacting with the database
	 * and performing CRUD operations on the Subject entity.
	 *
	 * @see com.testpaper.repository.SubjectRepository
	 */
	@Autowired
	private SubjectRepository subjectRepo;

	/**
	 * Adds a new subject to the system.
	 *
	 * @param subject The subject to add.
	 * @return The added subject.
	 */
	@Override
	public Subject addSubject(Subject subject) {
	    return this.subjectRepo.save(subject);
	}

	/**
	 * Updates an existing subject in the system.
	 *
	 * @param subject The subject to update.
	 * @return The updated subject.
	 */
	@Override
	public Subject updateSubject(Subject subject) {
	    return this.subjectRepo.save(subject);
	}

	/**
	 * Retrieves all subjects from the system.
	 *
	 * @return A set of all subjects.
	 */
	@Override
	public Set<Subject> getSubjects() {
	    return new LinkedHashSet<>(this.subjectRepo.findAll());
	}

	/**
	 * Retrieves a subject by its ID.
	 *
	 * @param subjectId The ID of the subject to retrieve.
	 * @return The subject with the specified ID.
	 */
	@Override
	public Subject getSubject(Long subjectId) {
	    return this.subjectRepo.findById(subjectId).get();
	}

	/**
	 * Deletes a subject from the system.
	 *
	 * @param subjectId The ID of the subject to delete.
	 */
	@Override
	public void deleteSubject(Long subjectId) {
	    Subject subject = new Subject();
	    subject.setSubId(subjectId);
	    this.subjectRepo.delete(subject);
	}
}
