package com.rightfindpro.become.dto.Choice;

import com.rightfindpro.become.domain.Choice;
import com.rightfindpro.become.domain.Question;
import com.rightfindpro.become.dto.Question.QuestionDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ChoiceResponse {
    private Integer id;
    private String name;
    private Double score;



}
