package com.example.collections.service;

import com.example.collections.exception.EmployeeAlreadyAddedException;
import com.example.collections.exception.EmployeeNotFoundException;
import com.example.collections.exception.EmployeeStorageIsFullException;
import com.example.collections.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>(MAX_SIZE);
    private static final int MAX_SIZE = 5;

    public EmployeeService() {
        Employee employee1 = new Employee("Danil", "Bogomolov", 1, getScale());
        Employee employee2 = new Employee("Ivan", "Ivanov", 2, getScale());
        Employee employee3 = new Employee("Alina", "Pogodina", 2, getScale());
        Employee employee4 = new Employee("Kostya", "Tamashenko", 3, getScale());
        Employee employee5 = new Employee("Denis", "Glazunov", 3,getScale());
        employees.put(createKey(employee1.getFirstName(), employee1.getLastName()), employee1);
        employees.put(createKey(employee2.getFirstName(), employee2.getLastName()), employee2);
        employees.put(createKey(employee3.getFirstName(), employee3.getLastName()), employee3);
        employees.put(createKey(employee4.getFirstName(), employee4.getLastName()), employee4);
        employees.put(createKey(employee5.getFirstName(), employee5.getLastName()), employee5);
    }

    public Employee add(String firstName, String lastName, int department, double salary) {
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employeeToAdd = new Employee(firstName, lastName, department, salary);
        if (employees.containsKey(createKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(createKey(firstName, lastName), employeeToAdd);
        return employeeToAdd;
    }

    public Employee remove(String firstName, String lastName, int department, double salary) {
        Employee employeeToRemove = new Employee(firstName, lastName, department, salary);
        if (!employees.containsKey(createKey(firstName, lastName))) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(createKey(firstName, lastName));
        return employeeToRemove;
    }

    public Employee find(String firstName, String lastName, int department, double salary) {
        Employee employeeToFind = new Employee(firstName, lastName, department, salary);
        if (!employees.containsKey(createKey(firstName, lastName))) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(createKey(firstName, lastName));
    }

    public List<Employee> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(employees.values()));
    }

    public static String createKey(String firstName, String lastName) {
        return firstName + lastName;
    }
    public static double getScale() {
        double start = 10000;
        double end = 20000;
        return (double) Math.round((Math.random() * (end - start)) + start);
    }
}
