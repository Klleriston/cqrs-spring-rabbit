package br.com.ms_beautique_query.service;

import br.com.ms_beautique_query.dtos.appointments.FullAppointmentDTO;

import java.util.List;

public interface AppointmentService {
    List<FullAppointmentDTO> listAllAppointments();
    List<FullAppointmentDTO> listAllAppointmentsByUser(Long userId);
    List<FullAppointmentDTO> lisAllAppointmentsByBeautyProcedure(Long beautyProcedureId);
}
