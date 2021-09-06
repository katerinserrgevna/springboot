package com.pechenina.spring.springboot.service;

import com.pechenina.spring.springboot.dao.RoleDao;
import com.pechenina.spring.springboot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    final
    RoleDao roledao;

    public RoleServiceImpl(RoleDao roledao) {
        this.roledao = roledao;
    }

    @Override
    @Transactional
    public Role getRoleByName(String userName) {
        return roledao.getRoleByName(userName);
    }

    @Override
    public Role getRoleById(int id) {
        return roledao.getById(id);
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roledao.save(role);
    }

    @Override
    @Transactional
    public List<Role> getRoles() {
        return roledao.findAll();
    }
}
