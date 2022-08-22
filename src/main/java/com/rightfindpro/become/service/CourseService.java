package com.rightfindpro.become.service;

import com.rightfindpro.become.Exception.ApiRequestException;
import com.rightfindpro.become.domain.Course;
import com.rightfindpro.become.dto.PageDto;
import com.rightfindpro.become.mapper.PageDtoMapper;
import com.rightfindpro.become.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourse(Integer id) {
        return courseRepository.findById(id).orElseThrow(()->new ApiRequestException("Course not found",HttpStatus.NOT_FOUND));
    }
    public Course createNewCourse(Course course) {
        return courseRepository.save(course);
    }


    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }

    public PageDto findAllCourses(int page, int size) {
           Pageable pageable = PageRequest.of(page, size);
           PageDtoMapper<Course> pageDtoMapper = new PageDtoMapper<>();
           Page<Course> coursePage = courseRepository.findAll(pageable);
           PageDto pageDto = pageDtoMapper.toPageResponse(coursePage);
           return pageDto;

    }

    public String deleteCourseById(int id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(()->new ApiRequestException("Course Not Found.", HttpStatus.NOT_FOUND));
        courseRepository.delete(course);
        return "Course deleted sucessfully";
    }



}
