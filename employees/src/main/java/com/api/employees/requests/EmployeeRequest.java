package com.api.employees.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeRequest {

    private String firstName;
    private String lastName;
    private String email;

}
