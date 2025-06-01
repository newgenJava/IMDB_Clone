package com.newgen.user.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newgen.user.model.User;
import com.newgen.user.model.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserJPAService {

	@Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setFullName(userDetails.getFullName());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

	public boolean existsById(Integer id) {
		return userRepository.existsById(id);
	}
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}	

}
