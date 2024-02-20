package com.blog.content.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;


@Entity
@Table(name = "tbl_Post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String post_Id;
    private String post_Title;
    @Column(length = 6000)
    private String post_Content;
    @Column(length = 6000)
    private String post_Code;
    private String post_Pic;
    private LocalDateTime post_Add_DateTime;
    private String categoryId;
    private String userId;

}
