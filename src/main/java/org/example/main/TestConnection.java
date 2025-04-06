package org.example.main;

import org.example.config.DatabaseConfig;
import org.example.dao.StudentDaoImpl;
import org.example.entity.Student;

import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        DatabaseConfig config = new DatabaseConfig();
        StudentDaoImpl student = new StudentDaoImpl();
        try {
            System.out.println(config.getConnection());
            System.out.println("Connected successfully");
            Student student1 = new Student(1L, "Gunel", 21);
            student.save(student1);
            System.out.println(student.seeAll());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}