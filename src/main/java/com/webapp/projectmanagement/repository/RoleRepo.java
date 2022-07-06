package com.webapp.projectmanagement.repository;

import com.webapp.projectmanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    @Query("select r from Role r where r.description = ?1")
    Role findByRole(String role);
}
