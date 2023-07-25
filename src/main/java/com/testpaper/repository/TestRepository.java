package com.testpaper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testpaper.model.Subject;
import com.testpaper.model.Test;

/**
 * The TestRepository interface is responsible for performing database operations
 * related to the Test entity.
 * 
 * @see Test
 * @author TPG team
 * @since 21-06-2023
 * @version 17
*/
@Repository
public interface TestRepository extends JpaRepository<Test,Long>{

	/**
	 * Counts the number of active tests.
	 * 
	 * @param b The flag indicating the status of the test (active or not).
	 * @return returns the count of the active tests
    */
	public long countByActive(boolean b);
	
	/**
	 * Retrieves a list of tests based on the specified subject.
	 * 
	 * @param subject The subject of the tests.
	 * @return A list of tests associated with the specified subject.
	 * @see Subject
    */
	public List<Test> findBysubject(Subject subject);

}
