package com.rightfindpro.become.mapper;

import com.rightfindpro.become.dto.course.CourseDto;
import com.rightfindpro.become.service.CourseService;
import lombok.RequiredArgsConstructor;
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
