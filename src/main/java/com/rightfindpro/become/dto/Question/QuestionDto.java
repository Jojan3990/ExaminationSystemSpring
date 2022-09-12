package com.rightfindpro.become.dto.Question;


import com.rightfindpro.become.domain.Question;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class QuestionDto {
    private Integer id;
    private @NotNull String question;

   /* public QuestionDto(Question question) {
        this.setId(question.getId());
        this.setQuestion(question.getQuestion());
    }*/

    /*public QuestionDto(@NotNull String question) {
        this.question = question;
    }*/

}
