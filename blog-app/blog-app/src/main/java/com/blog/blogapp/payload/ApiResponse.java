package com.blog.blogapp.payload;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ApiResponse {
  private String message;
  private boolean success;
}
