package com.newgen.user.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.newgen.user.model.User;
import com.newgen.user.model.UserRepository;
import com.newgen.user.service.JwtService;

@Controller
public class UserWebResource {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private JwtService jwtService;
	
	@GetMapping(path = "/login")
	public String login() {
		return "login";
	}
	
	@GetMapping(path = "/")
	public String homePage() {
		return "login";
	}
	
	@GetMapping(path = "/signup")
	public String signupPage() {
		return "signup";
	}
	
	
	@GetMapping(path = "/dashboard")
	public String dashboardPage() {
        return "dashboard";
    }
	
	@GetMapping(path = "/admin")
	public String adminPage() {
		return "admin";
	}
	
	@GetMapping(path = "/userInfo")
	public String userInfoPage() {
		return "userInfo";
	}


}