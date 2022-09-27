package com.rightfindpro.become.exam;

import com.rightfindpro.become.exam.Exam;
import com.rightfindpro.become.dto.PageDto;
import com.rightfindpro.become.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/exams")
public class ExamController {
    @Autowired
    ExamService examService;
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



    //Question list
    //+ Choices


    //list of questions
    //with thier choices
}
