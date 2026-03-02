package com.prudentstar.spring_boot_demo.repository;

import com.prudentstar.spring_boot_demo.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
}
