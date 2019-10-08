package com.spr.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.spr.entity.User;
import com.spr.service.UserService;
import com.spr.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Optional<User> findUser(int id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserToUpdate(User existingUser, User userToUpdate) {
		if(userToUpdate.getName() != null)
			existingUser.setName(userToUpdate.getName());
			
		if(userToUpdate.getEmail() != null)
			existingUser.setEmail(userToUpdate.getEmail());

		return existingUser;
	}
}
