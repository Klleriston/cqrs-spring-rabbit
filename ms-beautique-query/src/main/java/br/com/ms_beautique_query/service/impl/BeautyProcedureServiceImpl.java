package br.com.ms_beautique_query.service.impl;

import br.com.ms_beautique_query.dtos.beautyProcedures.BeautyProcedureDTO;
import br.com.ms_beautique_query.repositories.BeautyProcedureRepository;
import br.com.ms_beautique_query.service.BeautyProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {
    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Override
    public List<BeautyProcedureDTO> listAll() {
        try {
            return beautyProcedureRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar os procedures.");
        }
    }

    @Override
    public List<BeautyProcedureDTO> listByNameLikeIgnoreCase(String name) {
        try {
            return beautyProcedureRepository.findByNameIgnoreCase(name);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar os procedures.");
        }
    }

    @Override
    public List<BeautyProcedureDTO> listByDescriptionLikeIgnoreCase(String description) {
        try {
            return beautyProcedureRepository.findByDescriptionIgnoreCase(description);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar os procedures.");
        }
    }
}
