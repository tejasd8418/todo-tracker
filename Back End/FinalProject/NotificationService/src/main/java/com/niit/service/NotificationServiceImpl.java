package com.niit.service;

import com.niit.exception.NotificationNotFoundException;
import com.niit.model.Notification;
import com.niit.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    public NotificationRepository notificationRepository;

    @Override
    public Notification saveNotification(Notification notification) {

        return notificationRepository.save(notification);
    }

    @Override
    public boolean deleteNotification(int id) throws NotificationNotFoundException {
        boolean result=false;
        if(notificationRepository.findById(id).isEmpty()){
            throw new NotificationNotFoundException();
        }
        else{
            notificationRepository.deleteById(id);
            result = true;
        }
        return result;
    }

    @Override
    public List<Notification> getAllNotification() {
        return notificationRepository.findAll();
    }

    @Override
    public List<Notification> findNotificationByNotificationTitle(String notificationTitle) {
        return notificationRepository.findNotificationByNotificationTitle(notificationTitle);
    }
}
