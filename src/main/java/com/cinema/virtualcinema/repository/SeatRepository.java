package com.cinema.virtualcinema.repository;

import com.cinema.virtualcinema.model.Room;
import com.cinema.virtualcinema.model.Seat;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends PagingAndSortingRepository<Seat, Long> {
    List<Seat> findBySeatNumber(@Param("seat_number") int seatNumber);
    List<Seat> findBySeatRow(@Param("seat_row") char seatRow);
    List<Seat> findByRoom(@Param("room_id") Room room);
    List<Seat> findSeatBySeatRowAndSeatNumber(
            @Param("seat_row") char seatRow,
            @Param("seat_number") int seatNumber
    );
    Optional<Seat> findByUniqueId(@Param("uniqid") String uniqueID);
}
