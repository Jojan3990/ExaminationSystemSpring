package com.rightfindpro.become.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "user_exam", schema = "public")
public class UserExam {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;


    @ManyToOne
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    private Exam examId;

    private Date completedDate;


}
