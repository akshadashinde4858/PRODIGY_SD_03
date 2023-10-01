package com.prodigy.contactmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodigy.contactmanagementsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	User findByUsernameAndPassword(String username, String password );

}
