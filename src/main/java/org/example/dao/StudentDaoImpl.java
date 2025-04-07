package org.example.dao;

import org.example.config.DatabaseConfig;
import org.example.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private final Connection conn;

    public StudentDaoImpl() throws SQLException {
        this.conn = DatabaseConfig.getConnection();
    }

    @Override
    public Student getById(int id) {
        String sql = "select * from students where id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"));
            }
        } catch (SQLException e) {
            System.out.println("Couldn't get student by id: " + id);
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        String sql = "select * from students order by id";
        List<Student> students = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {
                int id = rs.getInt("id");
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

        try(PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());

            int affectedRows = ps.executeUpdate();
            if(affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if(rs.next()) {
                        int id = rs.getInt(1);
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

    @Override
    public void update(Student student) {
        String sql = "update students set name = ?, age = ? where id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setInt(3, student.getId());
            ps.executeUpdate();
            System.out.println("Updated student with id: " + student.getId());
        } catch (SQLException e) {
            System.out.println("Couldn't update student");
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from students where id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Deleted student with id: " + id);
        } catch (SQLException e) {
            System.out.println("Couldn't delete student");
        }
    }
}
