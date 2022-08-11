package com.rightfindpro.become.controller;

import com.rightfindpro.become.dto.PageDto;
import com.rightfindpro.become.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping("/user-list")
    public ResponseEntity<PageDto> getAllUsers(
            @RequestParam(defaultValue = "user") String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        return userService.findAllUsers(page,size);
    }
/*
    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        userService.save(user);
    }*/

  //  @PutMapping("/edit")
   /* public ResponseEntity<UserInfoResponse> updateUserInfo(@AuthenticationPrincipal User user,
                                                           @Valid @RequestBody UpdateUserRequest request,
                                                           BindingResult bindingResult) {
        return ResponseEntity.ok(userMapper(user.getUsername(), request, bindingResult));
    }
*/

}



