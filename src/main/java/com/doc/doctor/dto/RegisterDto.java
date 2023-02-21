package com.doc.doctor.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String email;
    private String password;
    private String phone;
    private String speciality;
    private String picture;
}
