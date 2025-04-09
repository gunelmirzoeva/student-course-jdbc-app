package org.example.service.impl;

import org.example.dao.StudentDao;
import org.example.entity.Student;
import org.example.service.StudentService;

import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void createStudent(String name, int age) {
        Optional.ofNullable(name)
                .filter(n -> !n.isBlank())
                .orElseThrow(() -> new IllegalArgumentException("Name cannot be empty"));

        Optional.of(age)
                .filter(n -> n > 0 && n <= 160)
                .orElseThrow(() -> new IllegalArgumentException("Invalid age"));

        studentDao.save(new Student(0, name, age));
        System.out.println("Student created.");
    }

    @Override
    public void deleteStudent(int id) {
        boolean studentExists = studentDao.getAll()
                .stream()
                .anyMatch(student -> student.getId() == id);
        if (!studentExists) {
            throw new IllegalArgumentException("Student with ID " + id + " does not exist.");
        }
        studentDao.delete(id);
        System.out.println("Student deleted.");
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAll();
    }
}
