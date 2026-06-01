package com.Mino.ems.controller;

import com.Mino.ems.dto.EmployeeDto;
import com.Mino.ems.dto.EmployeeResponseDto;
import com.Mino.ems.entity.Employee;
import com.Mino.ems.mapper.EmployeeMapper;
import com.Mino.ems.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<Employee>> getEmployeesPagedAndSorted(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Page<Employee> employees = employeeService.getEmployeesPagedAndSorted(
                page,
                size,
                sortBy,
                direction
        );

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search")
    public ResponseEntity<Employee> getEmployeeByEmail(
            @RequestParam String email
    ) {
        Employee employee = employeeService.getEmployeeByEmail(email);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(
            @PathVariable Long id
    ) {

        Employee employee =
                employeeService.getEmployeeById(id);

        EmployeeResponseDto response =
                EmployeeMapper.mapToEmployeeResponseDto(employee);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(
            @Valid @RequestBody EmployeeDto employeeDto
    ) {
        Employee savedEmployee = employeeService.createEmployee(employeeDto);
        return ResponseEntity.status(201).body(savedEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeDto employeeDto
    ) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDto);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}