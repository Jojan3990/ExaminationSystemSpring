package com.rightfindpro.become.controller;

import com.rightfindpro.become.domain.Exam;
import com.rightfindpro.become.domain.Exam;
import com.rightfindpro.become.domain.User;
import com.rightfindpro.become.dto.PageDto;
import com.rightfindpro.become.mapping.PageDtoMapper;
import com.rightfindpro.become.repository.ExamRepository;
import com.rightfindpro.become.service.ExamService;
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
@RequestMapping("/api/exams")
public class ExamController {
    @Autowired
    ExamService examService;
    @Autowired
    ExamRepository examRepository;

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
        try {
            Pageable paging = PageRequest.of(page, size);
            PageDtoMapper<Exam> pageDtoMapper = new PageDtoMapper();
            Page<Exam> pageUsers = examRepository.findAll(paging);
            PageDto pageDto = pageDtoMapper.toPageResponse(pageUsers);
            return new ResponseEntity<>(pageDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }



   /* @GetMapping("/{id}/questions")
    public List<Question> getExamQuestions(@RequestParam int id) {
        return
    }*/
    }
}
