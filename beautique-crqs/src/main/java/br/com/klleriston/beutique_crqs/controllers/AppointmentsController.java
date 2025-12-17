package br.com.klleriston.beutique_crqs.controllers;

import br.com.klleriston.beutique_crqs.dtos.AppointmentDTO;
import br.com.klleriston.beutique_crqs.services.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {
    @Autowired
    private AppointmentsService appointmentsService;

    @PostMapping
    public AppointmentDTO create(AppointmentDTO appointmentDTO) {
        return appointmentsService.create(appointmentDTO);
    }

    @PatchMapping
    public AppointmentDTO update(@RequestBody AppointmentDTO appointmentDTO) {
        return appointmentsService.update(appointmentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointmentsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    ResponseEntity<AppointmentDTO> setCustomerToAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentsService.setCustomerToAppointment(appointmentDTO));
    }
}
