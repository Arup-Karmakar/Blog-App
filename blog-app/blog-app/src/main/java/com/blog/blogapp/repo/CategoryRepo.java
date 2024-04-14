package com.blog.blogapp.repo;

import com.blog.blogapp.dao.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category , Integer> {
}
