package com.rightfindpro.become.controller;


import com.rightfindpro.become.domain.Course;
import com.rightfindpro.become.domain.Question;
import com.rightfindpro.become.service.CourseService;
import com.rightfindpro.become.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    CourseService courseService;
    QuestionService questionService;

    @GetMapping("/all")
    public String admin() {
        return ("<h1> Admin</h1>");
    }
//courses



    //questions




}
