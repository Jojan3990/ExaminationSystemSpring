package com.rightfindpro.become.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Override
    public String toString() {
        return this.name;
    }

}
