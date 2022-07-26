package com.rightfindpro.become.controller;

import com.rightfindpro.become.domain.Course;
import com.rightfindpro.become.domain.Exam;
import com.rightfindpro.become.domain.Role;
import com.rightfindpro.become.repository.CourseRepository;
import com.rightfindpro.become.service.CourseService;
import com.rightfindpro.become.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    CourseRepository courseRepository;

    @GetMapping(value = {"","/"})
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping("/CreateNewCourse")
    public Course createNewCourse(@RequestBody Course course) {

        return courseService.createNewCourse(course);

    }

    @GetMapping("/course?={course}")
    public List<Exam> getExamsByCourse(@RequestParam @PathVariable("course") Integer course){
        return courseRepository.getExamsById(course);
    }
//getCourseByID

    //

}
