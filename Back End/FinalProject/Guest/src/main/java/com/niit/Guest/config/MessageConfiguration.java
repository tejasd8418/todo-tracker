//package com.niit.Guest.config;
//
//import com.niit.Guest.domain.User;
//import com.niit.Guest.exception.UserAlreadyExistsException;
//import com.niit.Guest.service.GuestServiceImpl;
//import com.niit.rabbitmq.domain.UserDTO;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MessageConfiguration {
//
//    @Bean
//    public Jackson2JsonMessageConverter jsonMessageConverter()
//    {
//        return new Jackson2JsonMessageConverter();
//    }
//}
