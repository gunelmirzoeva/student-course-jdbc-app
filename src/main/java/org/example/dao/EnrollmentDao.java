package org.example.dao;

import org.example.entity.Course;
import org.example.entity.Student;

import java.util.List;

public interface EnrollmentDao {
    List<Course> getCoursesByStudentId(int studentId);
    List<Student> getStudentsByCourseId(int courseId);
    void removeEnrollment(int studentId, int courseId);

    void enroll(int studentId, int courseId);
    boolean isAlreadyEnrolled(int studentId, int courseId);

}
