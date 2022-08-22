package com.rightfindpro.become.repository;

import com.rightfindpro.become.domain.Choice;
import com.rightfindpro.become.domain.Course;
import com.rightfindpro.become.domain.Exam;
import com.rightfindpro.become.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

   // List<Question> findAllByExamId(Integer examId);



    List<Question> findAllByExamsIn(Collection<Integer>exams);

    @Query( value = "select q.question, c.name, c.question from Question q  inner join exam_question on  q.id=exam_question.question_id and exam_question.exam_id=:exams inner join choice c on q.id = c.question", nativeQuery = true )
    public List<Question>findByExam(@Param("exams")Integer exams);

    List<Choice> findAllById(Integer id);



}
