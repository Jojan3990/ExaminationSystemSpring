package com.rightfindpro.become.question;


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
