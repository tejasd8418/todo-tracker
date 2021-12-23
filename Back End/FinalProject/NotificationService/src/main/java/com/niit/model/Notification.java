package com.niit.model;

import com.mongodb.internal.connection.Time;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Notification implements Serializable {
    @Id
    private int notificationId;
    private String emailId;
    private String guestEmailId;
    private String notificationTitle;
    private String notificationContent;
    private Time notificationTime;

    public Notification() {
    }

    public Notification(int notificationId, String notificationTitle, String notificationContent, Time notificationTime) {
        this.notificationId = notificationId;
        this.notificationTitle = notificationTitle;
        this.notificationContent = notificationContent;
        this.notificationTime = notificationTime;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public Time getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(Time notificationTime) {
        this.notificationTime = notificationTime;
    }
}
