package com.rightfindpro.become.repository;

import com.rightfindpro.become.domain.Course;
import com.rightfindpro.become.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findAll();

    Optional<Question> findById(Integer id);

    List<Question> findAllByExams(Integer id);


}
