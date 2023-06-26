package com.example.collections.controller;

import com.example.collections.model.Employee;
import com.example.collections.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/add")
    public Employee add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, int department, double salary) {
        return employeeService.add(firstName, lastName, department, salary);
    }

    @GetMapping(value = "remove")
    public Employee remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,int department, double salary) {
        return employeeService.remove(firstName, lastName,department, salary);
    }

    @GetMapping(value = "/find")
    public Employee find(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, int department, double salary) {
        return employeeService.find(firstName, lastName, department, salary);
    }

    @GetMapping(value = "/all")
    public List<Employee> all() {
        return employeeService.getAll();
    }
}
