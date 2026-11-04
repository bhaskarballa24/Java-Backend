package com.training.employeeservice.controller;

import com.training.employeeservice.dto.EmployeeRequest;
import com.training.employeeservice.service.EmployeeService;
import com.training.employeeservice.model.Employee;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    //create
    @PostMapping
    public ResponseEntity<Employee> createEmployee(
            @Valid @RequestBody EmployeeRequest request ){

        Employee employee=employeeService.createEmployee(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    //read all
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = employeeService.findAllEmployees();

        return ResponseEntity.ok(employees);
    }

    //read by id
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById( @PathVariable Long id){

        Employee employee = employeeService.findEmployeeById(id);

        return ResponseEntity.ok(employee);
    }

    //update
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
                                                   @Valid @RequestBody EmployeeRequest request){

        Employee employee = employeeService.updateEmployee(id, request);

        if(employee == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    //delete
    @DeleteMapping("{id}")
    public  ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        boolean deleted = employeeService.deleteEmployee(id);

        if(!deleted){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }


}
