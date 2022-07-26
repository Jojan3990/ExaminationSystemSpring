package com.rightfindpro.become.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    @ManyToMany(mappedBy = "questions")
    private Set<Exam> exams;


    public Question() {

    }
}
