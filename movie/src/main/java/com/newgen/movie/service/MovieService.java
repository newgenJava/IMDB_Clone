package com.newgen.movie.service;

import org.springframework.stereotype.Service;

import com.newgen.movie.model.Movie;
import com.newgen.movie.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

	
	private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
