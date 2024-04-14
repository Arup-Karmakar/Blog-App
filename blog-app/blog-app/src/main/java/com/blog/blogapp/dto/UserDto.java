package com.blog.blogapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    private int userId;
    @NotEmpty
    @Size(min = 4 , message = "Username must be minimum 4 character")
    private String userName;
    @NotEmpty(message = "Not a Valid Email")
    private String userEmail;
    @NotEmpty
    @Size(min=3 , max = 10 , message = "Give password as per policy")
    private String userPassword;
    @NotEmpty
    private String userDescription;
}
