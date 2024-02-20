package com.blog.user.UserService.services;

import com.blog.user.UserService.model.User;
import com.blog.user.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByUserEmail(email);

    }

    @Override
    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }
}
