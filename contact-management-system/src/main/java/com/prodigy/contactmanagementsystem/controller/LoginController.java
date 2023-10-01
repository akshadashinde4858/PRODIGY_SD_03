package com.prodigy.contactmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prodigy.contactmanagementsystem.entity.AuthRequest;
import com.prodigy.contactmanagementsystem.entity.User;
import com.prodigy.contactmanagementsystem.service.JwtService;
import com.prodigy.contactmanagementsystem.service.UserService;

@RestController
public class LoginController {
	

    @Autowired
    private JwtService jwtService;
    
	@Autowired
	private UserService userService;


	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		User user = userService.findByUsernameAndPassword(authRequest);
		
		if (user != null) {
			return jwtService.generateToken(user.getUsername());
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}

	}

}
