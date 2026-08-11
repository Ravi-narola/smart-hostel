package com.smarthostel.room_service.mapper;

import com.smarthostel.room_service.dto.RoomRequest;
import com.smarthostel.room_service.dto.RoomResponse;
import com.smarthostel.room_service.entity.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public Room toEntity(RoomRequest request) {
        return Room.builder()
                .roomNumber(request.getRoomNumber())
                .floor(request.getFloor())
                .capacity(request.getCapacity())
                .occupiedBeds(request.getOccupiedBeds())
                .roomType(request.getRoomType())
                .status(request.getStatus())
                .description(request.getDescription())
                .build();
    }

    public RoomResponse toResponse(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .floor(room.getFloor())
                .capacity(room.getCapacity())
                .occupiedBeds(room.getOccupiedBeds())
                .roomType(room.getRoomType())
                .status(room.getStatus())
                .description(room.getDescription())
                .createdAt(room.getCreatedAt())
                .updatedAt(room.getUpdatedAt())
                .build();
    }

    public void updateEntity(Room room, RoomRequest request) {
        room.setRoomNumber(request.getRoomNumber());
        room.setFloor(request.getFloor());
        room.setCapacity(request.getCapacity());
        room.setOccupiedBeds(request.getOccupiedBeds());
        room.setRoomType(request.getRoomType());
        room.setStatus(request.getStatus());
        room.setDescription(request.getDescription());
    }
}