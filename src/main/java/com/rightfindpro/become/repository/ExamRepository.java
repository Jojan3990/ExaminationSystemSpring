package com.rightfindpro.become.repository;

import com.rightfindpro.become.domain.Course;
import com.rightfindpro.become.domain.Exam;
import com.rightfindpro.become.domain.Question;
import com.rightfindpro.become.service.QuestionService;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Integer> {

    //@Query("from Exam exam LEFT JOIN exam.course course where course.id = ?1")
  /*   @Query(value = "SELECT * FROM Exam exam LEFT JOIN exam.course on course.id WHERE course.id = ?1", nativeQuery = true)
    List<Exam> findExamByCourse(int course);*/

    List<Exam> findAllByCourseId(Integer courseId);
    Optional<Exam> findByName(String name);
    List<Exam> findAll();

    Optional<Exam> findById(Integer id);

    List<Exam> findAllByQuestions(Integer id);


    List<Exam> findAllByUserId(Integer userId);
}
