package com.cinema.virtualcinema.repository;

import com.cinema.virtualcinema.model.Room;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {
    Optional<Room> findByRoomName(@Param("room_name") String roomName);
}
