package org.example.main;

import org.example.config.DatabaseConfig;
import org.example.dao.CourseDao;
import org.example.dao.EnrollmentDao;
import org.example.dao.impl.CourseDaoImpl;
import org.example.dao.impl.EnrollmentDaoImpl;
import org.example.dao.impl.StudentDaoImpl;
import org.example.entity.Course;
import org.example.entity.Student;

import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args){
        DatabaseConfig config = new DatabaseConfig();

        try {
//            StudentDaoImpl student = new StudentDaoImpl();
//            System.out.println("Connected successfully");
//            Student student1 = new Student(1, "Gunel", 21);
//            //student.save(student1);
//            System.out.println(student.getAll());
//            System.out.println(student.getById(3));
//            student.update(new Student(2, "Elvira", 21));
//            System.out.println(student.getAll());
//            student.delete(4);
//            System.out.println(student.getAll());

//            CourseDao courseDao = new CourseDaoImpl();
//            Course course = new Course(1, "Java Programming");
//            courseDao.save(course);
//            System.out.println(courseDao.getAll());
//            System.out.println(courseDao.getById(course.getId()));
//            courseDao.update(new Course(2, "Python Programming"));
//            System.out.println(courseDao.getAll());
           // courseDao.delete(course.getId());
           // System.out.println(courseDao.getAll());
            EnrollmentDao enrollmentDao = new EnrollmentDaoImpl();
            //enrollmentDao.enrollStudentInCourse(1, 2);
            System.out.println(enrollmentDao.getCoursesByStudentId(1));
            System.out.println(enrollmentDao.getStudentsByCourseId(2));
            enrollmentDao.removeEnrollment(1, 2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}