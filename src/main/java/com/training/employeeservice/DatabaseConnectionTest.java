package com.training.employeeservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class DatabaseConnectionTest implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    public DatabaseConnectionTest(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args){

        String sql = """
                SELECT USER as logged_in_user,
                SYS_CONTEXT('USERENV', 'CON_NAME') AS container_name,
                SYS_CONTEXT('USERENV', 'SERVICE_NAME') AS service_name
                FROM dual
                """;

        Map<String , Object> res = jdbcTemplate.queryForMap(sql);
        System.out.println("==== DATABASE CONNECTION TEST===");
        System.out.println("Logged_in_user:" + res.get("LOGGED_IN_USER"));
        System.out.println("Container: "+ res.get("CONTAINER_NAME"));
        System.out.println("Service: "+res.get("SERVICE_NAME"));
        System.out.println("=======================================");
    }
}
