package com.Mino.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;
}