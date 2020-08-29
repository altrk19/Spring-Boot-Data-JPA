package com.spring.data.auditing.utils;

import com.spring.data.auditing.dto.EmployeeListResource;
import com.spring.data.auditing.dto.EmployeeResource;
import com.spring.data.auditing.entity.EmployeeEntity;

import java.util.ArrayList;
import java.util.List;

public class EmployeeConverter {
    public static EmployeeEntity toEmployeeEntity(EmployeeResource employeeResource) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName(employeeResource.getName());
        employeeEntity.setDob(employeeResource.getDob());
        employeeEntity.setDept(employeeResource.getDept());
        employeeEntity.setSalary(employeeResource.getSalary());
        employeeEntity.setCreateDate(employeeResource.getCreateDate());
        employeeEntity.setLastModifiedDate(employeeResource.getLastModifiedDate());
        employeeEntity.setCreatedBy(employeeResource.getCreatedBy());
        employeeEntity.setModifiedBy(employeeResource.getModifiedBy());

        return employeeEntity;
    }

    public static EmployeeResource toEmployeeResource(EmployeeEntity createdEmployee) {
        EmployeeResource employeeResource = new EmployeeResource();
        employeeResource.setId(createdEmployee.getId());
        employeeResource.setName(createdEmployee.getName());
        employeeResource.setDob(createdEmployee.getDob());
        employeeResource.setDept(createdEmployee.getDept());
        employeeResource.setSalary(createdEmployee.getSalary());
        employeeResource.setCreateDate(createdEmployee.getCreateDate());
        employeeResource.setLastModifiedDate(createdEmployee.getLastModifiedDate());
        employeeResource.setCreatedBy(createdEmployee.getCreatedBy());
        employeeResource.setModifiedBy(createdEmployee.getModifiedBy());

        return employeeResource;
    }

    public static EmployeeListResource toEmployeeListResource(List<EmployeeEntity> employees) {
        EmployeeListResource employeeListResource = new EmployeeListResource(new ArrayList<>());

        if(!employees.isEmpty()){
            for(EmployeeEntity employee:employees){
                EmployeeResource employeeResource = new EmployeeResource();
                employeeResource.setId(employee.getId());
                employeeResource.setName(employee.getName());
                employeeResource.setDob(employee.getDob());
                employeeResource.setDept(employee.getDept());
                employeeResource.setSalary(employee.getSalary());
                employeeResource.setCreateDate(employee.getCreateDate());
                employeeResource.setCreatedBy(employee.getCreatedBy());
                employeeResource.setLastModifiedDate(employee.getLastModifiedDate());
                employeeResource.setModifiedBy(employee.getModifiedBy());

                employeeListResource.getEmployees().add(employeeResource);
            }
        }
        return employeeListResource;
    }
}