package com.pechenina.spring.springboot.controller;

import com.pechenina.spring.springboot.model.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.pechenina.spring.springboot.model.User;
import com.pechenina.spring.springboot.service.RoleService;
import com.pechenina.spring.springboot.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String adminPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @PutMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id,
                             @RequestParam(required = false) String userRoles) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("USER"));
        if (userRoles != null && userRoles.equals(
                roleService.getRoleByName("ADMIN").getRoleName())) {
            roles.add(roleService.getRoleByName("ADMIN"));
        }
        user.setRoles(roles);
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute(new User());
        return "newUser";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(required = false) String userRoles) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("USER"));
        if (userRoles != null && userRoles.equals(
                roleService.getRoleByName("ADMIN").getRoleName())) {
            roles.add(roleService.getRoleByName("ADMIN"));
        }
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }
}
