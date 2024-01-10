package com.qualitybazar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qualitybazar.model.User;

public interface UserRepository extends JpaRepository<User,Long>  {
	
	public User findByEmail(String email);

}
