package com.doc.doctor.models;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NonNull
    @Column(length = 25)
    protected String username;
    @NonNull
    @Column(length = 80)
    protected String email;
    @NonNull
    protected String password;
}
