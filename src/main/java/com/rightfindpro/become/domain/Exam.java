package com.rightfindpro.become.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @OneToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course courseId;


    @ManyToMany
    @JoinTable(name = "exam_question",
            joinColumns = @JoinColumn(name = "exam_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"))
    private Set<Question> questions = new HashSet<>();

}
