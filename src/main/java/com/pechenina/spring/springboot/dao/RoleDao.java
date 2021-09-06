package com.pechenina.spring.springboot.dao;

import com.pechenina.spring.springboot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {
    @Query("SELECT u FROM Role u WHERE u.roleName = (:roleName)")
    Role getRoleByName(String roleName);
}
