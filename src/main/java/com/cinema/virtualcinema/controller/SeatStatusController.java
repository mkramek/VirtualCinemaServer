package com.cinema.virtualcinema.controller;

import com.cinema.virtualcinema.model.Seat;
import com.cinema.virtualcinema.model.SeatStatus;
import com.cinema.virtualcinema.repository.SeatStatusRepository;
import com.corundumstudio.socketio.BroadcastAckCallback;
import com.corundumstudio.socketio.SocketIOServer;
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
public class SeatStatusController {

    private static final Logger log = LoggerFactory.getLogger(SeatStatusController.class);

    @Autowired
    SeatStatusRepository seatStatusRepository;

    @GetMapping("/status")
    public ResponseEntity<List<SeatStatus>> getStatuses() {
        try {
            List<SeatStatus> seatStatuses = new ArrayList<>();
            seatStatusRepository.findAll().forEach(seatStatuses::add);

            if (seatStatuses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(seatStatuses, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status/{seat}")
    public ResponseEntity<SeatStatus> getStatusBySeat(@PathVariable("seat") Seat seat) {
        try {
            Optional<SeatStatus> optionalSeat = seatStatusRepository.findBySeatId(seat.getId());
            return optionalSeat
                    .map(status -> new ResponseEntity<>(status, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/status/update")
    public ResponseEntity<SeatStatus> updateOrCreateStatusBySeat(@RequestBody SeatStatus targetStatus) {
        Optional<SeatStatus> status = seatStatusRepository.findBySeat(targetStatus.getSeat());
        if (status.isPresent()) {
            status.get().setStatus(targetStatus.getStatus());
            status.get().setSender(targetStatus.getSender());
            final SeatStatus updatedStatus = seatStatusRepository.save(status.get());
            return new ResponseEntity<>(updatedStatus, HttpStatus.OK);
        } else {
            SeatStatus newStatus = new SeatStatus();
            newStatus.setSeat(targetStatus.getSeat());
            newStatus.setStatus(targetStatus.getStatus());
            newStatus.setSender(targetStatus.getSender());
            final SeatStatus savedStatus = seatStatusRepository.save(newStatus);
            return new ResponseEntity<>(savedStatus, HttpStatus.OK);
        }
    }
}
