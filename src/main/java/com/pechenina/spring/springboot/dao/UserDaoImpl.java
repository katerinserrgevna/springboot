package com.pechenina.spring.springboot.dao;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import com.pechenina.spring.springboot.model.User;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext()
    private EntityManager entityManager;

    @Override
    public User getUserById(int id) {
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getResultList().stream().findAny().orElse(null);
    }

    @Override
    public User getUserByUserName(String userName) {
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.userName = :userName", User.class)
                .setParameter("userName", userName)
                .getResultList().stream().findAny().orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Override
    public void delete(int id) {
        if(getUserById(id) != null) {
            entityManager.remove(getUserById(id));
        }
    }
}
