package br.com.klleriston.beutique_crqs.services.impl;

import br.com.klleriston.beutique_crqs.configurations.RabbitMqTopicConfig;
import br.com.klleriston.beutique_crqs.services.BrokerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqServiceImpl implements BrokerService {
    private ObjectMapper objectMapper;
    private RabbitTemplate rabbitTemplate;
    private RabbitMqTopicConfig rabbitMqTopicConfig;

    public RabbitMqServiceImpl(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate, RabbitMqTopicConfig rabbitMqTopicConfig) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMqTopicConfig = rabbitMqTopicConfig;
    }

    @Override
    public void send(String type, Object data) {
        String routingKey = type + ".#";
        try {
            String jsonData = objectMapper.writeValueAsString(data);
            rabbitTemplate.convertAndSend(rabbitMqTopicConfig.exchangeName, routingKey, jsonData, message -> {
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return message;
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
