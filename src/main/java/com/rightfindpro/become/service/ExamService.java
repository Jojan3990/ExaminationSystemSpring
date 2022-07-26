package com.rightfindpro.become.service;

import com.rightfindpro.become.domain.Course;
import com.rightfindpro.become.domain.Exam;
import com.rightfindpro.become.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExamService {

    @Autowired
    ExamRepository examRepository;

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public List<Exam> listExams(int courseId) {
        List<Exam> examList = examRepository.findAllByCourseId(courseId);
        return examList;
    }

    public Exam getExam(Integer id) {
        return examRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Exam not found"));
    }


}
