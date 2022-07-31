package com.rightfindpro.become.mapping;

import com.rightfindpro.become.domain.Role;
import com.rightfindpro.become.domain.User;
import com.rightfindpro.become.dto.SignUpDto;
import com.rightfindpro.become.repository.RoleRepository;
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
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role role = roleRepository.findByName("user").get();
        user.setRoles(Collections.singleton(role));
        return user;
    }



}
