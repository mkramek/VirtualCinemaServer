package com.cinema.virtualcinema.controller;

import com.cinema.virtualcinema.model.Movie;
import com.cinema.virtualcinema.model.Room;
import com.cinema.virtualcinema.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/movie")
    public ResponseEntity<List<Movie>> getMovies() {
        try {
            List<Movie> movies = new ArrayList<>();
            movieRepository.findAll().forEach(movies::add);

            if (movies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movie/title/{title}")
    public ResponseEntity<Movie> getMovieByTitle(@PathVariable("title") String title) {
        try {
            Optional<Movie> movieOpt = movieRepository.findByMovieTitle(title);
            return movieOpt
                    .map(movie -> new ResponseEntity<>(movie, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") long id) {
        try {
            Optional<Movie> movieOpt = movieRepository.findById(id);
            return movieOpt
                    .map(movie -> new ResponseEntity<>(movie, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
