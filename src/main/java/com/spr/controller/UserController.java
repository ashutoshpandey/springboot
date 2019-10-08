package com.spr.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spr.entity.User;
import com.spr.exception.EntityNotFoundException;
import com.spr.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/user")
	public List<User> getUsers(){
		return userService.getUsers();
	}
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable(value="id") int id) throws EntityNotFoundException {
		Optional<User> user = userService.findUser(id);
		
		if(!user.isPresent())
			throw new EntityNotFoundException();
		
		return user.get();
	}
	
	@PostMapping("/user")
	public User createUser(@Valid @RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@PutMapping("/user/{id}")
	public User updateUser(@PathVariable(value="id") int id, @Valid @RequestBody User user) throws EntityNotFoundException {
		Optional<User> existingUser = userService.findUser(id);
		
		if(existingUser.isPresent()) {
			User userToUpdate = userService.getUserToUpdate(existingUser.get(), user);
			return userService.saveUser(userToUpdate);
		}else {
			throw new EntityNotFoundException();
		}
	}
}
