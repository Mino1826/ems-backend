package com.Mino.ems.repository;

import com.Mino.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
    boolean existsByEmail(String email);

    List<Employee> findByLastName(String lastName);


}