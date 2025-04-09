package org.example.service.impl;

import org.example.dao.CourseDao;
import org.example.entity.Course;
import org.example.service.CourseService;

import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {
    private final CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public void createCourse(String name) {
        Optional.ofNullable(name)
                .filter(s -> !s.isBlank())
                .orElseThrow(() -> new IllegalArgumentException("Course name cannot be empty"));
        courseDao.save(new Course(0, name));
        System.out.println("Course " + name + " created");
    }

    @Override
    public void deleteCourse(int id) {
        boolean courseExist = courseDao.getAll()
                                        .stream()
                                        .anyMatch(course -> course.getId() == id);

        if(!courseExist) {
            throw new IllegalArgumentException("Course with id " + id + " does not exist");
        }
        courseDao.delete(id);
        System.out.println("Course " + id + " deleted");
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDao.getAll();
    }
}
