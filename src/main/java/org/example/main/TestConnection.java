package org.example.main;

import org.example.config.DatabaseConfig;
import org.example.dao.StudentDaoImpl;
import org.example.entity.Student;

import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args){
        DatabaseConfig config = new DatabaseConfig();

        try {
            StudentDaoImpl student = new StudentDaoImpl();
            System.out.println("Connected successfully");
            Student student1 = new Student(1, "Gunel", 21);
            //student.save(student1);
            System.out.println(student.getAll());
            System.out.println(student.getById(3));
            student.update(new Student(2, "Elvira", 21));
            System.out.println(student.getAll());
            student.delete(4);
            System.out.println(student.getAll());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}