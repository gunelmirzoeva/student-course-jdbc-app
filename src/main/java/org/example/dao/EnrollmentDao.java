package org.example.dao;

import org.example.entity.Course;
import org.example.entity.Student;

import java.util.List;

public interface EnrollmentDao {
    void enrollStudentInCourse(int studentId, int courseId);
    List<Course> getCoursesByStudentId(int studentId);
    List<Student> getStudentsByCourseId(int courseId);
    void removeEnrollment(int studentId, int courseId);
}
