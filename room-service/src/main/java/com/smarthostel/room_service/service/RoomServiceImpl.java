package com.smarthostel.room_service.service;

import com.smarthostel.room_service.entity.Room;
import com.smarthostel.room_service.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public Room createRoom(Room room) {

        if (roomRepository.existsByRoomNumber(
                room.getRoomNumber())) {

            throw new RuntimeException(
                    "Room already exists with number: "
                            + room.getRoomNumber()
            );
        }

        validateRoom(room);

        if (room.getOccupiedBeds() == null) {
            room.setOccupiedBeds(0);
        }

        if (room.getStatus() == null ||
                room.getStatus().isBlank()) {

            room.setStatus("AVAILABLE");
        }

        return roomRepository.save(room);
    }

    @Override
    public Room getRoomById(Long id) {

        return roomRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Room not found with id: " + id
                        ));
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomByNumber(String roomNumber) {

        return roomRepository.findByRoomNumber(roomNumber)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Room not found with number: "
                                        + roomNumber
                        ));
    }

    @Override
    public List<Room> getRoomsByFloor(
            Integer floor) {

        return roomRepository.findByFloor(floor);
    }

    @Override
    public List<Room> getRoomsByType(
            String roomType) {

        return roomRepository.findByRoomType(roomType);
    }

    @Override
    public List<Room> getRoomsByStatus(
            String status) {

        return roomRepository.findByStatus(status);
    }

    @Override
    public List<Room> getRoomsByMinimumCapacity(
            Integer capacity) {

        return roomRepository
                .findByCapacityGreaterThanEqual(capacity);
    }

    @Override
    public Room updateRoom(
            Long id,
            Room room) {

        Room existingRoom = getRoomById(id);

        if (!existingRoom.getRoomNumber()
                .equals(room.getRoomNumber())
                && roomRepository.existsByRoomNumber(
                        room.getRoomNumber())) {

            throw new RuntimeException(
                    "Room already exists with number: "
                            + room.getRoomNumber()
            );
        }

        validateRoom(room);

        existingRoom.setRoomNumber(
                room.getRoomNumber()
        );

        existingRoom.setFloor(
                room.getFloor()
        );

        existingRoom.setCapacity(
                room.getCapacity()
        );

        existingRoom.setOccupiedBeds(
                room.getOccupiedBeds()
        );

        existingRoom.setRoomType(
                room.getRoomType()
        );

        existingRoom.setStatus(
                room.getStatus()
        );

        existingRoom.setDescription(
                room.getDescription()
        );

        return roomRepository.save(existingRoom);
    }

    @Override
    public void deleteRoom(Long id) {

        Room room = getRoomById(id);

        roomRepository.delete(room);
    }

    private void validateRoom(Room room) {

        if (room.getFloor() < 0) {
            throw new RuntimeException(
                    "Floor cannot be negative"
            );
        }

        if (room.getCapacity() <= 0) {
            throw new RuntimeException(
                    "Capacity must be greater than zero"
            );
        }

        if (room.getOccupiedBeds() == null) {
            room.setOccupiedBeds(0);
        }

        if (room.getOccupiedBeds() < 0) {
            throw new RuntimeException(
                    "Occupied beds cannot be negative"
            );
        }

        if (room.getOccupiedBeds()
                > room.getCapacity()) {

            throw new RuntimeException(
                    "Occupied beds cannot exceed room capacity"
            );
        }
    }
}