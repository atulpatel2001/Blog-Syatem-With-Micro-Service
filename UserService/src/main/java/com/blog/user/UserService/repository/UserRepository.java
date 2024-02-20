package com.blog.user.UserService.repository;

import com.blog.user.UserService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    public User findByUserEmail(String email);
}
