package com.prudentstar.spring_boot_demo.service;

import com.prudentstar.spring_boot_demo.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);

    List<Employee> getAllEmployees();


    Employee getEmployeeById(String id);

    String deleteEmployeeById(String id);

    Employee update(Employee employee, String id);
}
