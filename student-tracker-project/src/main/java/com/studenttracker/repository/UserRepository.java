package com.studenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.studenttracker.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.username = ?1")
	public com.studenttracker.model.User getUserByUsername(String username);
}