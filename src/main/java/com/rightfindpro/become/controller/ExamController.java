package com.rightfindpro.become.controller;

import com.rightfindpro.become.domain.Exam;
import com.rightfindpro.become.dto.PageDto;
import com.rightfindpro.become.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {
    @Autowired
    ExamService examService;
    @GetMapping("/{course}")
    public List<Exam> getExamsByCourse(@PathVariable("course") Integer course) {
        return examService.listExams(course);
    }

    @GetMapping(value = {"", "/"})
    public List<Exam> getExams() {
        return examService.getAllExams();
    }

@GetMapping("/exams")
    public ResponseEntity<PageDto> getAllExams(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        return examService.findAllExams(page, size);



    }


    //list of questions
    //with thier choices
}
