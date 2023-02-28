package com.rightfindpro.become.exam;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rightfindpro.become.choice.Choice;
import com.rightfindpro.become.course.Course;
import com.rightfindpro.become.question.Question;
import com.rightfindpro.become.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
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

//    @JsonIgnore
    @JsonBackReference(value = "course-exam")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "course_id",nullable = false, referencedColumnName = "id")
    private Course course;

//    @JsonIgnore
//    @JsonManagedReference(value = "exam-user")
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE}, mappedBy = "exams")
//    @JoinColumn(name = "user_id",nullable = false,referencedColumnName = "id")
    @JsonIgnore
    private Set<User> users=new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE,mappedBy = "exams")
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();

//    @Column(nullable = true)
//    @JsonBackReference(value = "choice-exam")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE,mappedBy = "exams")
    private Set<Choice> choices;

    public Exam() {
    }



    public Set<User> getUsers(){
        return users;
    }

    public Set<Question> getQuestions(){
        return questions;
    }


//
//    public void setUsers(Set<User> users){
//        this.users=users;
//    }
}

