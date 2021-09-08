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
        roleService.saveRole(new Role("ROLE_USER"));
        roleService.saveRole(new Role("ROLE_ADMIN"));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("ROLE_USER"));
        userService.saveUser(new User("user", "1", roles));
        roles.add(roleService.getRoleByName("ROLE_ADMIN"));
        userService.saveUser(new User("1", "1", roles));
    }
}
