package com.emily.userservice.repository;

import com.emily.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    // custom JPA method to return user from email and password
    User findByUserEmailAndUserPassword(String userEmail, String userPassword);
}
