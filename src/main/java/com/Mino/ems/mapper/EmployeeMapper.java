package com.Mino.ems.mapper;

import com.Mino.ems.dto.EmployeeDto;
import com.Mino.ems.dto.EmployeeResponseDto;
import com.Mino.ems.entity.Employee;

public class EmployeeMapper {

    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        return employee;
    }

    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());

        return employeeDto;
    }


    public static EmployeeResponseDto mapToEmployeeResponseDto(
            Employee employee
    ) {

        return new EmployeeResponseDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }
}