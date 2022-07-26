package com.rightfindpro.become.service;

import com.rightfindpro.become.domain.Course;
import com.rightfindpro.become.domain.Role;
import com.rightfindpro.become.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourse(Integer id) {
        return courseRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Course not found"));
    }
    public Course createNewCourse(Course course) {
        return courseRepository.save(course);
    }


    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }
}
