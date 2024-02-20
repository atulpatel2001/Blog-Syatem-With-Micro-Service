package com.blog.category.services;

import com.blog.category.model.Category;

import org.springframework.stereotype.Service;

import java.util.List;


public interface CatregoryServices {




    public Category addCategory(Category category);

    public List<Category> getAllCategory();

    public Category getCategoryById(String category_Id);
}
