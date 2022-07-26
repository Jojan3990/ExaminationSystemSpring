package com.rightfindpro.become.controller;

import com.rightfindpro.become.domain.Question;
import com.rightfindpro.become.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired QuestionService questionService;

    @GetMapping(value = {"", "/"})
    public List<Question> getQuestions() {
        return questionService.getAllQuestions();
    }
    @PostMapping("/question")
    public Question createQuestion(@RequestBody Question question) {

        return questionService.createNewQuestion(question);

    }

  //Delete
    //Patch


}
