package br.com.ms_beautique_query.controllers;

import br.com.ms_beautique_query.dtos.appointments.FullAppointmentDTO;
import br.com.ms_beautique_query.dtos.beautyProcedures.BeautyProcedureDTO;
import br.com.ms_beautique_query.service.AppointmentService;
import br.com.ms_beautique_query.service.BeautyProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping()
    ResponseEntity<List<FullAppointmentDTO>> findAll() {
        return ResponseEntity.ok(appointmentService.listAllAppointments());
    }

    @GetMapping("/user/{userId}")
    ResponseEntity<List<FullAppointmentDTO>> listAllAppointmentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(appointmentService.listAllAppointmentsByUser(userId));
    }

    @GetMapping("/beauty-procedure/{beautyProcedureId}")
    ResponseEntity<List<FullAppointmentDTO>> listAllAppointmentsByBeautyProcedure(@PathVariable Long beautyProcedureId) {
        return ResponseEntity.ok(appointmentService.lisAllAppointmentsByBeautyProcedure(beautyProcedureId));
    }
}
