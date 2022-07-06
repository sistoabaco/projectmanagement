package com.webapp.projectmanagement.repository;

import com.webapp.projectmanagement.model.Employee;
import com.webapp.projectmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    @Query("select e from Employee  e where e.user = ?1")
    Employee findByEmployeeByUser(User user);
}
