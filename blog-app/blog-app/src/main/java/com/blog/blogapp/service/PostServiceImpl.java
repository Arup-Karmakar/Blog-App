package com.blog.blogapp.service;

import com.blog.blogapp.dao.Category;
import com.blog.blogapp.dao.Post;
import com.blog.blogapp.dao.User;
import com.blog.blogapp.dto.CategoryDto;
import com.blog.blogapp.dto.PostDto;
import com.blog.blogapp.exception.ResourceNotFoundException;
import com.blog.blogapp.payload.PostResponse;
import com.blog.blogapp.repo.CategoryRepo;
import com.blog.blogapp.repo.PostRepo;
import com.blog.blogapp.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
  private PostRepo postRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto post,int categoryId, int userId) {
        User user = this.userRepo.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User","User Id",userId)
        );
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFoundException("Category","Category Id",categoryId)
        );

        Post createPost = this.modelMapper.map(post,Post.class);
        createPost.setAddedDate(new Date());
        createPost.setPostImageName("default.png");
        createPost.setUser(user);
        createPost.setCategory(category);

        PostDto newPost = this.modelMapper.map(createPost,PostDto.class);
        return newPost;
    }

    @Override
    public PostDto getpost(int postId) {
        Post getPost =  this.postRepo.findById(postId).
                orElseThrow(()-> new ResourceNotFoundException("Post","Post Id",postId));
        PostDto post = modelMapper.map(getPost,PostDto.class);
        return post;
    }

    @Override
    public PostResponse getAllPost(int pageNumber, int pageSize,String sortBy) {
        Pageable page = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Post> pagePost = this.postRepo.findAll(page);
        List<Post> allPost = pagePost.getContent();
        List<PostDto> posts = allPost.stream().map((
                post)-> modelMapper.map(post,PostDto.class)).
                collect(Collectors.toList());

        PostResponse resp = new PostResponse();
        resp.setContent(posts);
        resp.setPageNumber(pagePost.getNumber());
        resp.setPageSize(pagePost.getSize());
        resp.setTotalPage(pagePost.getTotalPages());
        resp.setTotalElement(pagePost.getTotalElements());
        resp.setLastPage(pagePost.isLast());

        return resp;
    }

    @Override
    public PostDto upadatePost(PostDto post, int postId) {
        Post posts = this.postRepo.findById(postId).
                orElseThrow(()-> new ResourceNotFoundException("Post","Post Id",postId));

        posts.setCategory(post.getCategory());
        posts.setPostTitle(posts.getPostTitle());

        PostDto update = this.modelMapper.map(posts,PostDto.class);
        return update;
    }

    @Override
    public void deletePost(int postId) {
        Post post = this.postRepo.findById(postId).
                orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getPostsByCategory(int categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).
                orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
        List<Post> posts = this.postRepo.findByCategory(cat);

        List<PostDto> postDto = posts.stream().map(
                (post)->modelMapper.map(post,PostDto.class)).
                collect(Collectors.toList());
        return postDto;
    }

    @Override
    public List<PostDto> getPostsByUser(int userId) {
        User user = this.userRepo.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User","User Id",userId));
        List<Post> posts = this.postRepo.findByUser(user);

        List<PostDto> postDto = posts.stream().map(
                (post)->modelMapper.map(post,PostDto.class)).
                collect(Collectors.toList());

        return postDto;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> search = this.postRepo.findByTitleContaining(keyword);
        List<PostDto> newSearch = search.stream().map(
                (post)-> modelMapper.map(post,PostDto.class)).
                collect(Collectors.toList());

        return newSearch;
    }
}
