package com.spring.data.auditing.service;

import com.spring.data.auditing.entity.EmployeeEntity;
import com.spring.data.auditing.repo.EmployeeRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;


    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<EmployeeEntity> getAllEmployees(){
        return employeeRepo.findAll();
    }

    @Override
    @Transactional
    public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity) {
        return employeeRepo.save(employeeEntity);
    }

    @Override
    @Transactional
    public EmployeeEntity updateEmployee(int id, EmployeeEntity updatedEmployee){
        Optional<EmployeeEntity> employeeOpt = getEmployee(id);
        if(!employeeOpt.isPresent()){
           throw new RuntimeException("not found");

        }

        EmployeeEntity currentEmployee = employeeOpt.get();
        updateEmployeeEntity(updatedEmployee, currentEmployee);

        return currentEmployee;
    }

    private void updateEmployeeEntity(EmployeeEntity updatedEmployee, EmployeeEntity currentEmployee) {
        currentEmployee.setName(updatedEmployee.getName());
        currentEmployee.setDob(updatedEmployee.getDob());
        currentEmployee.setDept(updatedEmployee.getDept());
        currentEmployee.setSalary(updatedEmployee.getSalary());
    }

    private Optional<EmployeeEntity> getEmployee(int employeeId){
        return employeeRepo.findById(employeeId);
    }
}
