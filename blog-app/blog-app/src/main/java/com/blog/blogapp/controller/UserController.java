package com.blog.blogapp.controller;

import com.blog.blogapp.dto.UserDto;
import com.blog.blogapp.payload.ApiResponse;
import com.blog.blogapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }
    @GetMapping("/get-user/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable int userId){
        UserDto getUser = this.userService.getUser(userId);
        return new ResponseEntity<>(getUser,HttpStatus.OK);
    }
    @GetMapping("/get-allusers")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> getAllUsers = this.userService.getAllUser();
        return new ResponseEntity<>(getAllUsers,HttpStatus.OK);
    }

    @PutMapping("/update-user/{userid}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto , @PathVariable int userId){
        UserDto updateUser = this.userService.updateUser(userDto,userId);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);

    }

    public ResponseEntity<?> deleteUser(@PathVariable int userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully" , true),
                HttpStatus.OK);
    }


}
