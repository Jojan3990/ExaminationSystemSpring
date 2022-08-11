package com.rightfindpro.become.controller;

import com.rightfindpro.become.domain.Exam;
import com.rightfindpro.become.domain.Question;
import com.rightfindpro.become.dto.PageDto;
import com.rightfindpro.become.repository.QuestionRepository;
import com.rightfindpro.become.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;


    @GetMapping(value = {"", "/"})
    public List<Question> getQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping("/question")
    public Question createQuestion(@RequestBody Question question) {

        return questionService.createNewQuestion(question);

    }

    @GetMapping("/exam")
    public List<Question> getQuestionsByExam(@RequestParam Exam exam){

        return questionService.getQuestionsByExam(exam.getId());

    }

    public ResponseEntity<PageDto> getAllQuestios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        return questionService.findAllQuestions(page, size);
        //Delete
        //Patch


    }
}
