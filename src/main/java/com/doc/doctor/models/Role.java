package com.doc.doctor.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
   /*
    @ManyToMany(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST,CascadeType.MERGE},
            mappedBy = "roles")
    private List<User> users = new ArrayList<>();
*/
}
