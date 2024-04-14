package com.blog.blogapp.controller;

import com.blog.blogapp.dto.CategoryDto;
import com.blog.blogapp.payload.ApiResponse;
import com.blog.blogapp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create-category")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto dto){
        CategoryDto create = this.categoryService.createCategory(dto);
        return new ResponseEntity<>(create , HttpStatus.CREATED);
    }

    @GetMapping("/get-category/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable int categoryId){
        CategoryDto get = this.categoryService.getCategory(categoryId);
        return new ResponseEntity<>(get,HttpStatus.OK);
    }

    @GetMapping("/all-categories")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> getAll =  this.categoryService.getAllCategory();
        return new ResponseEntity<>(getAll,HttpStatus.OK);
    }

    @PutMapping("/update-category/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto dto , @PathVariable int categoryId){
         CategoryDto update = this.categoryService.updateCategory(dto,categoryId);
         return new ResponseEntity<>(update,HttpStatus.OK);
    }

    @DeleteMapping("/delete-category/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable int categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(
                new ApiResponse("Category Deleted Successfully" , true),HttpStatus.OK);
    }
}
