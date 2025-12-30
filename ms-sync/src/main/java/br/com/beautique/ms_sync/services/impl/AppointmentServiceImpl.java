package br.com.beautique.ms_sync.services.impl;

import br.com.beautique.ms_sync.dtos.appointments.FullAppointmentDTO;
import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.ms_sync.dtos.customers.CustomerDTO;
import br.com.beautique.ms_sync.repositories.AppointmentRepository;
import br.com.beautique.ms_sync.services.AppointmentService;
import br.com.beautique.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveAppointment(FullAppointmentDTO appointmentDTO) {
        try {
            SyncLogger.info(appointmentDTO.toString());
            appointmentRepository.save(appointmentDTO);
        } catch (Exception e) {
            SyncLogger.error("Error saving customer: " + e.getMessage());
            SyncLogger.error(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void updateAppointmentCustomer(CustomerDTO customer) {
        try {
            SyncLogger.info("Updating customer: " + customer.toString());
            Query query = new Query(Criteria.where("customer.id").is(customer.getId()));
            Update update = new Update().set("customer", customer);
            mongoTemplate.updateMulti(query, update, FullAppointmentDTO.class);
        } catch (Exception e) {
            SyncLogger.error("Error saving customer: " + e.getMessage());
            SyncLogger.error(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void updateAppointmentBeautyProcedures(BeautyProcedureDTO beautyProcedureDTO) {
        try {
            SyncLogger.info("Updating beauty procedure: " + beautyProcedureDTO.toString());
            Query query = new Query(Criteria.where("beautyProcedure.id").is(beautyProcedureDTO.getId()));
            Update update = new Update()
                    .set("beautyProcedure.name", beautyProcedureDTO.getName())
                    .set("beautyProcedure.description", beautyProcedureDTO.getDescription());
            mongoTemplate.updateMulti(query, update, BeautyProcedureDTO.class);
        } catch (Exception e) {
            SyncLogger.error("Error saving beauty procedure: " + e.getMessage());
            SyncLogger.error(Arrays.toString(e.getStackTrace()));
        }
    }
}
