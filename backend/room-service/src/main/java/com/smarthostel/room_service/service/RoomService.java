package com.smarthostel.room_service.service;

import com.smarthostel.room_service.entity.Room;

import java.util.List;

public interface RoomService {

    Room createRoom(Room room);

    Room getRoomById(Long id);

    List<Room> getAllRooms();

    Room getRoomByNumber(String roomNumber);

    List<Room> getRoomsByFloor(Integer floor);

    List<Room> getRoomsByType(String roomType);

    List<Room> getRoomsByStatus(String status);

    List<Room> getRoomsByMinimumCapacity(Integer capacity);

    Room updateRoom(Long id, Room room);

    void deleteRoom(Long id);
}