package com.example.employeeapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.service.EmployeeService;

class EmployeeControllerTest {

	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeService employeeService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateEmployee() {
		Employee employee = new Employee(null, "Ravi", "IT", 50000);
		Employee saved = new Employee(1L, "Ravi", "IT", 50000);
		when(employeeService.save(employee)).thenReturn(saved);

		ResponseEntity<Employee> response = employeeController.createEmployee(employee);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Ravi", response.getBody().getName());
	}

	@Test
	void testGetAllEmployees() {
		List<Employee> list = Arrays.asList(new Employee(1L, "A", "HR", 40000), new Employee(2L, "B", "IT", 50000));

		when(employeeService.getAllEmployees()).thenReturn(list);

		ResponseEntity<List<Employee>> response = employeeController.getAllEmployees();
		assertEquals(2, response.getBody().size());
	}

	@Test
	void testGetByDepartment() {
		List<Employee> list = List.of(new Employee(1L, "Meena", "HR", 35000));
		when(employeeService.getEmployeesByDepartment("HR")).thenReturn(list);

		ResponseEntity<List<Employee>> response = employeeController.getByDepartment("HR");
		assertEquals(1, response.getBody().size());
	}

	@Test
	void testGetAverageSalary() {
		when(employeeService.getAverageSalaryByDepartment("HR")).thenReturn(42000.0);

		ResponseEntity<Double> response = employeeController.getAverageSalary("HR");
		assertEquals(42000.0, response.getBody());
	}

	@Test
	void testUpdateEmployee() {
		Employee updated = new Employee(1L, "Ravi", "HR", 46000);
		when(employeeService.updateEmployee(eq(1L), any(Employee.class))).thenReturn(updated);

		ResponseEntity<Employee> response = employeeController.updateEmployee(1L, updated);
		assertEquals(46000, response.getBody().getSalary());
	}

	@Test
	void testDeleteEmployee() {
		doNothing().when(employeeService).deleteEmployeeById(1L);

		ResponseEntity<String> response = employeeController.deleteEmployee(1L);
		assertEquals("Employee deleted with id : 1", response.getBody());
	}
}
