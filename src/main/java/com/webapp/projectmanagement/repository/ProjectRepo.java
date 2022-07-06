package com.webapp.projectmanagement.repository;

import com.webapp.projectmanagement.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer> {
    @Query("select p from Project p where p.projectName = ?1")
    Project findByName(String name);
}
