package com.rightfindpro.become.mapper;

import com.rightfindpro.become.domain.Course;
import com.rightfindpro.become.dto.CourseDto;
import com.rightfindpro.become.dto.course.CourseResponse;
import com.rightfindpro.become.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class CourseDtoMapper {
    private DtoMapper mapper;
    private CourseService courseService;




    public List<CourseDto> findAllCourses(int page, int size) {
        return new ArrayList<>();
    }




}
