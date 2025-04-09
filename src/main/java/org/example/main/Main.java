package org.example.main;

import org.example.dao.impl.CourseDaoImpl;
import org.example.dao.impl.EnrollmentDaoImpl;
import org.example.dao.impl.StudentDaoImpl;
import org.example.service.impl.CourseServiceImpl;
import org.example.service.impl.EnrollmentServiceImpl;
import org.example.service.impl.StudentServiceImpl;
import org.example.service.CourseService;
import org.example.service.EnrollmentService;
import org.example.service.StudentService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService studentService = null;
        CourseService courseService = null;
        EnrollmentService enrollmentService = null;

        try {
            studentService = new StudentServiceImpl(new StudentDaoImpl());
            courseService = new CourseServiceImpl(new CourseDaoImpl());
            enrollmentService = new EnrollmentServiceImpl(new EnrollmentDaoImpl());
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            return;
        }

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Create Student");
            System.out.println("2. Create Course");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. List Courses by Student");
            System.out.println("5. List Students by Course");
            System.out.println("6. Delete Student");
            System.out.println("7. Delete Course");
            System.out.println("8. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Student Age: ");
                    int age = scanner.nextInt();
                    studentService.createStudent(name, age);
                }
                case 2 -> {
                    System.out.print("Enter Course Name: ");
                    String courseName = scanner.nextLine();
                    courseService.createCourse(courseName);
                }
                case 3 -> {
                    System.out.print("Enter Student ID: ");
                    int studentId = scanner.nextInt();
                    System.out.print("Enter Course ID: ");
                    int courseId = scanner.nextInt();
                    enrollmentService.enroll(studentId, courseId);
                }
                case 4 -> {
                    System.out.print("Enter Student ID: ");
                    int studentId = scanner.nextInt();
                    enrollmentService.printCoursesForStudent(studentId);
                }
                case 5 -> {
                    System.out.print("Enter Course ID: ");
                    int courseId = scanner.nextInt();
                    enrollmentService.printStudentsInCourse(courseId);
                }
                case 6 -> {
                    System.out.print("Enter Student ID to Delete: ");
                    int studentId = scanner.nextInt();
                    studentService.deleteStudent(studentId);
                }
                case 7 -> {
                    System.out.print("Enter Course ID to Delete: ");
                    int courseId = scanner.nextInt();
                    courseService.deleteCourse(courseId);
                }
                case 8 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
