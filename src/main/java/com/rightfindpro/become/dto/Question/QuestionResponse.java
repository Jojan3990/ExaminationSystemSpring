package com.rightfindpro.become.dto.Question;

import com.rightfindpro.become.domain.Choice;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
public class QuestionResponse {
    private Integer id;
    private String question;
    private int score;

}
