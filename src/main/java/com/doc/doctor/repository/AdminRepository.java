package com.doc.doctor.repository;

import com.doc.doctor.models.Admin;
import com.doc.doctor.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByEmail(String email);
}
