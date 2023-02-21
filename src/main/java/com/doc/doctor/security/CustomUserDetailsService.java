package com.doc.doctor.security;

import com.doc.doctor.models.Doctor;
import com.doc.doctor.models.Role;
import com.doc.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private DoctorRepository doctorRepository;

    @Autowired
    public CustomUserDetailsService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        System.out.println("am in loadUserByUsername now ^_^");
//        com.doc.doctor.models.Doctor user = doctorRepository.findByEmail(email).isPresent()
//                ? doctorRepository.findByEmail(email).get()
//                : doctorRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("no user found with this email: " + email));
//
//        return new User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles())
////                new ArrayList<>() {{add(new SimpleGrantedAuthority(user.getRole().name()));}}
//        );
//    }
//    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles){
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//    }
@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Doctor doc = doctorRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("no user found with this email: " + email));

    return new User(doc.getEmail(),doc.getPassword(), mapRolesToAuthorities(doc.getRoles()));
}

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
