package com.rightfindpro.become.controller;

import com.rightfindpro.become.domain.User;
import com.rightfindpro.become.dto.PageDto;
import com.rightfindpro.become.mapping.PageDtoMapper;
import com.rightfindpro.become.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/user-list")
    public ResponseEntity<PageDto> getAllUsers(
            @RequestParam(defaultValue = "user") String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            Pageable paging = PageRequest.of(page, size);
            PageDtoMapper<User> pageDtoMapper = new PageDtoMapper();
            Page<User> pageUsers = userRepository.findAll(paging);
            PageDto pageDto = pageDtoMapper.toPageResponse(pageUsers);
            return new ResponseEntity<>(pageDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        userRepository.save(user);
    }


}



