package com.niit.controller;

import com.niit.exception.NotificationNotFoundException;
import com.niit.model.Notification;
import com.niit.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v3/notificationservice/")
@CrossOrigin("*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    private ResponseEntity responseEntity;

    @PostMapping("add")
    public ResponseEntity<?> saveNotification(@RequestBody Notification notification){
        try{
            notificationService.saveNotification(notification);
            responseEntity = new ResponseEntity(notification, HttpStatus.CREATED);
        }
        catch (Exception ex){
            responseEntity = new ResponseEntity("error while adding notificationdata",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("notification/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable("id") int id) throws NotificationNotFoundException {
        try {
            notificationService.deleteNotification(id);
            responseEntity = new ResponseEntity("Notification deleted sucessfully", HttpStatus.OK);
        } catch (NotificationNotFoundException e) {
            throw new NotificationNotFoundException();
        } catch (Exception ex) {
            responseEntity = new ResponseEntity("error while deleting notificationdata", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

     @GetMapping("notifications")
     public ResponseEntity<?> getAllNotifications(){
         try{
             responseEntity = new ResponseEntity(notificationService.getAllNotification(),HttpStatus.OK);
         }
         catch (Exception ex){
             responseEntity = new ResponseEntity("error while fetching notificationdata",HttpStatus.INTERNAL_SERVER_ERROR);
         }
            return responseEntity;

    }
    @GetMapping("notifications/{notificationTitle}")
    public ResponseEntity<?> getNotificationByNotificationTitle(@PathVariable("notificationTitle") String notificationTitle){
        try{
            responseEntity = new ResponseEntity(notificationService.findNotificationByNotificationTitle(notificationTitle),HttpStatus.OK);

        }
        catch (Exception ex){
            responseEntity = new ResponseEntity("error while fetching notificationdata",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
