package com.newgen.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import com.newgen.user.model.User;
import com.newgen.user.model.UserRepository;
import com.newgen.user.service.JwtService;
import com.newgen.user.service.UserJPAService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserJPAController {

	@Autowired
	private UserJPAService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	@GetMapping("/me")
	public ResponseEntity<User> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		String token = authHeader.substring(7);
		String email = jwtService.extractUsername(token); // Extract email from JWT

		Optional<User> user = userRepository.findByEmail(email);
		if (user == null || user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.ok(user.get());
	}

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id) {
		Optional<User> user = userService.getUserById(id);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
		Optional<User> user = userService.getUserByEmail(email);

		if (!user.isPresent()) {
			throw new RuntimeException("User not found");
		}

		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@PostMapping(params = "version=2")
	public ResponseEntity<User> createUserV2(@RequestBody User user) {

		User addedUser = userRepository.save(user);
		UriComponents uriLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(addedUser.getId());

		return ResponseEntity.created(uriLocation.toUri()).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User userDetails) {
		try {
			User updatedUser = userService.updateUser(id, userDetails);
			return ResponseEntity.ok(updatedUser);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
