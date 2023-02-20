package com.rightfindpro.become.exam;

import com.rightfindpro.become.PageDto;
import com.rightfindpro.become.question.Question;
import com.rightfindpro.become.question.QuestionRepository;
import com.rightfindpro.become.user.UserRepository;
import liquibase.pro.packaged.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/exams")
public class ExamController {
    @Autowired
    ExamService examService;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;

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
        return examService.findAllExams(page, size);
    }
    @DeleteMapping("/Exam/{id}")
    public void deleteExam(@PathVariable("id") int id) {
        examService.deleteExam(id);
    }

//    @PostMapping("/createExam")
//    public ResponseEntity<?> createExam(@RequestBody ExamDTO examDTO){
//        System.out.println(examDTO.getCourse_id()+" "+examDTO.getName());
//        int courseId=examDTO.getCourse_id().intValue();
////        return null;
//        return new ResponseEntity<>(examService.createExam(courseId,examDTO.getName()), HttpStatus.OK);
//    }

    @PostMapping("/createExam")
    public ResponseEntity<?> createExam(@RequestBody Exam exam){

        return new ResponseEntity<>(examService.createExam(exam),HttpStatus.OK);
    }


    //for inserting data in user_exam showing many to many relationship
    @PostMapping("/users/{userId}/exams")
    public ResponseEntity<Exam> addExam(@PathVariable(value = "userId") int userId,@RequestBody Exam examRequest){
//        System.out.println("This is user id"+userId);
//        System.out.println("This is user name"+examRequest.getName());
        Exam exam=userRepository.findById(userId).map(user -> {
            int examId = examRequest.getId();

            //exam exists
            if (examId != 0L) {//OL means number zero or type long
                Exam _exam = examRepository.findById(examId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found exam with id =" + examId));
                user.addExam(_exam);
                userRepository.save(user);
                return _exam;
            }

            //add and create new Exam
            user.addExam(examRequest);
            return examRepository.save(examRequest);
        }).orElseThrow(()->new ResourceNotFoundException("Not found user with id ="+userId));
        return new ResponseEntity<Exam>(exam, HttpStatus.CREATED);
    }


    //exam_question
    @PostMapping("/questions/{questionId}/exams")
    public ResponseEntity<?> addExamQuestion(@PathVariable(value = "questionId") int questionId,@RequestBody Exam examRequest){
        Exam exam=questionRepository.findById(questionId).map(question -> {
            int examId = examRequest.getId();

            //exam exists
            if (examId != 0L) {//OL means number zero or type long
                Exam _exam = examRepository.findById(examId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found exam with id =" + examId));
                question.addExam(_exam);
                questionRepository.save(question);
                return _exam;
            }

            //add and create new Exam
            question.addExam(examRequest);
            return examRepository.save(examRequest);
        }).orElseThrow(()->new ResourceNotFoundException("Not found question with id ="+questionId));
        return new ResponseEntity<Exam>(exam, HttpStatus.CREATED);
    }



    //Question list
    //+ Choices


    //list of questions
    //with thier choices
}
