package com.spr.service;

import java.util.List;
import java.util.Optional;

import com.spr.entity.User;

public interface UserService {
	User saveUser(User user);
	Optional<User> findUser(int id);
	
	List<User> getUsers();
	User getUserToUpdate(User existingUser, User userToUpdate);
}
