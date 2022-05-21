package com.project.ams.data.repository;

import com.project.ams.data.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//    @Query("SELECT p from Employee p WHERE "
//            + "CONCAT(p.firstName, p.department)"
//            + "LIKE %?1%")
//    @Query("SELECT p from Employee p WHERE p.firstName LIKE %?1%")
//    public List<Employee> findAll(String department);
//    @Query("SELECT p from Employee p WHERE p.department = com.project.ams.data.models.Department.MARKETING")
//    public List<Employee> findAll(String department);
    @Query("SELECT p from Employee p WHERE p.department = " +
            "COALESCE(:#{T(com.project.ams.data.models.Department).#department},p.department)")
    public List<Employee> findAll(String department);
}