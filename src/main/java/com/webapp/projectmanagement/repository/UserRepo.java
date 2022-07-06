package com.webapp.projectmanagement.repository;

import com.webapp.projectmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.username = ?1")
    User findByUsername(String user);
}
