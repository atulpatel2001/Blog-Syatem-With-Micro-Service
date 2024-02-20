package com.blog.content.controller;

import com.blog.content.helper.FileUploadHelper;
import com.blog.content.model.ApiResponse;
import com.blog.content.model.Post;

import com.blog.content.services.PostServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostServices postServices;
    Logger logger= LoggerFactory.getLogger(PostController.class);

    @Autowired
    private ObjectMapper mapper;

    @PostMapping
    public ResponseEntity<Map<String,String>> savePost(@RequestParam("file") MultipartFile file, @RequestParam("post") String postData) throws JsonProcessingException {


        Post post= mapper.readValue(postData,Post.class);

       post.setPost_Pic(file.getOriginalFilename());
       post.setPost_Add_DateTime(LocalDateTime.now());

        boolean b = FileUploadHelper.uploadFile(file, "static/post-image");
        Map<String,String> map=new HashMap<>();
        if(b){
            Post post1 = this.postServices.savePost(post);
            logger.info("Success");
            map.put("message","success");
            map.put("post", String.valueOf(post1));
            return ResponseEntity.status(HttpStatus.CREATED).body(map);
        }
        else{
            map.put("message","error");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }


    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPost(){
        return ResponseEntity.ok(this.postServices.getAllPost());
    }

    @GetMapping("/{post_id}")
    public ResponseEntity<Post> getPostById(@PathVariable("post_id") String post_id){
        return ResponseEntity.ok(this.postServices.getPostById(post_id));
    }

    @GetMapping("/category/{category_Id}")
    public ResponseEntity<List<Post>> getPostByCategory(@PathVariable("category_Id") String category_Id){
        return ResponseEntity.ok(this.postServices.getPostByCategoryId(category_Id));
    }


    @GetMapping("/user/{user_Id}")
    public ResponseEntity<List<Post>> getPostByUserId(@PathVariable("user_Id") String userId){
        return ResponseEntity.ok(this.postServices.getPostByUserId(userId));
    }

}
