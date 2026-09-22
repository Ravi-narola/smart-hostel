package com.smarthostel.notification_service.service;

import com.smarthostel.notification_service.entity.Notification;
import com.smarthostel.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl
        implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification createNotification(
            Notification notification) {

        if (notification.getIsRead() == null) {
            notification.setIsRead(false);
        }

        return notificationRepository.save(notification);
    }

    @Override
    public Notification getNotificationById(Long id) {

        return notificationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Notification not found with id: "
                                        + id
                        ));
    }

    @Override
    public List<Notification> getAllNotifications() {

        return notificationRepository.findAll();
    }

    @Override
    public List<Notification> getNotificationsByUserId(
            Long userId) {

        return notificationRepository.findByUserId(userId);
    }

    @Override
    public List<Notification>
    getNotificationsByUserAndReadStatus(
            Long userId,
            Boolean isRead) {

        return notificationRepository
                .findByUserIdAndIsRead(
                        userId,
                        isRead
                );
    }

    @Override
    public List<Notification>
    getNotificationsByReadStatus(Boolean isRead) {

        return notificationRepository
                .findByIsRead(isRead);
    }

    @Override
    public List<Notification>
    getNotificationsByType(String type) {

        return notificationRepository.findByType(type);
    }

    @Override
    public long countUnreadNotifications(
            Long userId) {

        return notificationRepository
                .countByUserIdAndIsRead(
                        userId,
                        false
                );
    }

    @Override
    public Notification markAsRead(Long id) {

        Notification notification =
                getNotificationById(id);

        notification.setIsRead(true);

        return notificationRepository.save(
                notification
        );
    }

    @Override
    public Notification markAsUnread(Long id) {

        Notification notification =
                getNotificationById(id);

        notification.setIsRead(false);
        notification.setReadAt(null);

        return notificationRepository.save(
                notification
        );
    }

    @Override
    public Notification updateNotification(
            Long id,
            Notification notification) {

        Notification existing =
                getNotificationById(id);

        existing.setUserId(
                notification.getUserId()
        );

        existing.setTitle(
                notification.getTitle()
        );

        existing.setMessage(
                notification.getMessage()
        );

        existing.setType(
                notification.getType()
        );

        existing.setIsRead(
                notification.getIsRead()
        );

        return notificationRepository.save(existing);
    }

    @Override
    public void deleteNotification(Long id) {

        Notification notification =
                getNotificationById(id);

        notificationRepository.delete(notification);
    }
}