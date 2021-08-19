package com.pechenina.spring.springboot.dao;


import com.pechenina.spring.springboot.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Role getRoleByName(String roleName) {
        return entityManager.createQuery(
                "SELECT u FROM Role u WHERE u.roleName = :roleName", Role.class)
                .setParameter("roleName", roleName)
                .getSingleResult();
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> getRoles() {
        return entityManager.createQuery("FROM Role", Role.class)
                .getResultList();
    }
}
