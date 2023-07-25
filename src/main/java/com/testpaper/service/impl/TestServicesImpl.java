package com.testpaper.service.impl;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.testpaper.model.Subject;
import com.testpaper.model.Test;
import com.testpaper.repository.TestRepository;
import com.testpaper.service.TestService;

/**
 * The TestServicesImpl class implements the TestService interface and provides
 * the implementation for managing tests.
*/
@Service
public class TestServicesImpl implements TestService {
	
	/**
	 * The repository for accessing test in the database.
	 */
	@Autowired
	private TestRepository testRepo;

	/**
	 * Adds a new test to the database.
	 *
	 * @param test The test object to be added.
	 * @return The added test object.
	 */
	@Override
	public Test addTest(Test test) {
		return this.testRepo.save(test);
	}

	/**
	 * Updates an existing test in the database.
	 *
	 * @param test The test object to be updated.
	 * @return The updated test object.
	 */
	@Override
	public Test updateTest(Test test) {
		return this.testRepo.save(test);
	}

	/**
	 * Retrieves all the tests from the database.
	 *
	 * @return A set of all the tests.
	 * @see com.testpaper.repository.TestRepository#findAll()
	 */
	@Override
	public Set<Test> getTestes() {
		return new HashSet<>(this.testRepo.findAll());
	}

	/**
	 * Retrieves a specific test by its ID.
	 *
	 * @param testId The ID of the test to retrieve.
	 * @return The test with the specified ID.
	 * @see com.testpaper.repository.TestRepository#findById(Object)
	 */
	@Override
	public Test getTest(Long testId) {
		return this.testRepo.findById(testId).get();
	}

	/**
	 * Deletes a specific test by its ID.
	 *
	 * @param testId The ID of the test to delete.
	 * @see com.testpaper.repository.TestRepository#delete(Object)
	 */
	@Override
	public void deleteTest(Long testId) {
		Test test = new Test();
		test.setqId(testId);
		this.testRepo.delete(test);
	}

	/**
	 * Retrieves the count of quizzes/tests in the database.
	 *
	 * @return The count of quizzes/tests.
	 * @see com.testpaper.repository.TestRepository#count()
	 */
	@Override
	public int getQuizCount() {
		// TODO Auto-generated method stub
		return (int)testRepo.count();
	}

	/**
	 * Retrieves all the tests associated with a specific subject.
	 *
	 * @param sub The subject for which to retrieve the tests.
	 * @return A list of tests associated with the subject.
	 * @see com.testpaper.repository.TestRepository#findBysubject(Subject)
	 */
	@Override
	public List<Test> getTestsOfSubject(Subject sub) {
		// TODO Auto-generated method stub
		return this.testRepo.findBysubject(sub);
	}
}
