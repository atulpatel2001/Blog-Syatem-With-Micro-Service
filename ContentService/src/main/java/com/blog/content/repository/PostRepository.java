package com.blog.content.repository;

import com.blog.content.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,String> {

    public List<Post> findByCategoryId(String categoryId);

    public List<Post> findByUserId(String userId);
}
