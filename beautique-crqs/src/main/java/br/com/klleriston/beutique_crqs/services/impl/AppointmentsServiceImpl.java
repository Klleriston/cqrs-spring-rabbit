package br.com.klleriston.beutique_crqs.services.impl;

import br.com.klleriston.beutique_crqs.dtos.AppointmentDTO;
import br.com.klleriston.beutique_crqs.entitites.AppointmentsEntity;
import br.com.klleriston.beutique_crqs.entitites.BeautyProceduresEntity;
import br.com.klleriston.beutique_crqs.entitites.CustomerEntity;
import br.com.klleriston.beutique_crqs.repositories.AppointmentRepository;
import br.com.klleriston.beutique_crqs.repositories.BeautyProcedureRepository;
import br.com.klleriston.beutique_crqs.repositories.CustomerRepository;
import br.com.klleriston.beutique_crqs.services.AppointmentsService;
import br.com.klleriston.beutique_crqs.utils.ConverterUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentsServiceImpl implements AppointmentsService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private final ConverterUtil<AppointmentsEntity, AppointmentDTO> converterUtil = new ConverterUtil<>(AppointmentsEntity.class, AppointmentDTO.class);

    @Override
    @Transactional
    public AppointmentDTO create(AppointmentDTO appointmentDTO) {
        AppointmentsEntity appointmentsEntity = converterUtil.convertToSource(appointmentDTO);
        AppointmentsEntity newAppointmentsEntity = appointmentRepository.save(appointmentsEntity);
        return converterUtil.convertToTarget(newAppointmentsEntity);
    }

    @Override
    @Transactional
    public AppointmentDTO update(AppointmentDTO appointmentDTO) {
        Optional<AppointmentsEntity> currentAppointment = appointmentRepository.findById(appointmentDTO.getId());
        if (currentAppointment.isEmpty()) {
            throw new RuntimeException("Appointment not found");
        }
        AppointmentsEntity appointmentsEntity = converterUtil.convertToSource(appointmentDTO);
        appointmentsEntity.setCreatedAt(currentAppointment.get().getCreatedAt());
        AppointmentsEntity updatedAppointmentsEntity = appointmentRepository.save(appointmentsEntity);
        return converterUtil.convertToTarget(updatedAppointmentsEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<AppointmentsEntity> appointmentsEntity = appointmentRepository.findById(id);
        if (appointmentsEntity.isEmpty()) {
            throw new RuntimeException("Appointment with id " + id + " not found");
        }
        appointmentRepository.delete(appointmentsEntity.get());
    }

    @Override
    @Transactional
    public AppointmentDTO setCustomerToAppointment(AppointmentDTO appointmentDTO) {
        CustomerEntity customerEntity = findCustomerById(appointmentDTO.getCustomer());
        BeautyProceduresEntity beautyProceduresEntity = findBeautyProcedureById(appointmentDTO.getBeautyProcedure());
        AppointmentsEntity appointmentsEntity = findAppointmentById(appointmentDTO.getId());
        appointmentsEntity.setCustomer(customerEntity);
        appointmentsEntity.setBeautyProcedure(beautyProceduresEntity);
        appointmentsEntity.setAppointmentIsOpen(false);

        AppointmentsEntity updatedAppointment = appointmentRepository.save(appointmentsEntity);
        return converterUtil.convertToTarget(updatedAppointment);
    }

    private AppointmentsEntity findAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Appointment with id " + id + " not found"));
    }

    private CustomerEntity findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer with id " + id + " not found"));
    }

    private BeautyProceduresEntity findBeautyProcedureById(Long id) {
        return beautyProcedureRepository.findById(id).orElseThrow(() -> new RuntimeException("Beauty procedure with id " + id + " not found"));
    }

    private AppointmentDTO builderAppointmentDTO(AppointmentsEntity appointmentsEntity) {
        return AppointmentDTO.builder()
                .id(appointmentsEntity.getId())
                .beautyProcedure(appointmentsEntity.getBeautyProcedure().getId())
                .dateTime(appointmentsEntity.getDate())
                .appointmentOpen(appointmentsEntity.getAppointmentIsOpen())
                .customer(appointmentsEntity.getCustomer().getId())
                .build();
    }
}
