package com.spring.security.springsecuritybasic.controller;

import com.spring.security.springsecuritybasic.dto.UserDto;
import com.spring.security.springsecuritybasic.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {

    private final UserServiceImpl userServiceImpl;

    public MainController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/index")
    public ModelAndView greet() {
        return new ModelAndView("index");
    }

    @GetMapping("/user-page")
    public ModelAndView userPage() {
        return new ModelAndView("userPage");
    }

    @GetMapping("/admin-page")
    public ModelAndView adminPage() {
        return new ModelAndView("adminPage");
    }

    @GetMapping("/sign-up")
    public ModelAndView signUpPage() {
        return new ModelAndView("signUp");
    }

    @PostMapping("/sign-up")
    public ModelAndView createUser(UserDto userDto){
        userServiceImpl.addNewUser(userDto);
        return new ModelAndView("index");
    }


}
