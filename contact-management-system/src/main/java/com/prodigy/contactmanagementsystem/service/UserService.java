package com.prodigy.contactmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prodigy.contactmanagementsystem.entity.AuthRequest;
import com.prodigy.contactmanagementsystem.entity.User;
import com.prodigy.contactmanagementsystem.exception.UserNotFoundException;
import com.prodigy.contactmanagementsystem.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User findByUsernameAndPassword(AuthRequest authRequest) {
		return userRepository.findByUsernameAndPassword(authRequest.getUsername(), authRequest.getPassword());
	}
	
	
	

	public User getUserById(long userId) {
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			return user.get();
		} else {
			throw new UserNotFoundException("User not found with id : " + userId);
		}
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User updateUser(User user) {

		Optional<User> savedUser = this.userRepository.findById(user.getUserId());

		if (savedUser.isPresent()) {
			User userToUpdate = savedUser.get();

			userToUpdate.setUserId(user.getUserId());
			userToUpdate.setFirstName(user.getFirstName());
			userToUpdate.setLastName(user.getLastName());
			userToUpdate.setUsername(user.getUsername());
			userToUpdate.setPassword(user.getPassword());
			
			userRepository.save(userToUpdate);

			return userToUpdate;
		} else {
			throw new UserNotFoundException("User not found with id : " + user.getUserId());
		}
	}

	public void deleteUser(long id) {
		Optional<User> savedUser = userRepository.findById(id);

		if (savedUser.isPresent()) {
			userRepository.delete(savedUser.get());
		} else {
			throw new UserNotFoundException("User not found with id : " + id);
		}
	}

}
