package com.blog.category.services;

import com.blog.category.exception.ResourceNotFoundException;
import com.blog.category.model.Category;
import com.blog.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CatregoryServices {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(String category_Id) {
        Category category=this.categoryRepository.findById(category_Id).orElseThrow(() -> new ResourceNotFoundException("Category with given id not found on server!!:-" + category_Id));
        return category;
    }
}
