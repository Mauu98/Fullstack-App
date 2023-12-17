package com.api.employees.services;

import com.api.employees.entity.EmployeeEntity;
import com.api.employees.responses.EmployeeResponse;
import com.api.employees.repository.EmployeeRepository;
import com.api.employees.requests.EmployeeRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private final EmployeeRepository employeeRepository;

    @Transactional
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest){

        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .email(employeeRequest.getEmail())
                .build();

        employeeRepository.save(employeeEntity);

        return new EmployeeResponse(employeeEntity.getId(), employeeRequest.getFirstName(), employeeRequest.getLastName(), employeeRequest.getEmail());
    }

    public List<EmployeeResponse> getAllEmployees(){

        List<EmployeeEntity> employeesEntities = employeeRepository.findAll();

        return employeesEntities.stream()
                .map(emp -> new EmployeeResponse(emp.getId(), emp.getFirstName(), emp.getLastName(), emp.getEmail()))
                .collect(Collectors.toList());
    }
}
