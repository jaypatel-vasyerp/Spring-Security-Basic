package com.spring.security.springsecuritybasic.service;

import com.spring.security.springsecuritybasic.dto.UserDto;
import com.spring.security.springsecuritybasic.dto.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void addNewUser(UserDto userDto);

    List<UserResponseDto> getAllUsers();

}
