package com.pechenina.spring.springboot.controller;

import com.pechenina.spring.springboot.model.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.pechenina.spring.springboot.model.User;
import com.pechenina.spring.springboot.service.RoleService;
import com.pechenina.spring.springboot.service.UserService;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        userService.delete(id);
    }

    @GetMapping("/admin/delete/{id}")
    public ResponseEntity<User> getUserInfo(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @PutMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "roles", required = false) Integer[] userRoles) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("USER"));
        if (userRoles != null) {
            Arrays.stream(userRoles).forEach(id -> roles.add(roleService.getRoleById(id)));
        }
        System.out.println(userRoles.length);
        user.setRoles(roles);
        userService.update(user);
        return "redirect:/admin";
    }

    @PostMapping()
    public ResponseEntity<User> create(@RequestBody @Valid User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/admin/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.getRoles(), HttpStatus.OK);
    }

    private int sortingRole (Role r1, Role r2) {
        return (int) (r1.getId() - r2.getId());
    }
}
