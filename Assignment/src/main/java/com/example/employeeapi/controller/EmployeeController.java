package com.example.employeeapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

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
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "Employee deleted with id : " + id;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
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
