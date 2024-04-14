package com.blog.blogapp.service;

import com.blog.blogapp.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUser(int userId);
    List<UserDto> getAllUser();
    UserDto updateUser(UserDto userDto , int userId);
    void deleteUser(int userId);
}
