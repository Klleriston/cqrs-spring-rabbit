package br.com.klleriston.beutique_crqs.services.impl;

import br.com.klleriston.beutique_crqs.dtos.CustomerDTO;
import br.com.klleriston.beutique_crqs.entitites.CustomerEntity;
import br.com.klleriston.beutique_crqs.repositories.CustomerRepository;
import br.com.klleriston.beutique_crqs.services.CustomerService;
import br.com.klleriston.beutique_crqs.utils.ConverterUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    private final ConverterUtil<CustomerEntity, CustomerDTO> converterUtil = new ConverterUtil<>(CustomerEntity.class, CustomerDTO.class);

    @Override
    @Transactional
    public CustomerDTO create(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = converterUtil.convertToSource(customerDTO);
        CustomerEntity savedCustomerEntity = customerRepository.save(customerEntity);
        return converterUtil.convertToTarget(savedCustomerEntity);
    }

    @Override
    public void delete(Long id) {
       Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
       if (customerEntity.isEmpty()) {
           throw new RuntimeException("Customer with id " + id + " not found");
       }
       customerRepository.delete(customerEntity.get());
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
       Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(customerDTO.getId());
       if (customerEntityOptional.isEmpty()) {
           throw new RuntimeException("Customer with id " + customerDTO.getId() + " not found");
       }
       CustomerEntity customerEntity = converterUtil.convertToSource(customerDTO);
       customerEntity.setAppointments(customerEntityOptional.get().getAppointments());
       customerEntity.setCreatedAt(customerEntityOptional.get().getCreatedAt());
       return converterUtil.convertToTarget(this.customerRepository.save(customerEntity));
    }
}
