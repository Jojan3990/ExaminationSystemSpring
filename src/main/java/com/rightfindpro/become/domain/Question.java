package com.rightfindpro.become.domain;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String question;

    @ManyToMany(mappedBy = "questions")
    private Set<Exam> exams;


}
