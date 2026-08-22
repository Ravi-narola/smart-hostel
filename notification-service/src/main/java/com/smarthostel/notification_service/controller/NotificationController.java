package com.smarthostel.notification_service.controller;

import com.smarthostel.notification_service.dto.NotificationRequest;
import com.smarthostel.notification_service.dto.NotificationResponse;
import com.smarthostel.notification_service.entity.Notification;
import com.smarthostel.notification_service.mapper.NotificationMapper;
import com.smarthostel.notification_service.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;

    @PostMapping
    public ResponseEntity<NotificationResponse>
    createNotification(
            @Valid @RequestBody NotificationRequest request) {

        Notification notification =
                notificationMapper.toEntity(request);

        Notification saved =
                notificationService
                        .createNotification(notification);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        notificationMapper
                                .toResponse(saved)
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse>
    getNotificationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                notificationMapper.toResponse(
                        notificationService
                                .getNotificationById(id)
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>>
    getAllNotifications() {

        return ResponseEntity.ok(
                notificationService
                        .getAllNotifications()
                        .stream()
                        .map(notificationMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationResponse>>
    getNotificationsByUserId(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                notificationService
                        .getNotificationsByUserId(userId)
                        .stream()
                        .map(notificationMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/user/{userId}/read/{isRead}")
    public ResponseEntity<List<NotificationResponse>>
    getNotificationsByUserAndReadStatus(
            @PathVariable Long userId,
            @PathVariable Boolean isRead) {

        return ResponseEntity.ok(
                notificationService
                        .getNotificationsByUserAndReadStatus(
                                userId,
                                isRead
                        )
                        .stream()
                        .map(notificationMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/read/{isRead}")
    public ResponseEntity<List<NotificationResponse>>
    getNotificationsByReadStatus(
            @PathVariable Boolean isRead) {

        return ResponseEntity.ok(
                notificationService
                        .getNotificationsByReadStatus(isRead)
                        .stream()
                        .map(notificationMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<NotificationResponse>>
    getNotificationsByType(
            @PathVariable String type) {

        return ResponseEntity.ok(
                notificationService
                        .getNotificationsByType(type)
                        .stream()
                        .map(notificationMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/user/{userId}/unread/count")
    public ResponseEntity<Long>
    countUnreadNotifications(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                notificationService
                        .countUnreadNotifications(userId)
        );
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<NotificationResponse>
    markAsRead(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                notificationMapper.toResponse(
                        notificationService.markAsRead(id)
                )
        );
    }

    @PutMapping("/{id}/unread")
    public ResponseEntity<NotificationResponse>
    markAsUnread(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                notificationMapper.toResponse(
                        notificationService.markAsUnread(id)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationResponse>
    updateNotification(
            @PathVariable Long id,
            @Valid @RequestBody NotificationRequest request) {

        Notification notification =
                notificationMapper.toEntity(request);

        Notification updated =
                notificationService.updateNotification(
                        id,
                        notification
                );

        return ResponseEntity.ok(
                notificationMapper.toResponse(updated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(
            @PathVariable Long id) {

        notificationService.deleteNotification(id);

        return ResponseEntity.noContent().build();
    }
}