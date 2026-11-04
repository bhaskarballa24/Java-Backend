package com.training.employeeservice.service;

import com.training.employeeservice.dto.EmployeeRequest;
import com.training.employeeservice.dao.EmployeeDao;
import com.training.employeeservice.model.Employee;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    //create
    public Employee createEmployee(EmployeeRequest request) {

        Long employeeId = employeeDao.createEmployee(request);

        return employeeDao.findEmployeeById(employeeId);
    }

    //read all
    public List<Employee> findAllEmployees() {
        return employeeDao.findAllEmployees();
    }

    //read by id
    public Employee findEmployeeById(Long employeeId) {

        return employeeDao.findEmployeeById(employeeId);
    }

    //update
    public Employee updateEmployee(Long employeeId, EmployeeRequest request) {
        int rowsAffected = employeeDao.updateEmployee(employeeId, request);

        if (rowsAffected == 0) {
            return null;
        }
        return employeeDao.findEmployeeById(employeeId);
    }

    //delete
    public boolean deleteEmployee(Long employeeId) {

        int rowsAffected = employeeDao.deleteEmployee(employeeId);

        return rowsAffected > 0;
    }

}
