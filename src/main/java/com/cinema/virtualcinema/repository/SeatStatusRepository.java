package com.cinema.virtualcinema.repository;

import com.cinema.virtualcinema.model.Seat;
import com.cinema.virtualcinema.model.SeatStatus;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SeatStatusRepository extends PagingAndSortingRepository<SeatStatus, Long> {
    Optional<SeatStatus> findBySeatId(@Param("seatId") Long seatId);
    Optional<SeatStatus> findBySeat(@Param("seat") Seat seat);
}
