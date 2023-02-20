package com.rightfindpro.become.user;


//import com.rightfindpro.become.domain.Course;
import com.rightfindpro.become.course.CourseService;
import com.rightfindpro.become.question.QuestionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    CourseService courseService;
    QuestionService questionService;

    @GetMapping("/all")
    public String admin() {
        return ("<p>Admin</p>");
    }
//courses



    //questions




}
