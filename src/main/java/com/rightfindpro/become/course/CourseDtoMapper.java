package com.rightfindpro.become.course;

import com.rightfindpro.become.DtoMapper;
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
