package com.rightfindpro.become.exam;

import com.rightfindpro.become.GenericMapper;
import com.rightfindpro.become.question.Question;
import com.rightfindpro.become.question.QuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExamMapper implements GenericMapper<Exam,ExamUserDTO> {




    public ExamUserDTO toDto(Exam exam) {
        ExamUserDTO examUserDTO = new ExamUserDTO();
        examUserDTO.setId(exam.getId());
        examUserDTO.setName(exam.getName());
        return examUserDTO;
    }
}
