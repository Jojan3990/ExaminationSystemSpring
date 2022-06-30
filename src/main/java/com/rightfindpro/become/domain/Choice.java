package com.rightfindpro.become.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter

@Entity
public class Choice {

    @Id
    @GeneratedValue
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "question_id_id")
    private Question questionId;

    @Column(nullable = false)
    private String choice;

    @Column(nullable = false)
    private double score;

    public Question getQuestionId() {
        return questionId;
    }


}
