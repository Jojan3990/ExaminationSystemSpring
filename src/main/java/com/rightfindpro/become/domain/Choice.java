package com.rightfindpro.become.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
public class Choice {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "question")
    private Question question;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double score;

    @ManyToMany
    @JoinTable(name = "user_choice",
            joinColumns = @JoinColumn(name = "choice_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<Role> users;

    @ManyToMany
    @JoinTable(name = "user_choice",
            joinColumns = @JoinColumn(name = "choice_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id", referencedColumnName = "id"))
    private Set<Role> exams;


}
