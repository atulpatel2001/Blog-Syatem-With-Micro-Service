package com.blog.content.services;

import com.blog.content.model.Post;

import java.util.List;

public interface PostServices {

    public Post savePost(Post post);

    public List<Post> getAllPost();

    public List<Post> getPostByCategoryId(String category_Id);

    public Post getPostById(String postId);

    public List<Post> getPostByUserId(String userId);
}
