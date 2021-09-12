package com.cinema.virtualcinema.controller;

import com.cinema.virtualcinema.model.Reservation;
import com.cinema.virtualcinema.model.Room;
import com.cinema.virtualcinema.model.Seat;
import com.cinema.virtualcinema.model.SeatStatus;
import com.cinema.virtualcinema.repository.ReservationRepository;
import com.cinema.virtualcinema.repository.RoomRepository;
import com.cinema.virtualcinema.repository.SeatRepository;
import com.cinema.virtualcinema.repository.SeatStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ReservationsController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationsController.class);

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    SeatStatusRepository statusRepository;

    @GetMapping("/reservation")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        try {
            List<Reservation> reservations = new ArrayList<>();
            reservationRepository.findAll().forEach(reservations::add);
            if (reservations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(reservations, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reservation/seat/{id}")
    public ResponseEntity<Reservation> getReservationBySeat(@PathVariable("id") Long seatId) {
        try {
            Optional<Reservation> reservation = Optional.empty();
            Optional<Seat> seat = seatRepository.findById(seatId);
            if (seat.isPresent()) {
                reservation = reservationRepository.findBySeat(seat.get());
            }
            if (reservation.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(reservation.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reservation/room/{id}")
    public ResponseEntity<List<Reservation>> getReservationsByRoomId(@PathVariable("id") Long roomId) {
        try {
            List<Reservation> reservations = new ArrayList<>();
            Optional<Room> room = roomRepository.findById(roomId);
            room.ifPresent(existingRoom -> reservations.addAll(reservationRepository.findBySeatRoom(existingRoom)));
            if (reservations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(reservations, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reservation/user/{user}")
    public ResponseEntity<List<Reservation>> getReservationsByUser(@PathVariable("user") String user) {
        try {
            List<Reservation> reservations = new ArrayList<>(reservationRepository.findByUser(user));
            if (reservations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(reservations, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/reservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        try {
            Optional<Reservation> existingReservation = reservationRepository.findBySeat(reservation.getSeat());
            if (existingReservation.isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                final Reservation newReservation = reservationRepository.save(reservation);
                return new ResponseEntity<>(newReservation, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
