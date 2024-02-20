package com.blog.user.UserService.controller;

import com.blog.user.UserService.model.User;
import com.blog.user.UserService.services.UserService;
import com.blog.user.UserService.services.UserServiceInOkta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<?> getAllUser(Principal principal){
        return ResponseEntity.ok(this.userService.getAllUser());
    }







}
