package com.rightfindpro.become.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Choice {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double score;


}
