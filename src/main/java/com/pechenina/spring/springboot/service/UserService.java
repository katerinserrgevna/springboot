package com.pechenina.spring.springboot.service;

import com.pechenina.spring.springboot.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    User getUserByUserName(String userName);
    void saveUser(User user);
    User update(User user);
    void delete(int id);
}
