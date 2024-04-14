package com.blog.blogapp.dto;

import com.blog.blogapp.dao.Category;
import com.blog.blogapp.dao.User;
import lombok.Data;

import java.util.Date;

@Data
public class PostDto {
    private String postTitle;
    private String postContent;
    private Date addedDate;
    private String imageName;
    private User user;
    private Category category;

}
