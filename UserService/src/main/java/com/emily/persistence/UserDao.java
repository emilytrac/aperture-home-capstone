package com.emily.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emily.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	public User findByUserEmailAndUserPassword(String userEmail, String userPassword);
	
}
