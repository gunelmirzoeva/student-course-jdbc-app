package org.example.service;

import org.example.entity.Course;

import java.util.List;

public interface CourseService {
    void createCourse(String name);
    void deleteCourse(int id);
    List<Course> getAllCourses();
}
