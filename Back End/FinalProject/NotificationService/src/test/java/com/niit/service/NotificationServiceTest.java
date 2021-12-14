//package com.niit.service;
//
//import com.niit.exception.NotificationNotFoundException;
//import com.niit.model.Notification;
//import com.niit.repository.NotificationRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.mockito.internal.verification.VerificationModeFactory.times;
//
//@ExtendWith(MockitoExtension.class)
//public class NotificationServiceTest {
//
//    @Mock
//    private NotificationRepository notificationRepository;
//
//    @InjectMocks
//    private NotificationServiceImpl notificationService;
//
//    private Notification notification1;
//    private Notification notification2;
//    private List<Notification> allNotifications = new ArrayList<>();
//
//    @BeforeEach
//    public void setUp() {
//        notification1 = new Notification(1, "title1", "content1", null);
//        notification2 = new Notification(2, "title2", "content2", null);
//        allNotifications.add(notification1);
//        allNotifications.add(notification2);
//    }
//
//    @AfterEach
//    public void tearDown() {
//        notification1 = null;
//        notification2 = null;
//    }
//
//    @Test
//    public void saveNotificationTest(){
//        when(notificationRepository.save(any())).thenReturn(notification1);
//        assertEquals(notification1,notificationService.saveNotification(notification1));
//        verify(notificationRepository,times(1)).save(any());
//    }
//
//    @Test
//    public void deleteNotificationTest() throws NotificationNotFoundException {
//
//        when(notificationRepository.save(any())).thenReturn(notification1);
//        assertEquals(notification1,notificationService.saveNotification(notification1));
//        try {
//            assertTrue(notificationService.deleteNotification(1152));
//        } catch (NotificationNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void getAllNotificationTest()  {
//        when(notificationRepository.findAll()).thenReturn(allNotifications);
//        assertEquals(allNotifications,notificationService.getAllNotification());
//        verify(notificationRepository,times(1)).findAll();
//
//    }
//
//
//
//}
