package com.blog.blogapp.service;

import com.blog.blogapp.dao.User;
import com.blog.blogapp.dto.UserDto;
import com.blog.blogapp.exception.ResourceNotFoundException;
import com.blog.blogapp.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        User create =  this.userRepo.save(user);
        return this.userToDto(create);
    }
    @Override
    public UserDto getUser(int userId) {
        User user = this.userRepo.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        return this.userToDto(user);
    }
    @Override
    public List<UserDto> getAllUser() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> dto = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return dto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId) {
        User user = this.userRepo.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        user.setUserName(userDto.getUserName());
        user.setUserEmail(userDto.getUserEmail());
        user.setUserPassword(userDto.getUserPassword());
        user.setUserDescription(userDto.getUserDescription());

      User update = this.userRepo.save(user);
      UserDto dto = this.userToDto(update);
      return  dto;
    }

    @Override
    public void deleteUser(int userId) {
        User user = this.userRepo.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));

        this.userRepo.delete(user);

    }

    public UserDto userToDto(User user){
        UserDto dto = this.modelMapper.map(user , UserDto.class);
//        dto.setUserId(user.getUserId());
//        dto.setUserName(user.getUserName());
//        dto.setUserEmail(user.getUserEmail());
//        dto.setUserPassword(user.getUserPassword());
//        dto.setUserDescription(user.getUserDescription());
        return dto;

    }

    public User dtoToUser(UserDto dto){
        User user = this.modelMapper.map(dto,User.class);
//        user.setUserId(dto.getUserId());
//        user.setUserName(dto.getUserName());
//        user.setUserEmail(dto.getUserEmail());
//        user.setUserPassword(dto.getUserPassword());
//        user.setUserDescription(dto.getUserDescription());

        return user;
    }

}
