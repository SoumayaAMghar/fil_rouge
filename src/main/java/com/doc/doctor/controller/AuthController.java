package com.doc.doctor.controller;

import com.doc.doctor.dto.AuthRequestDto;
import com.doc.doctor.dto.AuthResponseDto;
import com.doc.doctor.dto.RegisterDto;
import com.doc.doctor.models.Doctor;
import com.doc.doctor.models.Role;
import com.doc.doctor.repository.RoleRepository;
import com.doc.doctor.repository.DoctorRepository;
import com.doc.doctor.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private DoctorRepository doctorRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(
            AuthenticationManager authenticationManager,
            DoctorRepository doctorRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.doctorRepository = doctorRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;

    }
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        if (doctorRepository.existsByEmail(registerDto.getEmail()))
        {
            return new ResponseEntity<>("Email is taken! ", HttpStatus.BAD_REQUEST);
        }

        Doctor user =new Doctor();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
        user.setPhone(registerDto.getPhone());
        user.setSpeciality(registerDto.getSpeciality());
        user.setPicture(registerDto.getPicture());

        Role roles= roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        doctorRepository.save(user);
        return new ResponseEntity<>("User registred success!", HttpStatus.OK);
    }
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getEmail(),
                        authRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

}

