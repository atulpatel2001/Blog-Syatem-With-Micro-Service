package com.blog.user.UserService.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtRequest {
    private String userEmail;
    private String userPassword;
}