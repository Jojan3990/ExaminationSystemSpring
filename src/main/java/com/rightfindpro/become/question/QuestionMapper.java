package com.rightfindpro.become.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionMapper {

    private QuestionService questionService;


    public List<QuestionDto> toDto(List<Question> questions) {
        List<QuestionDto> questionDtos = new ArrayList<>();
        for (Question question : questions) {
            QuestionDto questionDto = toDto(question);
            questionDtos.add(questionDto);
        }
        return questionDtos;

    }

    public static QuestionDto toDto(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(question.getId());
        questionDto.setQuestion(question.getQuestion());
        return questionDto;
    }

    //todo
    /*public static Question fromDto(QuestionDto questionDto) {
        Question question = new Question(questionDto);
        return question;
    }*/
}
