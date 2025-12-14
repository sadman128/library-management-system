package com.sajid.librarymanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class LibraryManagementSystemApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(LibraryManagementSystemApplication.class, args);

    }

}
