package com.rightfindpro.become.question;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rightfindpro.become.choice.Choice;
import com.rightfindpro.become.exam.Exam;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String question;

//    @JsonBackReference(value = "exam-question")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "exam_question",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id"))
    private Set<Exam> exams=new HashSet<>();

    @JsonManagedReference(value = "question-choice")
    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private Set<Choice> choices = new HashSet<>();


    public Question() {

    }

    public void addExam(Exam exam){
        this.exams.add(exam);
        exam.getQuestions().add(this);
    }

    public Set<Choice> getChoices(){
        return choices;
    }
}
