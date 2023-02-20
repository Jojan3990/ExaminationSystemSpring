package com.rightfindpro.become.security;

//import com.rightfindpro.become.domain.Role;
import com.rightfindpro.become.user.Role;
import com.rightfindpro.become.user.User;
import com.rightfindpro.become.user.SignUpDto;
import com.rightfindpro.become.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class SignUpDtoMapper {


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    public SignUpDtoMapper(PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User toUser(SignUpDto signUpDto) {
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        System.out.println("This is in signupdtomapper1");
        user.setEmail(signUpDto.getEmail());
        System.out.println("This is in signupdtomapper2");
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        System.out.println("This is in signupdtomapper3");
        Role role = roleRepository.findByName("user").get();
        user.setRoles(Collections.singleton(role));
        return user;
    }

}
