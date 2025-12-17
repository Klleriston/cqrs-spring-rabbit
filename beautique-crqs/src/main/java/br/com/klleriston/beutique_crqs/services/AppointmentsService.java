package br.com.klleriston.beutique_crqs.services;

import br.com.klleriston.beutique_crqs.dtos.AppointmentDTO;
import org.springframework.stereotype.Service;

@Service
public interface AppointmentsService {
    AppointmentDTO create(AppointmentDTO appointmentDTO);
    AppointmentDTO update(AppointmentDTO appointmentDTO);
    void delete(Long id);
    AppointmentDTO setCustomerToAppointment(AppointmentDTO appointmentDTO);
}
