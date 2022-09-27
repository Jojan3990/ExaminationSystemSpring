package com.rightfindpro.become.service;

import com.rightfindpro.become.Exception.ApiRequestException;
import com.rightfindpro.become.domain.Choice;
import com.rightfindpro.become.question.Question;
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
import java.util.Set;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public Question getQuestion(Integer id) {
        return questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question not found"));
    }


    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    /* public List<Question> listQuestions(Integer examId) {
         List<Question> questionList = questionRepository.findAll();
         return questionList;
     }*/
    public Question createNewQuestion(Question Question) {
        return questionRepository.save(Question);
    }


    public List<Question> listQuestions(Integer examId) {
        return questionRepository.findQuestionsByExams(examId);
    }
    


    /*public void addQuestion(QuestionDto questionDto) {
        Question question = getQuestionFromDto(questionDto);
        questionRepository.save(question);
    }*/

   /* public void updateQuestion(Integer questionID, QuestionDto questionDto) {
        Question question = getQuestionFromDto(questionDto);
        question.setId(questionID);
        questionRepository.save(question);
    }
    */

  /*  public List<Course> listCourses(Integer id) {
        return questionRepository.findById(id);
    }*/

    public ResponseEntity<PageDto> findAllQuestions(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);

            PageDtoMapper<Question> pageDtoMapper = new PageDtoMapper<>();
            Page<Question> questionPage = questionRepository.findAll(pageable);
            PageDto pageDto = pageDtoMapper.toPageResponse(questionPage);
            return new ResponseEntity<>(pageDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Choice> findAllByQuestion(int id) {
        return questionRepository.findAllById(id);
    }

    public String deleteQuestionById(int id) {

        Question question = questionRepository.findById(id).orElseThrow(() -> new ApiRequestException("Question Not Found.", HttpStatus.NOT_FOUND));
        questionRepository.delete(question);
        return "Question deleted sucessfully";
    }

    public Set<Choice> choices(int id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new ApiRequestException("Question Not Found.", HttpStatus.NOT_FOUND));
        ;
        Set<Choice> choices = question.getChoices();
        return choices;
    }

}

