package com.example.employeeapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository repo;

	public EmployeeServiceImpl(EmployeeRepository repo) {
		this.repo = repo;
	}

	public Employee save(Employee emp) {
		return repo.save(emp);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public List<Employee> getByName(String name) {
		return repo.findByName(name);
	}

	public List<Employee> getByDepartment(String dept) {
		return repo.findAllByDepartment(dept);
	}

	@Override
	public Double getAverageSalary(String dept) {
		List<Employee> employees = repo.findAllByDepartment(dept);
		if (employees.isEmpty())
			return 0.0;

		return employees.stream().mapToDouble(Employee::getSalary).average().orElse(0.0);
	}

	@Override
	public List<Employee> saveAll(List<Employee> employees) {
		return repo.saveAll(employees);
	}

	@Override
	public Employee update(Employee emp) {
		// Check if employee with given ID exists
		Employee existing = repo.findById(emp.getId()).orElseThrow(() -> new RuntimeException("Employee not found"));

		// Update details
		existing.setName(emp.getName());
		existing.setDepartment(emp.getDepartment());
		existing.setSalary(emp.getSalary());

		// Save updated employee
		return repo.save(existing);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return repo.findAll();
	}

}
