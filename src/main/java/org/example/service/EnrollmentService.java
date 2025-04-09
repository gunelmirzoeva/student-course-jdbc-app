package org.example.service;

public interface EnrollmentService {
    void enroll(int studentId, int courseId);
    void printCoursesForStudent(int studentId);
    void printStudentsInCourse(int courseId);
}
