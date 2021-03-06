package com.pechenina.spring.springboot.service;

import com.pechenina.spring.springboot.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleService {
    Role getRoleByName(String userName);
    void saveRole(Role role);
    List<Role> getRoles();
}
