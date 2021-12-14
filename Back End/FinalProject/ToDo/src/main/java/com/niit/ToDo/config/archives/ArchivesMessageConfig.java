package com.niit.ToDo.config.archives;

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
public class ArchivesMessageConfig {

    private String exchangeNameArchives="user_exchange_archives";
    private String registerQueueArchives="user_queue_archives";

    @Bean
    public DirectExchange directExchangeArchives()
    {
        return new DirectExchange(exchangeNameArchives);
    }


    @Bean
    public Queue registerQueueArchives()
    {
        return new Queue(registerQueueArchives,false);
    }


    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverterArchives()
    {
        return new  Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplateArchives(final ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemp=new RabbitTemplate(connectionFactory);
        rabbitTemp.setMessageConverter(producerJackson2MessageConverterArchives());
        return rabbitTemp;
    }

    @Bean
    Binding bindingUserArchives(@Qualifier("registerQueueArchives") Queue registerQueue,@Qualifier("directExchangeArchives") DirectExchange exchange)
    {
        return BindingBuilder.bind(registerQueueArchives()).to(exchange).with("user_routing_archives");
    }
}
