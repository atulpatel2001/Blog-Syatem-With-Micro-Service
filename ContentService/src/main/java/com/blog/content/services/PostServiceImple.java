package com.blog.content.services;

import com.blog.content.exception.ResourceNotFoundException;
import com.blog.content.model.Post;
import com.blog.content.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImple implements PostServices {
    @Autowired
    private PostRepository postRepository;
    @Override
    public Post savePost(Post post) {
        Post post1 = this.postRepository.save(post);
        return post1;
    }

    @Override
    public List<Post> getAllPost() {
              return this.postRepository.findAll();
    }

    @Override
    public List<Post> getPostByCategoryId(String category_Id) {
        List<Post> posts = this.postRepository.findByCategoryId(category_Id);
        return posts;
    }

    @Override
    public Post getPostById(String postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post with given id not found on server!!:-" + postId));
        return post;
    }

    @Override
    public List<Post> getPostByUserId(String userId) {
        List<Post> post = this.postRepository.findByUserId(userId);
        return post;
    }
}
