package com.rightfindpro.become.controller;

import com.rightfindpro.become.domain.Course;
import com.rightfindpro.become.domain.Exam;
import com.rightfindpro.become.domain.Role;
import com.rightfindpro.become.domain.User;
import com.rightfindpro.become.dto.PageDto;
import com.rightfindpro.become.mapping.PageDtoMapper;
import com.rightfindpro.become.repository.CourseRepository;
import com.rightfindpro.become.service.CourseService;
import com.rightfindpro.become.service.RoleService;
import liquibase.pro.packaged.G;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    @GetMapping("courses")
    public ResponseEntity<PageDto> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            Pageable paging = PageRequest.of(page, size);
            PageDtoMapper<Course> pageDtoMapper = new PageDtoMapper();
            Page<Course> pageUsers = courseRepository.findAll(paging);
            PageDto pageDto = pageDtoMapper.toPageResponse(pageUsers);
            return new ResponseEntity<>(pageDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
