package com.doc.doctor.controller;

import com.doc.doctor.dto.RegisterDto;
import com.doc.doctor.models.Doctor;
import com.doc.doctor.models.Role;
import com.doc.doctor.repository.RoleRepository;
import com.doc.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("api/auth")
public class AuthController {
//    private AuthenticationManager authenticationManager;
    private DoctorRepository doctorRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(
//            AuthenticationManager authenticationManager,
            DoctorRepository doctorRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//        this.authenticationManager = authenticationManager;
        this.doctorRepository = doctorRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        if (doctorRepository.existsByEmail(registerDto.getEmail())
//                || docotorRepository.existsAdoptantByEmail(registerDto.email())
        ) {
            return new ResponseEntity<>("Email is taken! ", HttpStatus.BAD_REQUEST);
        }
//        System.out.println(registerDto.getrole());
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


//        if (registerDto.role().equals(UserRoles.ADOPTANT)) {
//            Adoptant adoptant = new Adoptant(registerDto.name(), registerDto.email(), passwordEncoder.encode(registerDto.password()), registerDto.address(), registerDto.tel(), registerDto.nbr_animaux(), registerDto.role());
//            adoptantRepository.save(adoptant);
//        } else {
//            Client client = new Client(registerDto.name(), registerDto.email(), passwordEncoder.encode(registerDto.password()), registerDto.address(), registerDto.tel(), registerDto.role());
//            clientRepository.save(client);
//        }
//        return new ResponseEntity<>("User registred succesfully", HttpStatus.OK);
    }
}

