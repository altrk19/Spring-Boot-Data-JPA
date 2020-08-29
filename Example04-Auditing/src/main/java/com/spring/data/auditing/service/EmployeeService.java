package com.spring.data.auditing.service;

import com.spring.data.auditing.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

    List<EmployeeEntity> getAllEmployees();

    EmployeeEntity saveEmployee(EmployeeEntity employeeEntity);

    EmployeeEntity updateEmployee(int id, EmployeeEntity updatedEmployee);
}
