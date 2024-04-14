package com.blog.blogapp.payload;

import com.blog.blogapp.dto.PostDto;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private List<PostDto> content;
    private int pageNumber;
    private int pageSize;
    private int totalPage;
    private long totalElement;
    private boolean lastPage;
}
