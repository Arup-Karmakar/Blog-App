package com.blog.blogapp.service;

import com.blog.blogapp.dto.CategoryDto;
import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto getCategory(int categoryId);

    List<CategoryDto> getAllCategory();

    CategoryDto updateCategory(CategoryDto categoryDto , int categoryId);

    void deleteCategory(int categoryId);
    
}
