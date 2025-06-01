package com.newgen.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newgen.movie.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMovieId(Long movieId);
}