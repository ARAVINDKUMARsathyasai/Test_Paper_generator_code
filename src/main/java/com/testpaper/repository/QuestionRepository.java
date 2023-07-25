package com.testpaper.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.testpaper.model.Question;
import com.testpaper.model.Test;

/**
 * The QuestionRepository interface is responsible for interacting with the database
 * and performing CRUD operations on the Question entity.
 * 
 * @see com.testpaper.model.Question
 * @see com.testpaper.model.Test
 * 
 * @author TPG team
 * @since 21-06-2023
 * @version 17
*/
@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

	/**
	 * Retrieves a set of questions associated with the specified test.
	 * 
	 * @param test The test for which to retrieve the questions.
	 * @return A set of questions associated with the test.
    */
   public Set<Question> findByTest(Test test);
}
