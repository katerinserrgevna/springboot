package com.pechenina.spring.springboot.dao;

import com.pechenina.spring.springboot.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getUserById(int id);
    User getUserByUserName(String name);
    void saveUser(User user);
    User update(User user);
    void delete(int id);
}
