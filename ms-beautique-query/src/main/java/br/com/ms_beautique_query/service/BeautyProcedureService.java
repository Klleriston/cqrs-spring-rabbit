package br.com.ms_beautique_query.service;

import br.com.ms_beautique_query.dtos.beautyProcedures.BeautyProcedureDTO;

import java.util.List;

public interface BeautyProcedureService {
    List<BeautyProcedureDTO> listAll();
    List<BeautyProcedureDTO> listByNameLikeIgnoreCase(String name);
    List<BeautyProcedureDTO> listByDescriptionLikeIgnoreCase(String description);
}
