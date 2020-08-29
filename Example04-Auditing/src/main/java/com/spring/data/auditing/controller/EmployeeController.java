package com.spring.data.auditing.controller;

import com.spring.data.auditing.dto.EmployeeListResource;
import com.spring.data.auditing.dto.EmployeeResource;
import com.spring.data.auditing.entity.EmployeeEntity;
import com.spring.data.auditing.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.EmployeeConverter;

import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<EmployeeListResource> getAllEmployees() {
        List<EmployeeEntity> employees = employeeService.getAllEmployees();
        EmployeeListResource employeeListResource = EmployeeConverter.toEmployeeListResource(employees);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeListResource);
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeResource> createEmployee(@RequestBody EmployeeResource employeeResource) {
        EmployeeEntity employeeEntity = EmployeeConverter.toEmployeeEntity(employeeResource);
        EmployeeEntity createdEmployee = employeeService.saveEmployee(employeeEntity);
        EmployeeResource userResourceCreated = EmployeeConverter.toEmployeeResource(createdEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResourceCreated);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeResource> updateEmployee(@PathVariable("id") @NotNull Integer id,
                                                           @RequestBody EmployeeResource employeeResource) {
        EmployeeEntity employeeEntity = EmployeeConverter.toEmployeeEntity(employeeResource);
        EmployeeEntity updatedEmployee = employeeService.updateEmployee(id, employeeEntity);
        EmployeeResource userResourceCreated = EmployeeConverter.toEmployeeResource(updatedEmployee);
        return ResponseEntity.status(HttpStatus.OK).body(userResourceCreated);
    }

}