package com.blog.user.UserService.controller;

import com.blog.user.UserService.model.JwtRequest;
import com.blog.user.UserService.model.JwtResponse;
import com.blog.user.UserService.model.User;
import com.blog.user.UserService.security.JwtHelper;
import com.blog.user.UserService.security.UserDetailServiceImple;
import com.blog.user.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setUserPassword(this.bCryptPasswordEncoder.encode(user.getUserPassword()));
        user.setDateTime(LocalDateTime.now());
        user.setUserRole("ROLE_USER");
        User user1 = this.userService.createUser(user);


        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        System.out.println(request);
        this.doAuthenticate(request.getUserEmail(), request.getUserPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserEmail());
        String token = this.helper.generateToken(userDetails);
        System.out.println(token);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .userName(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }
}
