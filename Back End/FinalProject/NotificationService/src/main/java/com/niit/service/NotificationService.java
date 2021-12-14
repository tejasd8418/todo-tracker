package com.niit.service;

import com.niit.exception.NotificationNotFoundException;
import com.niit.model.Notification;

import java.util.List;

public interface NotificationService {
    public Notification saveNotification(Notification notification);
    public boolean deleteNotification(int id) throws NotificationNotFoundException;
    public List<Notification> getAllNotification();
    List<Notification> findNotificationByNotificationTitle(String notificationTitle);
}
