package com.smarthostel.notification_service.service;

import com.smarthostel.notification_service.entity.Notification;

import java.util.List;

public interface NotificationService {

    Notification createNotification(Notification notification);

    Notification getNotificationById(Long id);

    List<Notification> getAllNotifications();

    List<Notification> getNotificationsByUser(Long userId);

    List<Notification> getUnreadNotifications(Long userId);

    List<Notification> getReadNotifications(Long userId);

    List<Notification> getNotificationsByType(String type);

    Notification markAsRead(Long id);

    Notification updateNotification(Long id, Notification notification);

    void deleteNotification(Long id);
}