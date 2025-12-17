package br.com.klleriston.beutique_crqs.repositories;

import br.com.klleriston.beutique_crqs.entitites.AppointmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentsEntity, Long> {
}
