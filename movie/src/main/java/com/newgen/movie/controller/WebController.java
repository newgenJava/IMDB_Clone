package com.newgen.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
public class WebController {
	
	@GetMapping
	public String homePage()
	{
		return "Movies";
	}

}
