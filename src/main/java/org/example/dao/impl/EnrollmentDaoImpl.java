package org.example.dao.impl;

import org.example.config.DatabaseConfig;
import org.example.dao.EnrollmentDao;
import org.example.entity.Course;
import org.example.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDaoImpl implements EnrollmentDao {
    private final Connection conn;

    public EnrollmentDaoImpl() throws SQLException {
        this.conn = DatabaseConfig.getConnection();
    }

    @Override
    public void enroll(int studentId, int courseId) {
        String sql = "insert into student_courses (student_id, course_id) values (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
            System.out.println("Enrolled student " + studentId + " in course " + courseId);
        } catch (SQLException e) {
            System.out.println("Enrollment failed");
        }
    }

    @Override
    public boolean isAlreadyEnrolled(int studentId, int courseId) {
        String sql = "SELECT COUNT(*) FROM student_courses WHERE student_id = ? AND course_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Failed to check enrollment.");
        }
        return false;
    }


    @Override
    public List<Course> getCoursesByStudentId(int studentId) {
        String sql = "SELECT c.id, c.name FROM courses c JOIN student_courses sc ON c.id = sc.course_id WHERE sc.student_id = ?";
        List<Course> courses = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                courses.add(new Course(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println("Enrollment failed");
        }
        return courses;
    }

    @Override
    public List<Student> getStudentsByCourseId(int courseId) {
        String sql = "Select s.id, s.name, s.age from students s join student_courses sc on s.id = sc.student_id where sc.course_id = ?";
        List<Student> students = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                students.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age")));
            }
        } catch (SQLException e) {
            System.out.println("Enrollment failed");
        }
        return students;
    }

    @Override
    public void removeEnrollment(int studentId, int courseId) {
        String sql = "delete from student_courses where student_id = ? and course_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
            System.out.println("Enrollment removed with id " + studentId + " and course " + courseId);
        } catch (SQLException e) {
            System.out.println("Removing enrollment failed");
        }
    }

}
