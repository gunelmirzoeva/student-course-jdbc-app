package org.example.dao.impl;

import org.example.config.DatabaseConfig;
import org.example.dao.CourseDao;
import org.example.entity.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    private final Connection conn;

    public CourseDaoImpl() throws SQLException {
        this.conn = DatabaseConfig.getConnection();
    }

    @Override
    public Course getById(int id) {
        String sql = "select * from courses where id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Course(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Couldn't get course by id: " + id);
        }
        return null;
    }

    @Override
    public List<Course> getAll() {
        String sql = "select * from courses order by id";
        List<Course> courses = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                courses.add(new Course(id, name));
            }
        } catch (SQLException e) {
            System.out.println("Couldn't get all courses: " + e.getMessage());
        }
        return new ArrayList<>(courses);
    }

    @Override
    public void save(Course course) {
        String sql = "insert into courses (name) values (?)";
        try(PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, course.getName());

            int affectedRows = ps.executeUpdate();
            if(affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if(rs.next()) {
                        int id = rs.getInt(1);
                        System.out.println("Inserted course with id: " + id);
                    } else {
                        System.out.println("No rows were affected. Student might not have been inserted.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Couldn't insert course");
        }
    }

    @Override
    public void update(Course course) {
        String sql = "update courses set name = ? where id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, course.getName());
            ps.setInt(2, course.getId());
            ps.executeUpdate();
            System.out.println("Updated course with id: " + course.getId());
        } catch (SQLException e) {
            System.out.println("Couldn't update course");
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from courses where id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Deleted course with id: " + id);
        } catch (SQLException e) {
            System.out.println("Couldn't delete course");
        }
    }
}
