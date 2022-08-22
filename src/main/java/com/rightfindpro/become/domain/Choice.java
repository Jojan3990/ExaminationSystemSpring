package com.rightfindpro.become.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
public class Choice {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double score;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_choice",
            joinColumns = @JoinColumn(name = "choice_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_choice",
            joinColumns = @JoinColumn(name = "choice_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id", referencedColumnName = "id"))
    private Set<Exam> exams = new HashSet<>();



}
