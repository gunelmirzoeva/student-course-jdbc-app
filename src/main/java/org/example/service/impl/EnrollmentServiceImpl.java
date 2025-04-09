package org.example.service.impl;

import org.example.dao.EnrollmentDao;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.service.EnrollmentService;

import java.util.List;

public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentDao enrollmentDao;

    public EnrollmentServiceImpl(EnrollmentDao enrollmentDao) {
        this.enrollmentDao = enrollmentDao;
    }

    @Override
    public void enroll(int studentId, int courseId) {
        if(studentId <= 0 || courseId <= 0) {
            System.out.println("Invalid student or course id");
            return;
        }
        if (enrollmentDao.isAlreadyEnrolled(studentId, courseId)) {
            System.out.println("⚠️ Already enrolled.");
            return;
        }
        enrollmentDao.enroll(studentId, courseId);
        System.out.println("Student enrolled in course.");
    }

    @Override
    public void printCoursesForStudent(int studentId) {
        List<Course> courses = enrollmentDao.getCoursesByStudentId(studentId);
        if (courses.isEmpty()) {
            System.out.println("No courses found for student ID " + studentId);
            return;
        }
        System.out.println("Courses for Student ID " + studentId + ":");
        for (Course course : courses) {
            System.out.println(" - [" + course.getId() + "] " + course.getName());
        }
    }

    @Override
    public void printStudentsInCourse(int courseId) {
        List<Student> students = enrollmentDao.getStudentsByCourseId(courseId);
        if (students.isEmpty()) {
            System.out.println("No students found for course ID " + courseId);
            return;
        }
        System.out.println("Students enrolled in Course ID " + courseId + ":");
        for (Student student : students) {
            System.out.println(" - [" + student.getId() + "] " + student.getName() + ", age: " + student.getAge());
        }
    }
}
