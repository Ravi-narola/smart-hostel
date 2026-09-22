package com.smarthostel.notification_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String title;

    @NotBlank
    @Column(nullable = false, length = 500)
    private String message;

    @Column(length = 30)
    private String type;

    @Column(nullable = false)
    private Boolean isRead = false;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime readAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();

        if (isRead == null) {
            isRead = false;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        if (Boolean.TRUE.equals(isRead) && readAt == null) {
            readAt = LocalDateTime.now();
        }
    }
}