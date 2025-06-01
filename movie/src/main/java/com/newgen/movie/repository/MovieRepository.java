package com.newgen.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newgen.movie.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
