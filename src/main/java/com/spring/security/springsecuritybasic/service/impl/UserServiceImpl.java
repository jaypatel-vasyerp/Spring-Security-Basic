package com.spring.security.springsecuritybasic.service.impl;

import com.spring.security.springsecuritybasic.dto.UserDto;
import com.spring.security.springsecuritybasic.dto.UserResponseDto;
import com.spring.security.springsecuritybasic.exception.EntityNotFoundException;
import com.spring.security.springsecuritybasic.model.Role;
import com.spring.security.springsecuritybasic.model.User;
import com.spring.security.springsecuritybasic.repository.UserRepository;
import com.spring.security.springsecuritybasic.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User with Username " + username + " Does Not exists."));
        return user;
    }

    @Override
    public void addNewUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(2, "ROLE_USER"));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user ->modelMapper.map(user,UserResponseDto.class)).toList();
    }
}
