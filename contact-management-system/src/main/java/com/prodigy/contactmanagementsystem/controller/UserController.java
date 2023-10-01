package com.prodigy.contactmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodigy.contactmanagementsystem.entity.User;
import com.prodigy.contactmanagementsystem.service.UserService;

@RestController
@RequestMapping("/cms")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok().body(userService.getAllUsers());
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable long id) {
		return ResponseEntity.ok().body(userService.getUserById(id));
	}

	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return ResponseEntity.ok().body(this.userService.createUser(user));
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {
		user.setUserId(id);
		return ResponseEntity.ok().body(this.userService.createUser(user));
	}

	@DeleteMapping("/user/{id}")
	public HttpStatus deleteUser(@PathVariable long id) {
		this.userService.deleteUser(id);
		return HttpStatus.OK;
	}
}
