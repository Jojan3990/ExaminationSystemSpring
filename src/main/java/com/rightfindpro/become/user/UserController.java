package com.rightfindpro.become.user;

import com.rightfindpro.become.user.AuthenticatedUser;
import com.rightfindpro.become.exam.Exam;
import com.rightfindpro.become.dto.PageDto;
import com.rightfindpro.become.service.ExamService;
import com.rightfindpro.become.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;
    ExamService examService;



    @GetMapping("/user-list")
    public ResponseEntity<PageDto> getAllUsers(
            @RequestParam(defaultValue = "user") String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        return userService.findAllUsers(page,size);
    }





    @RequestMapping(value = "/{user_id}/exams", method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    @ResponseStatus(HttpStatus.OK)
    public Page<Exam> getExamsByUser(Pageable pageable, @PathVariable Integer user_id) {

        //User user = userService.find(user_id);
        return examService.findExamsByUser(user_id, pageable);
    }

    @RequestMapping(value = "/myExams", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public Page<Exam> getExamsByCurrentUser(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                              Pageable pageable) {

        return getExamsByUser(pageable, authenticatedUser.getId());
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



