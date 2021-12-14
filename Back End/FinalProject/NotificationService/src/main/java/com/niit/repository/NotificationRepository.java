package com.niit.repository;

import com.niit.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NotificationRepository  extends MongoRepository<Notification,Integer> {
    @Query("{'Notification.notificationTitle' : {$in : [?0]}}")
    List<Notification>  findNotificationByNotificationTitle(String notificationTitle);
}
