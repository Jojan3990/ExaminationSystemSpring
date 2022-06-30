package com.rightfindpro.become.repository;

import com.rightfindpro.become.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
