package com.cinema.virtualcinema.repository;

import com.cinema.virtualcinema.model.Reservation;
import com.cinema.virtualcinema.model.Room;
import com.cinema.virtualcinema.model.Seat;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {
    Optional<Reservation> findBySeat(@Param("seat") Seat seat);
    List<Reservation> findBySeatRoom(@Param("room") Room room);
    List<Reservation> findByUser(@Param("user") String user);
}
