//package com.niit.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import com.niit.model.Notification;
//import com.niit.service.NotificationService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.mockito.internal.verification.VerificationModeFactory.times;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//public class NotificationControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Mock
//    private NotificationService notificationService;
//
//    private Notification notification1;
//    private Notification notification2;
//    private List<Notification> allNotifications = new ArrayList<>();
//
//    @InjectMocks
//    private NotificationController notificationController;
//
//    @BeforeEach
//    public void setUp() {
//        notification1 = new Notification(1, "title1", "content1", null);
//        notification2 = new Notification(2, "title2", "content2", null);
//        allNotifications.add(notification1);
//        allNotifications.add(notification2);
//        mockMvc= MockMvcBuilders.standaloneSetup(notificationController).build();
//    }
//
//    @AfterEach
//    public void tearDown() {
//        notification1 = null;
//        notification2 = null;
//    }
//
//    private String jsontoString(final Object obj) {
//        String result;
//        try {
//            ObjectMapper objectMapper=new ObjectMapper();
//            String jsonContent = objectMapper.writeValueAsString(obj);
//            result = jsonContent;
//        }
//        catch (JsonProcessingException ex){
//            result="error while converting to string";
//        }
//        return result;
//    }
//
//    @Test
//    public void saveNotificationTest() throws Exception {
//        when(notificationService.saveNotification(notification1)).thenReturn(notification1);
//        mockMvc.perform(post("/api/v3/notificationservice/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsontoString(notification1)))
//                .andExpect(status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//        verify(notificationService,times(1)).saveNotification(any());
//    }
//
//    @Test
//    public void deleteNotificationTest() throws Exception {
//        when(notificationService.deleteNotification(any())).thenReturn(true);
//        mockMvc.perform(delete("/api/v3/notificationservice/notification/112")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        verify(notificationService,times(1)).deleteNotification(any());
//    }
//
//    @Test
//    public void getAllProductTest() throws Exception {
//        when(notificationService.getAllNotification()).thenReturn(allNotifications);
//        mockMvc.perform(get("/api/v3/notificationservice/notifications")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        verify(notificationService,times(1)).getAllNotification();
//
//    }
//
//}
