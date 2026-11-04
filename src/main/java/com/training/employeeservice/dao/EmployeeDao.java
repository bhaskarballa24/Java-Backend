package com.training.employeeservice.dao;

import com.training.employeeservice.dto.EmployeeRequest;
import com.training.employeeservice.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EmployeeDao {
    private final JdbcTemplate jdbcTemplate;

    public EmployeeDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public String findEmployeeNameById(Long employeeId){

        String sql = """
                SELECT FIRST_NAME 
                FROM EMPLOYEES
                WHERE EMPLOYEE_ID=?  
                """;

        return jdbcTemplate.queryForObject(
                sql,
                String.class,
                employeeId);
    }


    //create
    public Long createEmployee(EmployeeRequest request){

        Long employeeId = jdbcTemplate.queryForObject(
                "SELECT EMPLOYEE_SEQ.NEXTVAL FROM DUAL",
                Long.class
        );

        String sql = """
                INSERT INTO EMPLOYEES
                (EMPLOYEE_ID,FIRST_NAME,LAST_NAME,EMAIL,
                DEPARTMENT,SALARY,HIRE_DATE,CREATED_AT)
                VALUES(?,?,?,?,?,?,SYSDATE,SYSTIMESTAMP)                
                """;

        jdbcTemplate.update(
                sql,
                employeeId,
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getDepartment(),
                request.getSalary()
                );

        return employeeId;
    }

    //read all
    public List<Employee> findAllEmployees(){

        String sql = """
                SELECT * FROM EMPLOYEES ORDER BY EMPLOYEE_ID;
                """;

        return jdbcTemplate.query(sql, new EmployeeRowMapper());

    }

    //read by id

    public Employee findEmployeeById(Long employeeId) {
        String sql = """
            SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID = ?
            """;

        return jdbcTemplate.queryForObject(
                sql,
                new EmployeeRowMapper(),
                employeeId
        );
    }

    //update

    public int updateEmployee(Long employeeId, EmployeeRequest request){

        String sql= """
                UPDATE EMPLOYEES
                SET FIRST_NAME=?,
                LAST_NAME=?,
                EMAIL=?,
                DEPARTMENT=?,
                SALARY=?
                WHERE EMPLOYEE_ID=?
                """;

        return jdbcTemplate.update(
                sql,
                request.getFirstName(),
                request.getFirstName(),
                request.getEmail(),
                request.getDepartment(),
                request.getSalary(),
                employeeId
                );
    }


    //Delete
    public int deleteEmployee(Long employeeId){

        String sql = """
                DELETE FROM EMPLOYEES
                WHERE EMPLOYEE_ID=?""";

        return jdbcTemplate.update(sql,employeeId);
    }
}
