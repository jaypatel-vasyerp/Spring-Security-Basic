package com.spring.security.springsecuritybasic.controller;

import com.spring.security.springsecuritybasic.constants.SecurityConstants;
import com.spring.security.springsecuritybasic.dto.AuthRequest;
import com.spring.security.springsecuritybasic.dto.UserDto;
import com.spring.security.springsecuritybasic.dto.UserResponseDto;
import com.spring.security.springsecuritybasic.service.JwtService;
import com.spring.security.springsecuritybasic.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
public class MainController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    public MainController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService, ModelMapper modelMapper) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/index")
    public ModelAndView greet() {
        return new ModelAndView("index");
    }

    @GetMapping("/userPage")
    public ModelAndView userPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("userPage");
        modelAndView.addObject("Token",request.getHeader(SecurityConstants.HEADER_NAME).replace(SecurityConstants.HEADER_PREFIX,""));
        return modelAndView;
    }

    @GetMapping("/userPage/info")
    public UserResponseDto getUserInfo(HttpServletRequest request){
        UserDetails user = userService.loadUserByUsername(jwtService.extractUsername(request.getHeader(SecurityConstants.HEADER_NAME).replace(SecurityConstants.HEADER_PREFIX,"")));
        return modelMapper.map(user,UserResponseDto.class);
    }

    @GetMapping("/adminPage")
    public ModelAndView adminPage(HttpServletRequest request,HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("adminPage");
        response.addHeader("Token",request.getHeader(SecurityConstants.HEADER_NAME).replace(SecurityConstants.HEADER_PREFIX,""));
        modelAndView.addObject("Token",request.getHeader(SecurityConstants.HEADER_NAME).replace(SecurityConstants.HEADER_PREFIX,""));
        return modelAndView;
    }

    @GetMapping("/adminPage/info")
    public List<UserResponseDto> getAllusersInfo(HttpServletRequest request){
        return userService.getAllUsers();
    }

    @GetMapping("/signup")
    public ModelAndView signUpPage() {
        return new ModelAndView("signUp");
    }

    @PostMapping("/signup")
    public ModelAndView createUser(UserDto userDto) {
        userService.addNewUser(userDto);
        return new ModelAndView("index");
    }
    @PostMapping("/dashboard")
    public ModelAndView authenticateAndGetToken(AuthRequest authRequest, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("userPage");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            var list = List.of(userService.loadUserByUsername(authRequest.getUsername()).getAuthorities());
            if (list.get(0).contains(new SimpleGrantedAuthority(SecurityConstants.ROLE_ADMIN))){
                modelAndView = new ModelAndView("adminPage");
            }
            modelAndView.addObject("Token", jwtService.generateToken(authRequest.getUsername()));
            modelAndView.addObject("Role", userService.loadUserByUsername(authRequest.getUsername()).getAuthorities());
        } else {

            throw new UsernameNotFoundException("invalid user request !");
        }
        return modelAndView;
    }


}
