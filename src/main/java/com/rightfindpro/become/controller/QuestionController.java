package com.rightfindpro.become.controller;

import com.rightfindpro.become.domain.Question;
import com.rightfindpro.become.domain.User;
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
    @Autowired
    QuestionRepository questionRepository;

    @GetMapping(value = {"", "/"})
    public List<Question> getQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping("/question")
    public Question createQuestion(@RequestBody Question question) {

        return questionService.createNewQuestion(question);

    }

    public ResponseEntity<Map<String, Object>> getAllQuestios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            List<Question> questions = new ArrayList<Question>();
            Pageable paging = PageRequest.of(page, size);

            Page<Question> pageQuestions;

            pageQuestions = questionRepository.findAll(paging);
            questions = pageQuestions.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("questions", questions);
            response.put("currentPage", pageQuestions.getNumber());
            response.put("totalItems", pageQuestions.getTotalElements());
            response.put("totalPages", pageQuestions.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //Delete
        //Patch


    }
}
