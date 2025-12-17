package br.com.klleriston.beutique_crqs.services;

import br.com.klleriston.beutique_crqs.dtos.CustomerDTO;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    CustomerDTO create(CustomerDTO customerDTO);
    void delete(Long id);
    CustomerDTO update(CustomerDTO customerDTO);
}
