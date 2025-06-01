package com.newgen.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newgen.user.model.User;
import com.newgen.user.service.UserService;

@RestController
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/getUsers")
	public List<User> findAllUsers(){
		return userService.findAll();
	}
	

}
