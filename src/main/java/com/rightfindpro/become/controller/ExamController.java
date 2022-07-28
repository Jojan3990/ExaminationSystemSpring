package com.rightfindpro.become.controller;

import com.rightfindpro.become.domain.Exam;
import com.rightfindpro.become.domain.Question;
import com.rightfindpro.become.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/exams")
public class ExamController {
@Autowired ExamService examService;
    @GetMapping("/{course}")
    public List<Exam> getExamsByCourse(@PathVariable("course") Integer course){
        return examService.listExams(course);
    }

    @GetMapping(value = {"","/"})
    public List<Exam> getExams() {
        return examService.getAllExams();
    }

   /* @GetMapping("/{id}/questions")
    public List<Question> getExamQuestions(@RequestParam int id) {
        return
    }*/
}
