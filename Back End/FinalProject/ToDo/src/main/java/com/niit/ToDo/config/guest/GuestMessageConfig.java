package com.niit.ToDo.config.guest;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GuestMessageConfig {

    private String exchangeNameGuest="user_exchange_guest";
    private String registerQueueGuest="user_queue_guest";

    @Bean
    public DirectExchange directExchangeGuest()
    {
        return new DirectExchange(exchangeNameGuest);
    }


    @Bean
    public Queue registerQueueGuest()
    {
        return new Queue(registerQueueGuest,false);
    }


    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverterGuest()
    {
        return new  Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplateGuest(final ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemp=new RabbitTemplate(connectionFactory);
        rabbitTemp.setMessageConverter(producerJackson2MessageConverterGuest());
        return rabbitTemp;
    }

    @Bean
    Binding bindingUserGuest(@Qualifier("registerQueueGuest") Queue registerQueue,@Qualifier("directExchangeGuest") DirectExchange exchange)
    {
        return BindingBuilder.bind(registerQueueGuest()).to(exchange).with("user_routing_guest");
    }
}
