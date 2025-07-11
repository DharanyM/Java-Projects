package com.example.employeeapi.service;

import java.util.List;

import com.example.employeeapi.model.Employee;

public interface EmployeeService {
	Employee save(Employee emp);

	Employee update(Employee emp);

	void deleteById(Long id);

	List<Employee> getByName(String name);

	List<Employee> getByDepartment(String dept);

	List<Employee> saveAll(List<Employee> employees);

	Double getAverageSalary(String dept);
	 List<Employee> getAllEmployees();



}
