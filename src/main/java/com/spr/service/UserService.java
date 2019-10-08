package com.spr.service;

import java.util.List;

import com.spr.entity.User;

public interface UserService {
	User findUser(int id);
	User saveUser(User user);
	
	List<User> getUsers();
}
