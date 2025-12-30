package br.com.ms_beautique_query.service.impl;

import br.com.ms_beautique_query.dtos.appointments.FullAppointmentDTO;
import br.com.ms_beautique_query.repositories.AppointmentRepository;
import br.com.ms_beautique_query.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<FullAppointmentDTO> listAllAppointments() {
        try {
            return appointmentRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar os appointments.");
        }
    }

    @Override
    public List<FullAppointmentDTO> listAllAppointmentsByUser(Long userId) {
        try {
            return appointmentRepository.listAppointmentByCustomerId(userId);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar os appointments.");
        }
    }

    @Override
    public List<FullAppointmentDTO> lisAllAppointmentsByBeautyProcedure(Long beautyProcedureId) {
        try {
            return appointmentRepository.listAppointmentsByBeautyProcedureId(beautyProcedureId);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar os appointments.");
        }
    }
}
