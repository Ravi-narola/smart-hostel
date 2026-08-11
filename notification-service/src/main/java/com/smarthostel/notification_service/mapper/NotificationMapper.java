package com.smarthostel.notification_service.mapper;

import com.smarthostel.notification_service.dto.NotificationRequest;
import com.smarthostel.notification_service.dto.NotificationResponse;
import com.smarthostel.notification_service.entity.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public Notification toEntity(NotificationRequest request) {
        return Notification.builder()
                .userId(request.getUserId())
                .title(request.getTitle())
                .message(request.getMessage())
                .type(request.getType())
                .isRead(request.getIsRead())
                .build();
    }

    public NotificationResponse toResponse(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .userId(notification.getUserId())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .type(notification.getType())
                .isRead(notification.getIsRead())
                .createdAt(notification.getCreatedAt())
                .readAt(notification.getReadAt())
                .build();
    }

    public void updateEntity(
            Notification notification,
            NotificationRequest request) {

        notification.setUserId(request.getUserId());
        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());
        notification.setType(request.getType());

        if (request.getIsRead() != null) {
            notification.setIsRead(request.getIsRead());
        }
    }
}   