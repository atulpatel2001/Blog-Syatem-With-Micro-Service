package com.blog.user.UserService.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userPhoneNumber;
    private String userGender;
    private LocalDateTime dateTime;
    private String about;

    private String userRole;

}
