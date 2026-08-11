package com.smarthostel.room_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 20)
    private String roomNumber;

    @NotNull
    @Column(nullable = false)
    private Integer floor;

    @NotNull
    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Integer occupiedBeds = 0;

    @NotBlank
    @Column(nullable = false, length = 20)
    private String roomType;

    @NotBlank
    @Column(nullable = false, length = 20)
    private String status;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if (occupiedBeds == null) {
            occupiedBeds = 0;
        }

        if (status == null) {
            status = "AVAILABLE";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}