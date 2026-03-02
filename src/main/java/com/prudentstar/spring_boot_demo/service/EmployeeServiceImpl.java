package com.prudentstar.spring_boot_demo.service;


import com.prudentstar.spring_boot_demo.error.EmployeeNotFoundException;
import com.prudentstar.spring_boot_demo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service()
public class EmployeeServiceImpl implements EmployeeService {

    List<Employee> employeeList = new ArrayList<>();

    @Override
    public Employee save(Employee employee) {
        if (null == employee.getEmployeeId() || employee.getEmployeeId().isEmpty())
            employee.setEmployeeId(UUID.randomUUID().toString());
        employeeList.add(employee);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    @Override
    public Employee getEmployeeById(String id) {
        return employeeList
                .stream()
                .filter(employee -> employee.getEmployeeId().equals(id))
                .findFirst()
                //.orElseThrow(()->new RuntimeException("Employee not found with id "+id));
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id " + id));
    }

    @Override
    public String deleteEmployeeById(String id) {
        Employee employee = employeeList
                .stream()
                .filter(emp -> emp.getEmployeeId().equalsIgnoreCase(id))
                .findFirst()
                .get();
        employeeList.remove(employee);
        return "Employee id " + id + " successfully deleted";
    }

    @Override
    public Employee update(Employee employee, String id) {
        return null;
    }
}
