package com.blog.blogapp.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    private String postTitle;
    private String postImageName;
    private String postContent;
    private Date addedDate;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;

}
