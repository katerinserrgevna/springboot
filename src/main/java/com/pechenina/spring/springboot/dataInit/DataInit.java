package com.pechenina.spring.springboot.dataInit;

import com.pechenina.spring.springboot.model.Role;
import com.pechenina.spring.springboot.model.User;
import com.pechenina.spring.springboot.service.RoleService;
import com.pechenina.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInit {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DataInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        roleService.saveRole(new Role("USER"));
        roleService.saveRole(new Role("ADMIN"));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("USER"));
        userService.saveUser(new User("user", "1", roles));
        roles.add(roleService.getRoleByName("ADMIN"));
        userService.saveUser(new User("admin", "2", roles));
    }
}
