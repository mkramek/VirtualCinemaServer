package com.cinema.virtualcinema.controller;

import com.cinema.virtualcinema.model.Room;
import com.cinema.virtualcinema.repository.RoomRepository;
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
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/room")
    public ResponseEntity<List<Room>> getRooms() {
        try {
            List<Room> rooms = new ArrayList<>();
            roomRepository.findAll().forEach(rooms::add);

            if (rooms.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(rooms, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/room/name/{name}")
    public ResponseEntity<Room> getRoomByName(@PathVariable("name") String name) {
        try {
            Optional<Room> roomOpt = roomRepository.findByRoomName(name);
            return roomOpt
                    .map(room -> new ResponseEntity<>(room, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("id") long id) {
        try {
            Optional<Room> roomOpt = roomRepository.findById(id);
            return roomOpt
                    .map(room -> new ResponseEntity<>(room, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
