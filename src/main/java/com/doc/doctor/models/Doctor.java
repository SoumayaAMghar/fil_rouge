package com.doc.doctor.models;

import jakarta.persistence.Column;
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

    @Column(length = 14)
    private String phone;
    private String picture;
    private String speciality;
    @OneToMany(mappedBy = "doctor")
    private List<Post> posts;
}
