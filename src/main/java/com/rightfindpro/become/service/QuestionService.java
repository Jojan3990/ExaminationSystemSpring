package com.rightfindpro.become.service;

import com.rightfindpro.become.domain.Course;
import com.rightfindpro.become.domain.Question;
import com.rightfindpro.become.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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
}

