package com.niit.ToDo.config.guest;

import com.niit.rabbitmq.domain.UserDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProducerGuest {

    private RabbitTemplate rabbitTemplateGuest;


    private DirectExchange exchangeGuest;

    @Autowired
    public ProducerGuest( @Qualifier("rabbitTemplateGuest") RabbitTemplate rabbitTemplateGuest,@Qualifier("directExchangeGuest") DirectExchange exchangeGuest) {
        super();
        this.rabbitTemplateGuest = rabbitTemplateGuest;
        this.exchangeGuest = exchangeGuest;
    }

    public void sendMessageToRabbitMqGuest(UserDTO userDTO)
    {
        rabbitTemplateGuest.convertAndSend(exchangeGuest.getName(),"user_routing_guest",userDTO);
    }
}
