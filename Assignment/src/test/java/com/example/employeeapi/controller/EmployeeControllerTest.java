package com.example.employeeapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.service.EmployeeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    void testSave() {
        Employee employee = new Employee(null, "Ravi", "IT", 50000);
        Employee saved = new Employee(1L, "Ravi", "IT", 50000);

        when(employeeService.save(employee)).thenReturn(saved);

        Employee response = employeeController.save(employee);

        assertEquals("Ravi", response.getName());
        assertEquals(50000, response.getSalary());
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> list = Arrays.asList(
                new Employee(1L, "A", "HR", 40000),
                new Employee(2L, "B", "IT", 50000)
        );

        when(employeeService.getAllEmployees()).thenReturn(list);

        List<Employee> response = employeeController.getAllEmployees();

        assertEquals(2, response.size());
    }

    @Test
    void testGetByDepartment() {
        List<Employee> list = List.of(new Employee(1L, "Meena", "HR", 35000));
        when(employeeService.getByDepartment("HR")).thenReturn(list);

        List<Employee> response = employeeController.getByDepartment("HR");
        assertEquals(1, response.size());
    }

    @Test
    void testGetAverageSalary() {
        when(employeeService.getAverageSalary("HR")).thenReturn(42000.0);

        Double response = employeeController.getAverageSalary("HR");
        assertEquals(42000.0, response);
    }

    @Test
    void testUpdateEmployee() {
        Employee updated = new Employee(1L, "Ravi", "HR", 46000);
        when(employeeService.update(updated)).thenReturn(updated);

        Employee response = employeeController.update(updated);
        assertEquals(46000, response.getSalary());
    }

    @Test
    void testDeleteEmployee() {
        doNothing().when(employeeService).deleteById(1L);

        String response = employeeController.delete(1L);
        assertEquals("Employee deleted with id : 1", response);
    }

    @Test
    void testSaveAllEmployees() {
        List<Employee> employees = Arrays.asList(
                new Employee(null, "A", "IT", 50000),
                new Employee(null, "B", "HR", 60000)
        );

        List<Employee> saved = Arrays.asList(
                new Employee(1L, "A", "IT", 50000),
                new Employee(2L, "B", "HR", 60000)
        );

        when(employeeService.saveAll(employees)).thenReturn(saved);

        List<Employee> response = employeeController.saveAll(employees);
        assertEquals(2, response.size());
    }
}
