package com.example1.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseConnectionTest implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        testDatabaseConnection();
    }

    private void testDatabaseConnection() throws SQLException {
        System.out.println("Testing database connection...");

        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Database connection successful: " + connection.getCatalog());
        } catch (Exception e) {
            System.err.println("Database connection failed: " + e.getMessage());
            throw e;
        }
    }
}
