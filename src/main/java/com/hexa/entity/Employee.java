package com.hexa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Employee {
    private int employeeId;
    private String name;
    private String department;
    private String email;
    private String password;

    
}
