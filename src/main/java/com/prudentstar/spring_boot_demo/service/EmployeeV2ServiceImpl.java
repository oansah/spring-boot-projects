package com.prudentstar.spring_boot_demo.service;

import com.prudentstar.spring_boot_demo.entity.EmployeeEntity;
import com.prudentstar.spring_boot_demo.error.EmployeeNotFoundException;
import com.prudentstar.spring_boot_demo.model.Employee;
import com.prudentstar.spring_boot_demo.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service()
public class EmployeeV2ServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        if (null == employee.getEmployeeId() || employee.getEmployeeId().isEmpty())
            employee.setEmployeeId(UUID.randomUUID().toString());

        EmployeeEntity entity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, entity);
        employeeRepository.save(entity);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        return employeeEntityList
                .stream()
                .map(employeeEntity -> {
                    Employee employee = new Employee();
                    BeanUtils.copyProperties(employeeEntity, employee);
                    return employee;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Employee getEmployeeById(String id) {
        /*EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);
        return employee;

        return employeeList
                .stream()
                .filter(employee -> employee.getEmployeeId().equals(id))
                .findFirst()
                //.orElseThrow(()->new RuntimeException("Employee not found with id "+id));
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id " + id));
       */

        return employeeRepository.findById(id)
                .map(employeeEntity -> {
                    Employee emp = new Employee();
                    BeanUtils.copyProperties(employeeEntity, emp);
                    return emp;
                })
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " not found"));

    }

    @Override
    public String deleteEmployeeById(String id) {
//        EmployeeEntity employeeEntity = employeeRepository.findById(id)
//                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " not found"));
//        employeeRepository.delete(employeeEntity);
//        return "Employee id " + id + " successfully deleted";

//      OR
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        }
        employeeRepository.deleteById(id);
        return "Employee id " + id + " successfully deleted";
    }

    @Override
    public Employee update(Employee employee, String id) {
        EmployeeEntity existingEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " not found"));
        // 2️⃣ Copy properties from DTO to entity (exclude id)
        BeanUtils.copyProperties(employee, existingEntity, "employeeId");
        // 3️⃣ Save updated entity
        EmployeeEntity savedEntity = employeeRepository.save(existingEntity);

        // 4️⃣ Convert back to DTO to return

        Employee employeeDto = new Employee();
        BeanUtils.copyProperties(savedEntity, employeeDto);
        return employeeDto;
    }
}
