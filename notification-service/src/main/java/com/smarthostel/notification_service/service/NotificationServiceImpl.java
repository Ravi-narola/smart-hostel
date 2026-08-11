package com.smarthostel.notification_service.service;

import com.smarthostel.notification_service.entity.Notification;
import com.smarthostel.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification createNotification(Notification notification) {

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
                                "Notification not found with id: " + id));
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public List<Notification> getNotificationsByUser(Long userId) {
        return notificationRepository
                .findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository
                .findByUserIdAndIsReadFalseOrderByCreatedAtDesc(userId);
    }

    @Override
    public List<Notification> getReadNotifications(Long userId) {
        return notificationRepository
                .findByUserIdAndIsReadTrueOrderByCreatedAtDesc(userId);
    }

    @Override
    public List<Notification> getNotificationsByType(String type) {
        return notificationRepository
                .findByTypeOrderByCreatedAtDesc(type);
    }

    @Override
    public Notification markAsRead(Long id) {

        Notification notification = getNotificationById(id);

        notification.setIsRead(true);

        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(
            Long id,
            Notification notification) {

        Notification existingNotification =
                getNotificationById(id);

        existingNotification.setUserId(notification.getUserId());
        existingNotification.setTitle(notification.getTitle());
        existingNotification.setMessage(notification.getMessage());
        existingNotification.setType(notification.getType());

        if (notification.getIsRead() != null) {
            existingNotification.setIsRead(notification.getIsRead());
        }

        return notificationRepository.save(existingNotification);
    }

    @Override
    public void deleteNotification(Long id) {

        Notification notification = getNotificationById(id);

        notificationRepository.delete(notification);
    }
}