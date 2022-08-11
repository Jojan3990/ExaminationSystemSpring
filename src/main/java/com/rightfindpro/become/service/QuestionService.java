package com.rightfindpro.become.service;

import com.rightfindpro.become.domain.Question;
import com.rightfindpro.become.dto.PageDto;
import com.rightfindpro.become.mapper.PageDtoMapper;
import com.rightfindpro.become.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public Question getQuestion(Integer id) {
        return questionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Question not found"));
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }


    public Question createNewQuestion(Question Question) {
        return questionRepository.save(Question);
    }


    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
    }

    public List<Question> getQuestionsByExam(Integer id) {
        return questionRepository.findAllByExams(id);
    }

    public ResponseEntity<PageDto> findAllQuestions(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);

            PageDtoMapper<Question> pageDtoMapper = new PageDtoMapper<>();
            Page<Question> questionPage = questionRepository.findAll(pageable);
            PageDto pageDto = pageDtoMapper.toPageResponse(questionPage);
            return new ResponseEntity<>(pageDto, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

