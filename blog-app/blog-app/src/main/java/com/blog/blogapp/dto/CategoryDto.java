package com.blog.blogapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {
    private int categoryId;
    @NotBlank
    @Size(min = 3 , max = 10)
    private String categoryTitle;
    @Size(max = 20)
    private  String categoryDescription;
}
