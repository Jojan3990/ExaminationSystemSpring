package com.rightfindpro.become.service;

import com.rightfindpro.become.domain.Choice;
import com.rightfindpro.become.repository.ChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ChoiceService {

    @Autowired
    ChoiceRepository choiceRepository;

    public List<Choice> getAllCourses() {
        return choiceRepository.findAll();
    }


    public Choice createNewChoice(Choice choice) {
        return choiceRepository.save(choice);
    }


    public void deleteChoice(Integer id) {
        choiceRepository.deleteById(id);
    }

    public List<Choice> findChoiceByQuestion(Integer questionId) {
        return choiceRepository.findAllByQuestion(questionId);
    }
}

