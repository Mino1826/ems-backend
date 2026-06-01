package com.Mino.ems.service;

import com.Mino.ems.dto.EmployeeDto;
import com.Mino.ems.entity.Employee;
import com.Mino.ems.exception.DuplicateResourceException;
import com.Mino.ems.exception.ResourceNotFoundException;
import com.Mino.ems.mapper.EmployeeMapper;
import com.Mino.ems.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Employee> getEmployeesPagedAndSorted(
            int page,
            int size,
            String sortBy,
            String direction
    ) {
        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        return employeeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Transactional(readOnly = true)
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Transactional
    public Employee createEmployee(EmployeeDto employeeDto) {
        if (employeeRepository.existsByEmail(employeeDto.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = getEmployeeById(id);

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        return employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }
}