package org.example.dao;

import org.example.config.DatabaseConfig;
import org.example.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDaoImpl implements StudentDao {

    @Override
    public List<Student> seeAll() {
        String sql = "select * from students";
        List<Student> students = new ArrayList<>();
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
               students.add(new Student(id, name, age));
            }
        } catch (SQLException e) {
            System.out.println("Couldn't see students");
        }
        return new ArrayList<>(students);
    }

    @Override
    public void save(Student student) {
        String sql = "insert into students (name, age) values (?, ?)";

        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());

            int affectedRows = ps.executeUpdate();
            if(affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if(rs.next()) {
                        Long id = rs.getLong(1);
                        System.out.println("Inserted student id: " + id);
                    } else {
                        System.out.println("No rows were affected. Student might not have been inserted.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Couldn't insert student");
        }
    }
}
