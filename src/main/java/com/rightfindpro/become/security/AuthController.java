package com.rightfindpro.become.security;


import com.nimbusds.oauth2.sdk.auth.JWTAuthentication;
import com.rightfindpro.become.security.jwt.JwtUtils;
import com.rightfindpro.become.user.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SignUpDtoMapper signUpDtoMapper;

    @Autowired
    private LoginDto loginDto;

    @Autowired
    JwtUtils jwtUtils;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(HttpServletRequest req, @Valid @RequestBody LoginDto loginDto) throws AuthenticationException {

//        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                loginDto.getUsername(),loginDto.getPassword()
//        ));
        UsernamePasswordAuthenticationToken authReq=new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword());
        Authentication authentication=authenticationManager.authenticate(authReq);
        SecurityContext context=SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
//        HttpSession session=req.getSession(true);
//        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, context);

//        UserDetailsImple userDetails= (UserDetailsImple) authentication.getPrincipal();
//        System.out.println(userDetails.getId());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthenticatedUser userDetails = (AuthenticatedUser) authentication.getPrincipal();//
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
//        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//                .body(new UserInfoResponse(
//                        userDetails.getId(),
//                        userDetails.getUsername(),
//                        userDetails.getEmail(),
//                        roles
//                ));
        System.out.println("Working");
        return new ResponseEntity<>(jwtCookie,HttpStatus.OK);

//        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//                .body(userDetails.getUsername()+ roles);

//        return new ResponseEntity<>(authentication.getDetails()+ " "+authentication.getPrincipal(), HttpStatus.OK);
//        return new ResponseEntity<>( userDetails.getUsername() ,HttpStatus.OK);
//        return new ResponseEntity<>("  "+((UserDetails) authentication.getPrincipal()).getUsername(), HttpStatus.OK);
//        return new ResponseEntity<>(((UserDetailsImpl) principal).getUsername(), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        System.out.println("This is working");
        // add check for username exists in a DB
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        User user = signUpDtoMapper.toUser(signUpDto);
        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return new ResponseEntity<>("User logged out successfully", HttpStatus.OK);
    }


    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e)
    {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

//    private Authentication authenticate(String username, String password)
//    {
//        Objects.requireNonNull(username);
//        Objects.requireNonNull(password);
//
//        try {
//            /// ???
//            System.out.println("I am working");
//            Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
//            return authentication;
//        }
//        catch (DisabledException e)
//        {
//            logger.warn("UTENTE DISABILITATO");
////            throw new AuthenticationException("UTENTE DISABILITATO", e);
//        }
//        catch (BadCredentialsException e)
//        {
//            logger.warn("CREDENZIALI NON VALIDE");
////            throw new AuthenticationException("CREDENZIALI NON VALIDE", e);
//        }
//        catch (Exception e)
//        {
//            logger.warn("I dont know what is this");
//        }
//    }
}


