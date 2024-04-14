package com.blog.blogapp.repo;

import com.blog.blogapp.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
