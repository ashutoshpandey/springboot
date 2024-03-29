package com.spr.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spr.entity.Authority;
import com.spr.entity.User;
import com.spr.repository.AuthorityRepository;
import com.spr.repository.UserRepository;
import com.spr.search.SearchCriteria;
import com.spr.search.UserSpecification;
import com.spr.service.UserService;
import com.spr.util.Constants;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserServiceImpl() {
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
	}
	
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
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		User createdUser = userRepository.save(user);
		
		Authority authority = new Authority();
		authority.setUsername(createdUser.getUsername());
		authority.setAuthority(Constants.ROLE_USER);
		authorityRepository.save(authority);
		
		return createdUser;
	}

	@Override
	public User getUserToUpdate(User existingUser, User userToUpdate) {
		if (userToUpdate.getName() != null)
			existingUser.setName(userToUpdate.getName());

		if (userToUpdate.getEmail() != null)
			existingUser.setEmail(userToUpdate.getEmail());

		return existingUser;
	}
/*
	@Override
	public List<User> getUsersByName(User user) {
		UserSpecification spec = new UserSpecification(new SearchCriteria("name", ":", user.getName()));

		List<User> users = userRepository.findAll(spec);
		return users;
	}
*/
	
	@Override
	public List<User> getUsersByName(User user) {
		List<User> users = userRepository.findByName(user.getName());
		return users;
	}

	@Override
	public List<User> getUsersByNameGender(User user) {
		UserSpecification specName = new UserSpecification(new SearchCriteria("name", ":", user.getName()));
		UserSpecification specGender = new UserSpecification(new SearchCriteria("gender", "=", user.getGender()));

		List<User> users = userRepository.findAll(Specification.where(specName).and(specGender));
		return users;
	}
}
