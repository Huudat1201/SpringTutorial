package com.springboot.Services;

import com.springboot.Entity.User;

public interface UserService {
	User findByUserName(String userName);
}