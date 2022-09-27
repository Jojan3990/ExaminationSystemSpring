package com.rightfindpro.become.course;

import com.rightfindpro.become.domain.Course;
import com.rightfindpro.become.dto.PageDto;
import com.rightfindpro.become.service.CourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/courses")
public class CourseController {

    private CourseDtoMapper courseMapper;
    @Autowired
    private CourseService courseService;



    @GetMapping(value = {"", "/"})
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/all")
    public ResponseEntity<PageDto<Course>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
       return new ResponseEntity<>(courseService.findAllCourses(page, size), HttpStatus.OK);
    }


    @PostMapping("/CreateNewCourse")
    public Course createNewCourse(@RequestBody Course course) {

        return courseService.createNewCourse(course);

    }

    @DeleteMapping("/Course/{id}")
    public void deleteCourse(@PathVariable("id") int id) {
        courseService.deleteCourseById(id);;
    }



   /* @GetMapping("/{course}/exam")
    public List<Exam> getExamsByCourse( @PathVariable("course") Integer course) {
        return courseService.getExamsById(course);
    }*/
//getCourseByID

    //

}
