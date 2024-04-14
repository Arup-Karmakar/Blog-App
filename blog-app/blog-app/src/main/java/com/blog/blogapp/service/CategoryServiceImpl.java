package com.blog.blogapp.service;

import com.blog.blogapp.dao.Category;
import com.blog.blogapp.dto.CategoryDto;
import com.blog.blogapp.exception.ResourceNotFoundException;
import com.blog.blogapp.repo.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category create = this.modelMapper.map(categoryDto , Category.class);
        Category cat = this.categoryRepo.save(create);
        return  this.modelMapper.map(cat , CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(int categoryId) {
       Category get = this.categoryRepo.findById(categoryId).orElseThrow(
               () -> new ResourceNotFoundException("Category" , "Category Id" , categoryId));
       return this.modelMapper.map(get , CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
      List<Category> getAll =  this.categoryRepo.findAll();
      List<CategoryDto> category = getAll.stream().map((cat) ->this.modelMapper.map(cat,CategoryDto.class)).
              collect(Collectors.toList());
      return category;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
        Category get = this.categoryRepo.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category" , "Category Id" , categoryId));

        CategoryDto upadate = this.modelMapper.map(get , CategoryDto.class);
        upadate.setCategoryTitle(categoryDto.getCategoryTitle());
        upadate.setCategoryDescription(categoryDto.getCategoryDescription());

        return upadate;
    }

    @Override
    public void deleteCategory(int categoryId) {

        Category get = this.categoryRepo.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category" , "Category Id" , categoryId));
        this.categoryRepo.delete(get);
    }
}
