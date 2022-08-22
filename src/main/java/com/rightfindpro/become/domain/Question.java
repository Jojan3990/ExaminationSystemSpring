package com.rightfindpro.become.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "questions")
    private Set<Exam> exams;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question")
    private Set<Choice> choices = new HashSet<>();


    public Question() {

    }
}
