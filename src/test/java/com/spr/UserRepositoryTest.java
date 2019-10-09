package com.spr;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.spr.entity.User;
import com.spr.repository.UserRepository;
import com.spr.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	public void getAllUsersTest() {
		List<User> users = new ArrayList<User>();
		
		users.add(new User("User1", "user1@email.com", "male"));
		users.add(new User("User2", "user2@email.com", "female"));
		
		when(userRepository.findAll()).thenReturn(users);
		
		List<User> userList = userService.getUsers();
		
		assertEquals(2, userList.size());
		verify(userRepository, times(1)).findAll();
	}
	
	@Test
	public void getUsersByName() {
		List<User> users = new ArrayList<User>();
		
		users.add(new User("Shiva", "user2@email.com", "male"));
		users.add(new User("Shiva", "user2@email.com", "male"));
		
		when(userRepository.findByName("Shiva")).thenReturn(users);
		
		List<User> userList = userService.getUsersByName(new User("Shiva", null, null));
		
		assertEquals(2, userList.size());
		verify(userRepository, times(1)).findByName("Shiva");
	}
}
