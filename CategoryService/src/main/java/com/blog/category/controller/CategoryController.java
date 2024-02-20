package com.blog.category.controller;

import com.blog.category.model.Category;
import com.blog.category.services.CatregoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
     private CatregoryServices catregoryServices;

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category category1 = this.catregoryServices.addCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(category1);
    }


    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> categories = this.catregoryServices.getAllCategory();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{category_Id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("category_Id") String category_Id){

        Category category = this.catregoryServices.getCategoryById(category_Id);
        return ResponseEntity.ok(category);

    }


}
