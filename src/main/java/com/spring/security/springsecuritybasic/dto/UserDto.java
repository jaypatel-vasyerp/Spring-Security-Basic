package com.spring.security.springsecuritybasic.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private String name;
    private String username;
    private String password;
    private String email;
}
