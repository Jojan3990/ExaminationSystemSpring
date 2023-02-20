package com.rightfindpro.become.choice;

//import com.rightfindpro.become.domain.Choice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChoiceService {

    @Autowired
    ChoiceRepository choiceRepository;

    public List<Choice> getAllChoices() {
        return choiceRepository.findAll();
    }


    public Choice createNewChoice(Choice choice) {
        return choiceRepository.save(choice);
    }


    public List<Choice> findChoiceByQuestion(Integer questionId) {
        return choiceRepository.findAllByQuestion(questionId);
    }
    public void deleteChoiceById(int id) {
        choiceRepository.deleteById(id);
    }

    public List<Choice> getAllChoicesByQuestion(int id) {
        return choiceRepository.findAllChoiceByQuestionId(id);
    }
}

