//package com.niit.config;
//
//import com.niit.domain.User;
//import com.niit.exception.UserAlreadyExistsException;
//import com.niit.rabbitmq.domain.UserDTO;
//import com.niit.service.ArchivesServiceImpl;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Consumer {
//
//    @Autowired
//    private ArchivesServiceImpl archivesService;
//
//    @RabbitListener(queues="user_queue_archives")
//    public void getUserDtoFromRabbitMq(UserDTO userDto) throws UserAlreadyExistsException
//    {
//        User user=new User();
//        user.setEmailId(userDto.getEmailId());
//        user.setPassword(userDto.getPassword());
//        archivesService.registerUser(user);
//    }
//}
