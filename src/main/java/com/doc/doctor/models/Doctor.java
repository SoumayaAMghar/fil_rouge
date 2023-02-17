package com.doc.doctor.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Doctor extends User{
    private Integer phone;
    private String picture;
    private String speciality;
    @OneToMany(mappedBy = "doctor")
    private List<Post> posts;
}
