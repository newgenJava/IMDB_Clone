package com.newgen.movie.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newgen.movie.model.Movie;
import com.newgen.movie.service.MovieService;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

	 private final MovieService movieService;

	    public MovieController(MovieService movieService) {
	        this.movieService = movieService;
	    }

	    @GetMapping
	    public List<Movie> getAllMovies() {
	        return movieService.getAllMovies();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
	        return movieService.getMovieById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    public Movie createMovie(@RequestBody Movie movie) {
	        return movieService.createMovie(movie);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
	        movieService.deleteMovie(id);
	        return ResponseEntity.noContent().build();
	    }
}
