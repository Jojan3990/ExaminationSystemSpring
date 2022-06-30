package com.rightfindpro.become.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class UserExam {

   @Id
    @ManyToOne
    @JoinColumn(name = "user_id_id")
    private User userId;


   @Id
    @ManyToOne
    @JoinColumn(name = "exam_id_id")
    private Exam examId;

    private Date completed_date;

    public Exam getExamId() {
        return examId;
    }

    public User getUserId() {
        return userId;
    }
}
