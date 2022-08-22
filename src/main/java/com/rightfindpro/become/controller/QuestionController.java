package com.rightfindpro.become.controller;

import com.rightfindpro.become.domain.Choice;
import com.rightfindpro.become.domain.Question;
import com.rightfindpro.become.dto.PageDto;
import com.rightfindpro.become.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


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

    @GetMapping("/{exam}")
    public List<Question> getQuestionsByExam(@PathVariable("exam") Integer exam) {
        return questionService.listQuestions((exam));
    }

    @GetMapping("/questionsv1")
    public ResponseEntity<PageDto> getAllQuestions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        return questionService.findAllQuestions(page, size);
        //Delete
        //Patch


    }

    @DeleteMapping("/Question/{id}")
    public void deleteCourse(@PathVariable("id") int id) {
        questionService.deleteQuestionById(id);
    }

    @GetMapping("/choice")
    public List<Choice> getChoiceByQuestion(int id){
        return questionService.findAllByQuestion(id);
    }
}
