package com.cinema.virtualcinema.repository;

import com.cinema.virtualcinema.model.Movie;
import com.cinema.virtualcinema.model.Room;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
    Optional<Movie> findByRoom(@Param("room_id") Room room);
    Optional<Movie> findByMovieTitle(@Param("title") String title);
}
