package br.com.ms_beautique_query.repositories;

import br.com.ms_beautique_query.dtos.appointments.FullAppointmentDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepository extends MongoRepository<FullAppointmentDTO, Long> {
}
