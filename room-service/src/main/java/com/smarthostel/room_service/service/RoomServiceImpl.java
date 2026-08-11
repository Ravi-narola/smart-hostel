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
        return roomRepository.save(room);
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Room not found with id: " + id));
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> getRoomsByFloor(Integer floor) {
        return roomRepository.findByFloor(floor);
    }

    @Override
    public List<Room> getRoomsByStatus(String status) {
        return roomRepository.findByStatus(status);
    }

    @Override
    public List<Room> getRoomsByType(String roomType) {
        return roomRepository.findByRoomType(roomType);
    }

    @Override
    public Room updateRoom(Long id, Room room) {
        Room existingRoom = getRoomById(id);

        existingRoom.setRoomNumber(room.getRoomNumber());
        existingRoom.setFloor(room.getFloor());
        existingRoom.setCapacity(room.getCapacity());
        existingRoom.setOccupiedBeds(room.getOccupiedBeds());
        existingRoom.setRoomType(room.getRoomType());
        existingRoom.setStatus(room.getStatus());
        existingRoom.setDescription(room.getDescription());

        return roomRepository.save(existingRoom);
    }

    @Override
    public void deleteRoom(Long id) {
        Room room = getRoomById(id);
        roomRepository.delete(room);
    }
}