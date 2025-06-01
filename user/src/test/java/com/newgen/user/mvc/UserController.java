package com.newgen.user.mvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.newgen.user.controller.UserJPAController;
import com.newgen.user.model.User;
import com.newgen.user.service.UserJPAService;

@WebMvcTest(UserJPAController.class)
public class UserController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserJPAService userService;

	@Test
	public void testGetUser() throws Exception {
//		User user = new User("John", "john@example.com");
//		Mockito.when(userService.getUserByEmail("john@example.com")).thenReturn(Optional.of(user));
//
//		mockMvc.perform(get("/users/email/john@example.com")).andExpect(status().isOk())
//				.andExpect(jsonPath("$.name").value("John")).andExpect(jsonPath("$.email").value("john@example.com"));
	}
}
