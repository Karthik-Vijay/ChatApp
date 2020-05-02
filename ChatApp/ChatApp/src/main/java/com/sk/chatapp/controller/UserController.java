package com.sk.chatapp.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.chatapp.model.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping("/username")
	public String getLoggedInUsername(Principal principal) {
		
		return principal.getName();
	}
	
	
	@PostMapping("/register")
	public String registerUser(User user) throws Exception {
		
		
		
		throw  new Exception();
	}
	
}
