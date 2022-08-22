package com.rightfindpro.become.service;


import com.rightfindpro.become.Exception.ApiRequestException;
import com.rightfindpro.become.domain.Role;
import com.rightfindpro.become.domain.User;
import com.rightfindpro.become.dto.PageDto;
import com.rightfindpro.become.mapper.PageDtoMapper;
import com.rightfindpro.become.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       /* try {
           Optional <User> user = userRepository.findByUsername(username);
           user = user.ifPresentOrElse(user1->user=Optional.of(user1),()->userRepository.findByEmail(username));


            return UserDetailsImpl.build(user);
        } catch (UsernameNotFoundException un) {
            try {
                Optional<User> user = userRepository.findByEmail(username);
            } catch (UsernameNotFoundException em) {


            }
        }*/
        User user = userRepository.getUserByEmailOrUserName(username)
                .orElseThrow(() -> new ApiRequestException("Username not found: " , HttpStatus.NOT_FOUND));


        return UserDetailsImpl.build(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
    public Optional<User> getUserById(Integer Id) {
        return userRepository.findById(Id);
    }
    public ResponseEntity<PageDto> findAllUsers(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);

            PageDtoMapper<User> pageDtoMapper = new PageDtoMapper<>();
            Page<User> userPage = userRepository.findAll(pageable);
            PageDto pageDto = pageDtoMapper.toPageResponse(userPage);
            return new ResponseEntity<>(pageDto, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Transactional
    public User updateUserInfo(String emailorUsername, User user) {
        User userFromDb = userRepository.getUserByEmailOrUserName(emailorUsername).orElseThrow(() ->new UsernameNotFoundException("User Not Found with email: " + emailorUsername));
        userFromDb.setName(user.getName());
        return userFromDb;
    }






}



