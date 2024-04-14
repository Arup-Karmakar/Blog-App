package com.blog.blogapp.controller;

import com.blog.blogapp.dto.PostDto;
import com.blog.blogapp.payload.ApiResponse;
import com.blog.blogapp.payload.PostResponse;
import com.blog.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/create-post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable int userId,
                                              @PathVariable int categoryId){
      PostDto createPost = this.postService.createPost(postDto,userId,categoryId);
      return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int userId){
        List<PostDto> postDto = this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int categoryId){
        List<PostDto> postDtos = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable int postId){
        PostDto post = this.postService.getpost(postId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @GetMapping("/allposts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber" , defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize" , defaultValue = "10") int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "postId") String sortBy){
        PostResponse posts = this.postService.getAllPost(pageNumber,pageSize,sortBy);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                              @PathVariable int postId){
        PostDto posts = this.postService.upadatePost(postDto,postId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @DeleteMapping("/delete-post/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable int postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(
                new ApiResponse("Post deleted",true),HttpStatus.OK);
    }

    @GetMapping("/search-post/{keyword}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keyword){
        List<PostDto> search = this.postService.searchPost(keyword);
        return new ResponseEntity<>(search , HttpStatus.OK);
    }


}


