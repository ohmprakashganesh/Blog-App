package com.codewithdurgesh.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdurgesh.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	//this is for security 
	Optional<User> findByEmail(String email);

}
