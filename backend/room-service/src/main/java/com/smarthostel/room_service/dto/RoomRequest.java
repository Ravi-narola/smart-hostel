package com.smarthostel.room_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomRequest {

    @NotBlank
    private String roomNumber;

    @NotNull
    private Integer floor;

    @NotNull
    private Integer capacity;

    private Integer occupiedBeds;

    @NotBlank
    private String roomType;

    private String status;

    private String description;
}