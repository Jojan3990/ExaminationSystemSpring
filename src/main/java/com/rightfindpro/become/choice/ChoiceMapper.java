package com.rightfindpro.become.choice;

import com.rightfindpro.become.domain.Choice;
import com.rightfindpro.become.choice.ChoiceResponse;
import com.rightfindpro.become.service.ChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChoiceMapper {


    private ChoiceService choiceService;


    public List<ChoiceResponse> toDto(List<Choice> choices) {
        List<ChoiceResponse> choiceResponses = new ArrayList<>();
        for (Choice choice : choices) {
            ChoiceResponse choiceResponse = toDto(choice);
            choiceResponses.add(choiceResponse);
        }
        return choiceResponses;

    }

    public static ChoiceResponse toDto(Choice choice) {
        ChoiceResponse choiceResponse = new ChoiceResponse();
        choiceResponse.setId(choice.getId());
        choiceResponse.setName(choice.getName());
        choiceResponse.setScore(choice.getScore());
        return choiceResponse;
    }

    //todo
    /*public static Choice fromDto(ChoiceResponse choiceResponse) {
        Choice choice = new Choice(choiceResponse);
        return choice;
    }*/
  /* // private static String setQuestions(Question question) {
        return question.getQuestion();*/
}




