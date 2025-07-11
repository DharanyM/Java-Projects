package com.example.employeeapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private final EmployeeService service;

	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	@PostMapping
	public Employee save(@RequestBody Employee emp) {
		return service.save(emp);
	}

	@PostMapping("/bulk")
	public List<Employee> saveAll(@RequestBody List<Employee> employees) {
		return service.saveAll(employees);
	}

	@PutMapping
	public Employee update(@RequestBody Employee emp) {
		return service.update(emp);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteById(id);
	}

	@GetMapping("/by-name")
	public List<Employee> getByName(@RequestParam String name) {
		return service.getByName(name);
	}

	@GetMapping("/by-department")
	public List<Employee> getByDepartment(@RequestParam String dept) {
		return service.getByDepartment(dept);
	}

	@GetMapping("/avg-salary")
	public Double getAverageSalary(@RequestParam String dept) {
		return service.getAverageSalary(dept);
	}
}
