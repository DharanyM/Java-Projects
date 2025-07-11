package com.example.employeeapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeeapi.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByName(String name);

	List<Employee> findByDepartment(String department);

	// ✅ Use Spring Data JPA method to get average salary
	List<Employee> findAllByDepartment(String department);
}
