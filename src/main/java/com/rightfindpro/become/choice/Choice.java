package com.rightfindpro.become.choice;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rightfindpro.become.exam.Exam;
import com.rightfindpro.become.question.Question;
import com.rightfindpro.become.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Choice {

    @Id
    @GeneratedValue
    private Integer id;

    @JsonBackReference(value ="question-choice" )
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double score;

//    @JsonManagedReference(value = "choice-user")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_choice",
            joinColumns = @JoinColumn(name = "choice_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users = new HashSet<>();

//    @JsonManagedReference(value = "choice-exam")
//    @JsonIdentityInfo()
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_exam",
            joinColumns = @JoinColumn(name = "choice_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id", referencedColumnName = "id"))
    private Set<Exam> exams = new HashSet<>();



}
