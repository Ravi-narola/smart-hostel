package com.smarthostel.notification_service.service;

import com.smarthostel.notification_service.entity.Notification;

import java.util.List;

public interface NotificationService {

    Notification createNotification(
            Notification notification
    );

    Notification getNotificationById(Long id);

    List<Notification> getAllNotifications();

    List<Notification> getNotificationsByUserId(
            Long userId
    );

    List<Notification> getNotificationsByUserAndReadStatus(
            Long userId,
            Boolean isRead
    );

    List<Notification> getNotificationsByReadStatus(
            Boolean isRead
    );

    List<Notification> getNotificationsByType(
            String type
    );

    long countUnreadNotifications(Long userId);

    Notification markAsRead(Long id);

    Notification markAsUnread(Long id);

    Notification updateNotification(
            Long id,
            Notification notification
    );

    void deleteNotification(Long id);
}