package com.rightfindpro.become.exam;

import com.rightfindpro.become.question.QuestionResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamResponse {

    private Integer id;
    private String name;
    private String course;
    private QuestionResponse[] question;



}
