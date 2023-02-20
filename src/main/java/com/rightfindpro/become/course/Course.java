package com.rightfindpro.become.course;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rightfindpro.become.exam.Exam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @JsonManagedReference(value = "course-exam")
    @OneToMany(mappedBy = "course",cascade = CascadeType.MERGE)
//    @JoinColumn(name = "course_id")
    private Set<Exam> exams;

    public Course() {

    }


}
