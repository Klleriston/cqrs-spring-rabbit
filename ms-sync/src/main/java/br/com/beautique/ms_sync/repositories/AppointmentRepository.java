package br.com.beautique.ms_sync.repositories;

import br.com.beautique.ms_sync.dtos.appointments.FullAppointmentDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends MongoRepository<FullAppointmentDTO, Long> {
}
