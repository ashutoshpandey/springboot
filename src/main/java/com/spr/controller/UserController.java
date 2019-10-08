package com.spr.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable(value="id") int id) throws EntityNotFoundException {
		Optional<User> user = userService.findUser(id);
		
		if(!user.isPresent())
			throw new EntityNotFoundException();
		
		return new ResponseEntity<User>(user.get(), HttpStatus.OK);
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.OK);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value="id") int id, @Valid @RequestBody User user) throws EntityNotFoundException {
		Optional<User> existingUser = userService.findUser(id);
		
		if(existingUser.isPresent()) {
			User userToUpdate = userService.getUserToUpdate(existingUser.get(), user);
			return new ResponseEntity<User>(userService.saveUser(userToUpdate), HttpStatus.OK);
		}else {
			throw new EntityNotFoundException();
		}
	}
}

/*
 ResponseEntity represents the whole HTTP response: status code, headers, and body. 
 Because of it, we can use it to fully configure the HTTP response.
*/
