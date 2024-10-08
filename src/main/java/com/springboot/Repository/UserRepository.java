package com.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.Entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUserName(String userName);
}
