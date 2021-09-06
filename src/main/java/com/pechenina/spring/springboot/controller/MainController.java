package com.pechenina.spring.springboot.controller;

import com.pechenina.spring.springboot.model.Role;
import com.pechenina.spring.springboot.model.User;
import com.pechenina.spring.springboot.service.RoleService;
import com.pechenina.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/")
public class MainController {
    private final UserService userService;
    private final RoleService roleService;

    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("user")
    public String show(Model model, Authentication authentication) {
        model.addAttribute("user", userService.getUserByUserName(authentication.getName()));
        return "userShow";
    }

    @GetMapping("admin")
    public String getAllUsers(Model model) {
        return "admin";
    }

    @GetMapping("admin/new")
    public String newUser(Model model) {
        model.addAttribute(new User());
        Map<Role, Boolean> roles = new TreeMap((Comparator<Role>) this::sortingRole);
        roleService.getRoles().forEach(r -> roles.put(r, false));
        model.addAttribute("roles", roles);
        return "newUser";
    }

    private int sortingRole (Role r1, Role r2) {
        return (int) (r1.getId() - r2.getId());
    }
}
