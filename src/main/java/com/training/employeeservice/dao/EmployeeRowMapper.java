package com.training.employeeservice.dao;

import com.training.employeeservice.model.Employee;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs,int rowNum) throws SQLException {
        Employee employee = new Employee();

        employee.setEmployeeId(rs.getLong("EMPLOYEE_ID"));
        employee.setFirstName(rs.getString("FIRST_NAME"));
        employee.setLastName(rs.getString("LAST_NAME"));
        employee.setEmail(rs.getString("EMAIL"));
        employee.setDepartment(rs.getString("DEPARTMENT"));
        employee.setSalary(rs.getBigDecimal("SALARY"));
        employee.setHireDate(rs.getDate("HIRE_DATE").toLocalDate());
        employee.setCreatedAt(rs.getTimestamp("CREATED_AT").toLocalDateTime());

        return employee;
    }
}
