package com.spring.security.springsecuritybasic.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private String name;
    private String userName;
    private String password;
    private String email;
}
