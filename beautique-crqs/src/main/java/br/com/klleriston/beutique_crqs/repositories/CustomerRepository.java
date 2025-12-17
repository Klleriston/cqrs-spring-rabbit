package br.com.klleriston.beutique_crqs.repositories;

import br.com.klleriston.beutique_crqs.entitites.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
