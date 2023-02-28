package com.rightfindpro.become.user;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rightfindpro.become.choice.Choice;
import com.rightfindpro.become.exam.Exam;
import liquibase.pro.packaged.S;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "user_all", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Column(nullable = false)
    private String password;

    //    @JsonManagedReference(value = "user-role")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    //        @JsonBackReference(value = "choice-user")
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private Set<Choice> choices;

    //        @JsonIgnore
//        @JsonBackReference(value = "exam-user")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_exam", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "exam_id"))
    private Set<Exam> exams = new HashSet<>();

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void addExam(Exam exam) {
        this.exams.add(exam);
        exam.getUsers().add(this);
    }

}
