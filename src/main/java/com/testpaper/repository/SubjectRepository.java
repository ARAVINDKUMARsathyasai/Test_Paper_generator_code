package com.testpaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.testpaper.model.Subject;

/**
 * The SubjectRepository interface is a repository for managing Subject entities.
 * It provides methods for performing CRUD operations on Subject objects in the database.
 * 
 * @see Subject
 * @author TPG team
 * @since 21-06-2023
 * @version 17 
*/
@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long>{

}
