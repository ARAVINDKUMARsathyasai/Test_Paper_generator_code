package com.testpaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testpaper.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

   public User findByEmail(String email);

   public User findByUsername(String username);

   public User findByPhoneNo(String phoneNo);

  public long countByChecked(boolean b);
}
