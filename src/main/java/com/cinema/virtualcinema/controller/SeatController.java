package com.cinema.virtualcinema.controller;

import com.cinema.virtualcinema.model.Room;
import com.cinema.virtualcinema.model.Seat;
import com.cinema.virtualcinema.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class SeatController {

    @Autowired
    SeatRepository seatRepository;

    @GetMapping("/seat")
    public ResponseEntity<List<Seat>> getSeats() {
        try {
            List<Seat> seats = new ArrayList<>();
            seatRepository.findAll().forEach(seats::add);

            if (seats.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(seats, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/seat/{uid}")
    public ResponseEntity<Seat> getSeatByUID(@PathVariable("uid") String uniqueID) {
        try {
            Optional<Seat> optionalSeat = seatRepository.findByUniqueId(uniqueID);
            return optionalSeat
                    .map(seat -> new ResponseEntity<>(seat, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/seat/{uid}")
    public ResponseEntity<Seat> updateSeatByUniqueID(@PathVariable("uid") String uniqueID, @RequestBody Seat newSeat) {
        Optional<Seat> seat = seatRepository.findByUniqueId(uniqueID);
        if (seat.isPresent()) {
            final Seat updatedSeat = seatRepository.save(seat.get());
            return new ResponseEntity<>(updatedSeat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/seat/room/{room}")
    public ResponseEntity<List<Seat>> getSeatsByRoom(@PathVariable("room") Room room) {
        try {
            List<Seat> seats = new ArrayList<>(seatRepository.findByRoom(room));
            if (seats.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(seats, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/seat/row/{row}")
    public ResponseEntity<List<Seat>> getSeatsByRow(@PathVariable("row") char row) {
        try {
            List<Seat> seats = new ArrayList<>(seatRepository.findBySeatRow(row));
            if (seats.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(seats, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/seat/row/{row}/{num}")
    public ResponseEntity<List<Seat>> getSeatByRowAndNumber(@PathVariable("row") char row, @PathVariable("num") int number) {
        try {
            List<Seat> seats = new ArrayList<>(seatRepository.findSeatBySeatRowAndSeatNumber(row, number));
            if (seats.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(seats, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
