package com.blog.blogapp.service;

import com.blog.blogapp.dao.Post;
import com.blog.blogapp.dto.PostDto;
import com.blog.blogapp.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto post,int categoryid,int userId);
    PostDto getpost(int postId);
    PostResponse getAllPost(int pageNumber, int pageSize,String sortBy);
    PostDto upadatePost(PostDto post , int postId);
    void deletePost(int postId);
    List<PostDto> getPostsByCategory(int categoryId);
    List<PostDto> getPostsByUser(int userId);
    List<PostDto> searchPost(String keyword);
}
