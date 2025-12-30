package br.com.beautique.ms_sync.services.impl;

import br.com.beautique.ms_sync.dtos.appointments.FullAppointmentDTO;
import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.ms_sync.dtos.customers.CustomerDTO;
import br.com.beautique.ms_sync.services.AppointmentService;
import br.com.beautique.ms_sync.services.BeautyProcedureService;
import br.com.beautique.ms_sync.services.CustomerService;
import br.com.beautique.ms_sync.services.SyncService;
import br.com.beautique.ms_sync.utils.SyncLogger;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SyncServiceImpl implements SyncService {
    private final AppointmentService appointmentService;
    private final CustomerService customerService;
    private final BeautyProcedureService beautyProcedureService;

    public SyncServiceImpl(AppointmentService appointmentService, CustomerService customerService,  BeautyProcedureService beautyProcedureService) {
        this.appointmentService = appointmentService;
        this.customerService = customerService;
        this.beautyProcedureService = beautyProcedureService;
    }

    @Override
    public void syncCustomer(CustomerDTO customerDTO) {
        try {
            SyncLogger.info(String.format("Sync service started. Customer: %s", customerDTO));
            customerService.saveCustomer(customerDTO);
            SyncLogger.info("Updating appointment customer: " + customerDTO.getId());
            appointmentService.updateAppointmentCustomer(customerDTO);
        } catch (Exception e) {
            SyncLogger.info("Error saving customer: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void syncAppointment(FullAppointmentDTO appointmentDTO) {
        try {
            SyncLogger.info(String.format("Sync service started. Appoimtment: %s", appointmentDTO));
            appointmentService.saveAppointment(appointmentDTO);
        } catch (Exception e) {
            SyncLogger.info("Error saving appointment: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void syncBeautyProcedure(BeautyProcedureDTO beautyProcedureDTO) {
        try {
            SyncLogger.info(String.format("Sync service started. Beauty Procedure: %s", beautyProcedureDTO));
            beautyProcedureService.saveBeautyProcedure(beautyProcedureDTO);
            SyncLogger.info("Updating appointment beauty procedure: " + beautyProcedureDTO.getId());
            appointmentService.updateAppointmentBeautyProcedures(beautyProcedureDTO);
        } catch (Exception e) {
            SyncLogger.info("Error saving beauty procedure: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
