package com.niit.ToDo.config.archives;

import com.niit.rabbitmq.domain.UserDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProducerArchives {


    private RabbitTemplate rabbitTemplateArchives;


    private DirectExchange exchangeArchives;

    @Autowired
    public ProducerArchives( @Qualifier("rabbitTemplateArchives") RabbitTemplate rabbitTemplateArchives,@Qualifier("directExchangeArchives") DirectExchange exchangeArchives) {
        super();
        this.rabbitTemplateArchives = rabbitTemplateArchives;
        this.exchangeArchives = exchangeArchives;
    }

    public void sendMessageToRabbitMqArchives(UserDTO userDTO)
    {
        rabbitTemplateArchives.convertAndSend(exchangeArchives.getName(),"user_routing_archives",userDTO);
    }
}
