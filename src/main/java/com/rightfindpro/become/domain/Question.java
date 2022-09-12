package com.rightfindpro.become.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rightfindpro.become.dto.Question.QuestionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Question {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String question;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "questions")
    private Set<Exam> exams;

    @JsonBackReference
    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private Set<Choice> choices = new HashSet<>();


    public Question() {

    }


}
