package com.pechenina.spring.springboot.dao;

import com.pechenina.spring.springboot.model.Role;

import java.util.List;

public interface RoleDao {
    Role getRoleByName(String roleName);
    void saveRole(Role role);
    List<Role> getRoles();
}
