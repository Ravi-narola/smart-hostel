package com.smarthostel.mess_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "mess")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String messName;

    @NotNull
    @Column(nullable = false)
    private LocalDate mealDate;

    @Column(length = 500)
    private String breakfast;

    @Column(length = 500)
    private String lunch;

    @Column(length = 500)
    private String dinner;

    @Column(length = 500)
    private String specialMenu;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if (status == null) {
            status = "ACTIVE";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}