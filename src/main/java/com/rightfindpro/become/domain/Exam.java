package com.rightfindpro.become.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "course", referencedColumnName = "id")
    private Course course;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "exam_question",
            joinColumns = @JoinColumn(name = "exam_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"))
    private Set<Question> questions = new HashSet<>();

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "exams")
    private Set<Choice> choices;

    public Exam() {

    }
}
