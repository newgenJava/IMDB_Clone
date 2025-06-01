package com.newgen.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.newgen.user.model.*;

@Component
public class UserService {
	
	private static List<User> userList = new ArrayList<>();
	static {
		userList.add(new User(100, "Bob"));
		userList.add(new User(101, "Alex"));
		userList.add(new User(102, "Sarah"));
	}
	
	
	public List<User> findAll(){
		return userList;
	}

}
