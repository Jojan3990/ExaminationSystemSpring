package com.rightfindpro.become.service;

import com.nimbusds.oauth2.sdk.Response;
import com.rightfindpro.become.Exception.ApiRequestException;
import com.rightfindpro.become.domain.*;
import com.rightfindpro.become.dto.PageDto;
import com.rightfindpro.become.exam.Exam;
import com.rightfindpro.become.mapper.PageDtoMapper;
import com.rightfindpro.become.question.Question;
import com.rightfindpro.become.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    @Autowired
    ExamRepository examRepository;
    QuestionService questionService;

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public List<Exam> listExams(int courseId) {
        List<Exam> examList = examRepository.findAllByCourseId(courseId);
        return examList;
    }

    public Exam getExam(Integer id) {
        return examRepository.findById(id).orElseThrow(()->new ApiRequestException("Exam not found",HttpStatus.NOT_FOUND));
    }

    public List<Exam> getExamByCourse(Integer id) {
        return  examRepository.findAllByCourseId(id);
    }

    public ResponseEntity<PageDto> findAllExams(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);

            PageDtoMapper<Exam> pageDtoMapper = new PageDtoMapper<>();
            Page<Exam> examPage = examRepository.findAll(pageable);
            PageDto pageDto = pageDtoMapper.toPageResponse(examPage);
            return new ResponseEntity<>(pageDto, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String deleteExam(Integer id) {
        {
            Exam exam = examRepository.findById(id)
                    .orElseThrow(() -> new ApiRequestException("Course Not Found.", HttpStatus.NOT_FOUND));
            examRepository.delete(exam);
            return "Exam deleted sucessfully";
        }
    }
    public Result checkAnswers(Exam exam, List<Response> answersBundle) {
        Result results = new Result();

        for (Question question : exam.getQuestions()) {
            boolean isFound = false;

        }


        return results;
    }

    public Page<Exam> findExamsByUser(Integer userId, Pageable pageable) {
        return (Page<Exam>) examRepository.findAllByUserId(userId);
    }


   
}
