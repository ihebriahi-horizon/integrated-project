package com.example.back.controller;

import com.example.back.entity.User;
import com.example.back.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("{userId}")
	public User getUser(@PathVariable int userId) {
		return userService.getUser(userId);
	}

	@GetMapping("")
	public List<User> getUsers() {
		return userService.getUsers();
	}

	@PostMapping("")
	public void addUser(@RequestBody User user) {
		System.out.println(user);

		userService.addUser(user);
	}

}