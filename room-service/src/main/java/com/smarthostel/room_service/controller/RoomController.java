package com.smarthostel.room_service.controller;

import com.smarthostel.room_service.dto.RoomRequest;
import com.smarthostel.room_service.dto.RoomResponse;
import com.smarthostel.room_service.entity.Room;
import com.smarthostel.room_service.mapper.RoomMapper;
import com.smarthostel.room_service.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final RoomMapper roomMapper;

    @PostMapping
    public ResponseEntity<RoomResponse> createRoom(
            @Valid @RequestBody RoomRequest request) {

        Room room = roomMapper.toEntity(request);

        Room savedRoom =
                roomService.createRoom(room);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        roomMapper.toResponse(savedRoom)
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoomById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                roomMapper.toResponse(
                        roomService.getRoomById(id)
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>>
    getAllRooms() {

        return ResponseEntity.ok(
                roomService.getAllRooms()
                        .stream()
                        .map(roomMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/number/{roomNumber}")
    public ResponseEntity<RoomResponse>
    getRoomByNumber(
            @PathVariable String roomNumber) {

        return ResponseEntity.ok(
                roomMapper.toResponse(
                        roomService.getRoomByNumber(
                                roomNumber
                        )
                )
        );
    }

    @GetMapping("/floor/{floor}")
    public ResponseEntity<List<RoomResponse>>
    getRoomsByFloor(
            @PathVariable Integer floor) {

        return ResponseEntity.ok(
                roomService.getRoomsByFloor(floor)
                        .stream()
                        .map(roomMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/type/{roomType}")
    public ResponseEntity<List<RoomResponse>>
    getRoomsByType(
            @PathVariable String roomType) {

        return ResponseEntity.ok(
                roomService.getRoomsByType(roomType)
                        .stream()
                        .map(roomMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<RoomResponse>>
    getRoomsByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                roomService.getRoomsByStatus(status)
                        .stream()
                        .map(roomMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/capacity/{capacity}")
    public ResponseEntity<List<RoomResponse>>
    getRoomsByMinimumCapacity(
            @PathVariable Integer capacity) {

        return ResponseEntity.ok(
                roomService
                        .getRoomsByMinimumCapacity(capacity)
                        .stream()
                        .map(roomMapper::toResponse)
                        .toList()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> updateRoom(
            @PathVariable Long id,
            @Valid @RequestBody RoomRequest request) {

        Room room = roomMapper.toEntity(request);

        Room updatedRoom =
                roomService.updateRoom(id, room);

        return ResponseEntity.ok(
                roomMapper.toResponse(updatedRoom)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(
            @PathVariable Long id) {

        roomService.deleteRoom(id);

        return ResponseEntity.noContent().build();
    }
}