package com.rightfindpro.become.user;

import com.rightfindpro.become.exam.Exam;
import com.rightfindpro.become.PageDto;
import com.rightfindpro.become.exam.ExamService;
import com.rightfindpro.become.exam.ExamUserDTO;
import liquibase.pro.packaged.E;
import liquibase.pro.packaged.M;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    ExamService examService;
    @Autowired
    private UserRepository userRepository;





    //this is for test

    @GetMapping("/user-list")
    public ResponseEntity<PageDto> getAllUsers(
            @RequestParam(defaultValue = "user") String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size
    ) {
        return userService.findAllUsers(page,size);
    }

    @GetMapping("/userByEmail/{email}")
    public User getUserByEmail(@PathVariable(value = "email") String email){
        return userService.findByEmail(email);
    }

    @GetMapping("/userByUsername/{username}")
    public ResponseEntity<?>  getUserByUserName(@PathVariable(value = "username") String username){
//        System.out.println("This is null ?");
//        System.out.println(username);
        return new ResponseEntity<>(userService.findByUsername(username),HttpStatus.OK);
    }

//    @RequestMapping(value = "/{user_id}/exams", method = RequestMethod.GET)
//    @PreAuthorize("permitAll")
//    @ResponseStatus(HttpStatus.OK)
//    public Page<Exam> getExamsByUser(Pageable pageable, @PathVariable Integer user_id) {
//
//        //User user = userService.find(user_id);
//        return examService.findExamsByUser(user_id, pageable);
//    }

    @GetMapping("/getTotalExamsByUser/{user_id}")
    public ResponseEntity<?> getTotalExamsByUserId(@PathVariable Integer user_id){
        User user= userRepository.findUserNameById(user_id);
        if(user!=null){
            String username=user.getName();
            return new ResponseEntity<>(username+" has given total of "+userRepository.getTotalExamByUserId(user_id)+" exams",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("user with id "+user_id+" not found",HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/getExamsNameByUserId/{user_id}") //documentation is important so do documentation
    public ResponseEntity<?> getExamsNameByUserId(@PathVariable Integer user_id){
        return userService.getExamsNameByUserId(user_id);
    }

//    @RequestMapping(value = "/myExams", method = RequestMethod.GET)
//    @PreAuthorize("isAuthenticated()")
//    @ResponseStatus(HttpStatus.OK)
//    public Page<Exam> getExamsByCurrentUser(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
//                                              Pageable pageable) {
//
//        return getExamsByUser(pageable, authenticatedUser.getId());
//    }
    @PostMapping("/users")
    void addUser(@RequestBody User user) {
//        System.out.println("This is working");
        userService.saveUser(user);
    }

  //  @PutMapping("/edit")
   /* public ResponseEntity<UserInfoResponse> updateUserInfo(@AuthenticationPrincipal User user,
                                                           @Valid @RequestBody UpdateUserRequest request,
                                                           BindingResult bindingResult) {
        return ResponseEntity.ok(userMapper(user.getUsername(), request, bindingResult));
    }
*/
}



