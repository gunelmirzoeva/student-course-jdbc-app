package org.example.service;


import org.example.entity.Student;

import java.util.List;

public interface StudentService {
    void createStudent(String name, int age);
    void deleteStudent(int id);
    List<Student> getAllStudents();
}
