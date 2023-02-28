package com.rightfindpro.become.exam;

import com.rightfindpro.become.Exception.ApiRequestException;
//import com.rightfindpro.become.domain.*;
import com.rightfindpro.become.PageDto;
import com.rightfindpro.become.PageDtoMapper;
import com.rightfindpro.become.course.CourseRepository;
import com.rightfindpro.become.question.QuestionService;
import com.rightfindpro.become.user.User;
import com.rightfindpro.become.user.UserExamDTO;
import com.rightfindpro.become.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamService {

    @Autowired
    ExamRepository examRepository;
    @Autowired
    QuestionService questionService;
    @Autowired
    private CourseRepository courseRepository;


    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public List<Exam> listExams(int courseId) {
        List<Exam> examList = examRepository.findAllByCourseId(courseId);
        return examList;
    }

    public Exam getExam(Integer id) {
        return examRepository.findById(id).orElseThrow(() -> new ApiRequestException("Exam not found", HttpStatus.NOT_FOUND));
    }

    public List<Exam> getExamByCourse(Integer id) {
        return examRepository.findAllByCourseId(id);
    }

    public ResponseEntity<PageDto> findAllExams(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            PageDtoMapper<Exam> pageDtoMapper = new PageDtoMapper<>();
            Page<Exam> examPage = examRepository.findAll(pageable);
            PageDto pageDto = pageDtoMapper.toPageResponse(examPage);
            return new ResponseEntity<>(pageDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String deleteExam(Integer id) {
        {
            Exam exam = examRepository.findById(id).orElseThrow(() -> new ApiRequestException("Course Not Found.", HttpStatus.NOT_FOUND));
            examRepository.delete(exam);
            return "Exam deleted successfully";
        }
    }

    public List<User> getUsersNameByExamId(int exam_id) {
//        Optional<Exam> exam = examRepository.findById(exam_id);
//        if (exam.isPresent()) {
//            List<User> listStore = examRepository.getUsersNameByExamId(exam_id);
//            if (listStore.isEmpty() != true) {
//                return new ResponseEntity<>(listStore.stream().map(userMapper::toDto).collect(Collectors.toList()), HttpStatus.OK);
//            }
//            return new ResponseEntity<>("Users has not given exam with id " + exam_id + " exam id", HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>("Exam with id " + exam_id + " not found", HttpStatus.NOT_FOUND);
//        }
        try {
            Optional<Exam> exam = examRepository.findById(exam_id);
//            List<User> listStore = examRepository.getUsersNameByExamId(exam_id);
//            return listStore.stream().map(userMapper::toDto).collect(Collectors.toList());
            return examRepository.getUsersNameByExamId(exam_id);
        }
        catch (Exception ex){
            return null;
        }
    }


//    public Result checkAnswers(Exam exam, List<Response> answersBundle) {
//        Result results = new Result();
//
//        for (Question question : exam.getQuestions()) {
//            boolean isFound = false;
//
//        }
//
//
//        return results;
//    }

//    public Page<Exam> findExamsByUser(Integer userId, Pageable pageable) {
//        return (Page<Exam>) examRepository.findAllByUserId(userId);
//    }

    //    public Exam createExam(Integer course_id, String exam_name){
//        Exam exam=new Exam();
//        exam.setName(exam_name);
//        Course course=courseRepository.findAllById(course_id).get();
//        exam.setCourse(Collections.singleton(course));
//        exam.setCourse(course);
//        examRepository.save(exam);
//        return exam;
//    }
    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }


}
