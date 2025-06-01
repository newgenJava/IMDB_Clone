package com.newgen.movie.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newgen.movie.model.Review;
import com.newgen.movie.repository.MovieRepository;
import com.newgen.movie.repository.ReviewRepository;

@RestController
@RequestMapping("/api/movies/{movieId}/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public ReviewController(ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@PathVariable Long movieId, @RequestBody Review review) {
        return movieRepository.findById(movieId).map(movie -> {
            review.setMovie(movie);
            return ResponseEntity.ok(reviewRepository.save(review));
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviewRepository.findByMovieId(movieId));
    }
    
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Object> deleteReview(@PathVariable Long movieId, @PathVariable Long reviewId) {
        return reviewRepository.findById(reviewId).map(review -> {
            if (review.getMovie().getId().equals(movieId)) {
                reviewRepository.delete(review);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Review doesn't belong to movie
            }
        }).orElse(ResponseEntity.notFound().build());
    }
}