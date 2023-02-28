package com.rightfindpro.become.user;


import com.jayway.jsonpath.MapFunction;
import com.rightfindpro.become.PageDto;
import com.rightfindpro.become.exam.Exam;
import com.rightfindpro.become.exam.ExamMapper;
import com.rightfindpro.become.exam.ExamUserDTO;
import liquibase.pro.packaged.E;
import liquibase.pro.packaged.H;
import liquibase.pro.packaged.R;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Autowired
    ExamMapper examMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    @Lazy
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            System.out.println("The mail " + user.getEmail() + " is already in use");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    /*
     * Look up by both Email and Username. Throw exception if it wasn't in
     * either. TODO: Join Username and Email into one JPQL
     */
//    @Override
    @Transactional
    @Override
    public AuthenticatedUser loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        User user;
        try {
            user = findByUsername(username);
            user.setId(user.getId());
        } catch (UsernameNotFoundException e) {
            try {
                user = findByEmail(username);
            } catch (UsernameNotFoundException e2) {
                throw new UsernameNotFoundException(username + " couldn't be resolved to any user");
            }
        }

        return new AuthenticatedUser(user);
//        return new UserDetailsImpl(user);
    }

    public User findByUsername(String username) {
        System.out.println(username);
        User user = userRepository.findByUsername(username);


        if (user == null) {
            System.out.println("User not found");
        }

        return user;
    }

    public Optional<User> find(Integer id) {
        Optional<User> user = userRepository.findById(id);

        if (user == null) {
            System.out.println("User " + id + " not found.");
        }

        return user;
    }

//    public void delete(Integer user_id)  {
//        Optional<User> userToDelete = find(user_id);
//
//        userRepository.delete(userToDelete);
//    }


    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            System.out.println("User not found");
        }
        return user;
    }


    public User updatePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public ResponseEntity<PageDto> findAllUsers(int page, int size) {
        return findAllUsers(page, size);
    }


    public ResponseEntity<?> getExamsNameByUserId(int user_id) {
        User user = userRepository.findUserNameById(user_id);
        if (user != null) {
            List<Exam> listStore = userRepository.getExamsNameByUserId(user_id);
            if (listStore.isEmpty() != true) {
                return new ResponseEntity<>(listStore.stream().map(examMapper::toDto).collect(Collectors.toList()), HttpStatus.OK);
            }
            return new ResponseEntity<>("User with id " + user_id + " has not given any exam", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("user with id " + user_id + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
