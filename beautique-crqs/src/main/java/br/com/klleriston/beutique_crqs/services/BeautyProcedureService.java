package br.com.klleriston.beutique_crqs.services;

import br.com.klleriston.beutique_crqs.dtos.BeautyProcedureDTO;
import org.springframework.stereotype.Service;

@Service
public interface BeautyProcedureService {
    BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedureDTO);
    void delete(Long id);
    BeautyProcedureDTO update(BeautyProcedureDTO beautyProcedureDTO);
}
