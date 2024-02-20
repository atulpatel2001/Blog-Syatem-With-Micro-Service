package com.blog.user.UserService.services;

import com.blog.user.UserService.model.User;

import java.util.List;


public interface UserService {


    public User createUser(User user);

    public User findByEmail(String email);


    public List<User> getAllUser();

}
