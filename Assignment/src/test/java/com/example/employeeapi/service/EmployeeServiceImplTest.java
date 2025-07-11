package com.example.employeeapi.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.repository.EmployeeRepository;

class EmployeeServiceImplTest {

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@Mock
	private EmployeeRepository employeeRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSaveEmployee() {
		Employee employee = new Employee(null, "Ravi", "IT", 50000);
		Employee saved = new Employee(1L, "Ravi", "IT", 50000);

		when(employeeRepository.save(employee)).thenReturn(saved);

		Employee result = employeeService.saveEmployee(employee);
		assertEquals("Ravi", result.getName());
	}

	@Test
	void testGetAllEmployees() {
		List<Employee> list = Arrays.asList(new Employee(1L, "A", "HR", 40000), new Employee(2L, "B", "IT", 50000));

		when(employeeRepository.findAll()).thenReturn(list);

		List<Employee> result = employeeService.getAllEmployees();
		assertEquals(2, result.size());
	}

	@Test
	void testGetByDepartment() {
		List<Employee> employees = List.of(new Employee(1L, "Meena", "HR", 35000));
		when(employeeRepository.findByDepartment("HR")).thenReturn(employees);

		List<Employee> result = employeeService.getEmployeesByDepartment("HR");
		assertEquals(1, result.size());
	}

	@Test
	void testGetAverageSalary() {
		List<Employee> employees = Arrays.asList(new Employee(1L, "A", "HR", 40000),
				new Employee(2L, "B", "HR", 50000));

		when(employeeRepository.findByDepartment("HR")).thenReturn(employees);

		double avg = employeeService.getAverageSalaryByDepartment("HR");
		assertEquals(45000.0, avg);
	}

	@Test
	void testUpdateEmployee() {
		Employee oldEmp = new Employee(1L, "Old", "IT", 40000);
		Employee newEmp = new Employee(1L, "New", "HR", 60000);

		when(employeeRepository.findById(1L)).thenReturn(Optional.of(oldEmp));
		when(employeeRepository.save(any(Employee.class))).thenReturn(newEmp);

		Employee result = employeeService.updateEmployee(1L, newEmp);
		assertEquals("New", result.getName());
		assertEquals(60000, result.getSalary());
	}

	@Test
	void testDeleteEmployee() {
		doNothing().when(employeeRepository).deleteById(1L);
		assertDoesNotThrow(() -> employeeService.deleteEmployeeById(1L));
		verify(employeeRepository, times(1)).deleteById(1L);
	}
}
