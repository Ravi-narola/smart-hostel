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
    public ResponseEntity<NotificationResponse> createNotification(
            @Valid @RequestBody NotificationRequest request) {

        Notification notification =
                notificationMapper.toEntity(request);

        Notification savedNotification =
                notificationService.createNotification(notification);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(notificationMapper.toResponse(savedNotification));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotificationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                notificationMapper.toResponse(
                        notificationService.getNotificationById(id)));
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>>
    getAllNotifications() {

        return ResponseEntity.ok(
                notificationService.getAllNotifications()
                        .stream()
                        .map(notificationMapper::toResponse)
                        .toList());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationResponse>>
    getByUser(@PathVariable Long userId) {

        return ResponseEntity.ok(
                notificationService.getNotificationsByUser(userId)
                        .stream()
                        .map(notificationMapper::toResponse)
                        .toList());
    }

    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<NotificationResponse>>
    getUnread(@PathVariable Long userId) {

        return ResponseEntity.ok(
                notificationService.getUnreadNotifications(userId)
                        .stream()
                        .map(notificationMapper::toResponse)
                        .toList());
    }

    @GetMapping("/user/{userId}/read")
    public ResponseEntity<List<NotificationResponse>>
    getRead(@PathVariable Long userId) {

        return ResponseEntity.ok(
                notificationService.getReadNotifications(userId)
                        .stream()
                        .map(notificationMapper::toResponse)
                        .toList());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<NotificationResponse>>
    getByType(@PathVariable String type) {

        return ResponseEntity.ok(
                notificationService.getNotificationsByType(type)
                        .stream()
                        .map(notificationMapper::toResponse)
                        .toList());
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<NotificationResponse> markAsRead(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                notificationMapper.toResponse(
                        notificationService.markAsRead(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationResponse> updateNotification(
            @PathVariable Long id,
            @Valid @RequestBody NotificationRequest request) {

        Notification notification =
                notificationMapper.toEntity(request);

        Notification updatedNotification =
                notificationService.updateNotification(id, notification);

        return ResponseEntity.ok(
                notificationMapper.toResponse(updatedNotification));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(
            @PathVariable Long id) {

        notificationService.deleteNotification(id);

        return ResponseEntity.noContent().build();
    }
}