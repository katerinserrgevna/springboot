package com.pechenina.spring.springboot.service;

import com.pechenina.spring.springboot.dao.RoleDao;
import com.pechenina.spring.springboot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleDao roledao;

    @Override
    @Transactional
    public Role getRoleByName(String userName) {
        return roledao.getRoleByName(userName);
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roledao.saveRole(role);
    }

    @Override
    @Transactional
    public List<Role> getRoles() {
        return roledao.getRoles();
    }
}
