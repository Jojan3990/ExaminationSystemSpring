package com.rightfindpro.become.service;


import com.rightfindpro.become.user.AuthenticatedUser;
import com.rightfindpro.become.user.User;
import com.rightfindpro.become.dto.PageDto;
import com.rightfindpro.become.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class UserService  {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user){
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
    public AuthenticatedUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;

        try {
            user = findByUsername(username);
        } catch (UsernameNotFoundException e) {
            try {
                user = findByEmail(username);
            } catch (UsernameNotFoundException e2) {
                throw new UsernameNotFoundException(username + " couldn't be resolved to any user");
            }
        }

        return new AuthenticatedUser(user);
    }

    public User findByUsername(String username){
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

    public void delete(Integer user_id)  {
        Optional<User> userToDelete = find(user_id);

        userRepository.delete(userToDelete);
    }






    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            System.out.println("User not found");
        }

        return user;
    }


    public User updatePassword(User user, String password)  {
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public ResponseEntity<PageDto> findAllUsers(int page, int size) {
        return findAllUsers(page,size);
    }
}
