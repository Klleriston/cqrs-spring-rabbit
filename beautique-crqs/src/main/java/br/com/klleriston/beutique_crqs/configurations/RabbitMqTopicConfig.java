package br.com.klleriston.beutique_crqs.configurations;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqTopicConfig {
    public final String exchangeName = "beautiqueExchange";

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue queue() {
        return new Queue("customerQueue", true);
    }

    @Bean
    Binding bindingCustomer(Queue customerQueue, TopicExchange exchange) {
        return BindingBuilder.bind(customerQueue).to(exchange).with("customer.#");
    }

    @Bean
    public Queue beautyProceduresQueue() {
        return new Queue("beautyProceduresQueue", true);
    }

    @Bean
    Binding bindingBeautyProcedures(Queue beautyProceduresQueue, TopicExchange exchange) {
        return BindingBuilder.bind(beautyProceduresQueue).to(exchange).with("beautyProcedures.#");
    }

    @Bean
    public Queue AppointmentsQueue() {
        return new Queue("appointmentsQueue", true);
    }

    @Bean
    Binding bindingAppointments(Queue appointmentsQueue, TopicExchange exchange) {
        return BindingBuilder.bind(appointmentsQueue).to(exchange).with("appointmentsQueue.#");
    }
}
