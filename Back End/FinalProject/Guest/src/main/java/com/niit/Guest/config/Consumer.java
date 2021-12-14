//package com.niit.Guest.config;
//
//import com.niit.Guest.domain.User;
//import com.niit.Guest.exception.UserAlreadyExistsException;
//import com.niit.Guest.service.GuestServiceImpl;
//import com.niit.rabbitmq.domain.UserDTO;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Consumer {
//
//    @Autowired
//    private GuestServiceImpl guestService;
//
//    @RabbitListener(queues="user_queue_guest")
//    public void getUserDtoFromRabbitMq(UserDTO userDto) throws UserAlreadyExistsException
//    {
//        User user=new User();
//        user.setEmailId(userDto.getEmailId());
//        user.setPassword(userDto.getPassword());
//        guestService.registerUser(user);
//    }
//}
