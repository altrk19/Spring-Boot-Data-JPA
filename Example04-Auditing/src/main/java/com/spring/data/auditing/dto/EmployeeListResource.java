package com.spring.data.auditing.dto;

import java.util.List;

public class EmployeeListResource {
    private List<EmployeeResource> employees;

    public EmployeeListResource() {
    }

    public EmployeeListResource(List<EmployeeResource> employees) {
        this.employees = employees;
    }

    public List<EmployeeResource> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeResource> employees) {
        this.employees = employees;
    }
}
