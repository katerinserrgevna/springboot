package com.pechenina.spring.springboot.dao;

import com.pechenina.spring.springboot.model.Role;

import java.util.List;

public interface RoleDao {
    Role getRoleByName(String roleName);
    Role getRoleById(int id);
    void saveRole(Role role);
    List<Role> getRoles();
}
