package com.webapp.projectmanagement.repository;

import com.webapp.projectmanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    @Query("select c from Category  c where c.description = ?1")
    Category findByName(String description);
}
