package com.rightfindpro.become.user;

import com.rightfindpro.become.GenericMapper;
import com.rightfindpro.become.exam.Exam;
import com.rightfindpro.become.exam.ExamUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements GenericMapper<User,UserExamDTO> {
    public UserExamDTO toDto(User user) {
        UserExamDTO userExamDTO = new UserExamDTO();
        userExamDTO.setId(user.getId());
        userExamDTO.setName(user.getName());
        return userExamDTO;
    }
}
