package com.rightfindpro.become.dto.Exam;

import com.rightfindpro.become.domain.Question;
import com.rightfindpro.become.dto.Question.QuestionResponse;
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
