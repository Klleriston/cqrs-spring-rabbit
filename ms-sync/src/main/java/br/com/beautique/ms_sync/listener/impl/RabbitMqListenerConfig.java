package br.com.beautique.ms_sync.listener.impl;

import br.com.beautique.ms_sync.dtos.appointments.FullAppointmentDTO;
import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.ms_sync.dtos.customers.CustomerDTO;
import br.com.beautique.ms_sync.listener.ListenerConfig;
import br.com.beautique.ms_sync.services.SyncService;
import br.com.beautique.ms_sync.utils.SyncLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMqListenerConfig implements ListenerConfig {

    private final ObjectMapper objectMapper;

    private final SyncService syncService;

    public RabbitMqListenerConfig(ObjectMapper objectMapper, SyncService syncService) {
        this.objectMapper = objectMapper;
        this.syncService = syncService;
    }

    @RabbitListener(queues="customerQueue")
    @Override
    public void listenToCustomerQueue(String message) {
        try {
            CustomerDTO customer = objectMapper.readValue(message, CustomerDTO.class);
            syncService.syncCustomer(customer);
            SyncLogger.info("Message received from customer queue: " + customer.toString());
        } catch (Exception e) {
            SyncLogger.error(e.getMessage());
        }
    }

    @RabbitListener(queues="appointmentsQueue")
    @Override
    public void listenToAppointmentQueue(String message) {
        try {
            FullAppointmentDTO appointment = objectMapper.readValue(message, FullAppointmentDTO.class);
            syncService.syncAppointment(appointment);
            SyncLogger.info("Message received from appointment queue: " + appointment.toString());
        } catch (Exception e) {
            SyncLogger.error(e.getMessage());
        }
    }

    @RabbitListener(queues = "beautyProceduresQueue")
    @Override
    public void listenToBeautyProcedureQueue(String message) {
        try {
            BeautyProcedureDTO beautyProcedure = objectMapper.readValue(message, BeautyProcedureDTO.class);
            syncService.syncBeautyProcedure(beautyProcedure);
            SyncLogger.info("Message received from beauty procedure queue: " + beautyProcedure.toString());
        } catch (Exception e) {
            SyncLogger.error(e.getMessage());
        }
    }
}
