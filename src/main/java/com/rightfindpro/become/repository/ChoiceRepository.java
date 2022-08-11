package com.rightfindpro.become.repository;

import com.rightfindpro.become.domain.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Integer> {
    public List<Choice> findAllByQuestion(Integer id);
    public Choice findAllById(Integer id);

    public void deleteChoiceByExamsIsNull();






}
