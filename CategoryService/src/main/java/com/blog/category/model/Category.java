package com.blog.category.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String category_Id;
    private String category_Name;
    @Column(length = 3000)
    private String category_Discription;

}
