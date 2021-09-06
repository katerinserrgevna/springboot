package com.pechenina.spring.springboot.dao;

import com.pechenina.spring.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    @Query(" SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles ")
    List<User> findAll();

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = (:id)")
    User getUserById(@Param("id") Integer id);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.userName = (:userName)")
    User getUserByUserName(@Param("userName") String userName);
}
