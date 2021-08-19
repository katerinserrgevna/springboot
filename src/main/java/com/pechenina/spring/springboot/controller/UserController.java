package com.pechenina.spring.springboot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pechenina.spring.springboot.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String show(Model model, Authentication authentication) {
        model.addAttribute("user", userService.getUserByUserName(authentication.getName()));
        return "userShow";
    }
}
