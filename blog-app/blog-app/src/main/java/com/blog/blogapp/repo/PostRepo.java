package com.blog.blogapp.repo;

import com.blog.blogapp.dao.Category;
import com.blog.blogapp.dao.Post;
import com.blog.blogapp.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post ,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
}
