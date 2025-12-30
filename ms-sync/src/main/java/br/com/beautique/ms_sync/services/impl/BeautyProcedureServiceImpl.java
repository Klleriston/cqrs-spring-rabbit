package br.com.beautique.ms_sync.services.impl;

import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.ms_sync.repositories.BeautyProcedureRepository;
import br.com.beautique.ms_sync.services.BeautyProcedureService;
import br.com.beautique.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {
    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Override
    public void saveBeautyProcedure(BeautyProcedureDTO beautyProcedureDTO) {
        try {
            SyncLogger.info("Saving Beauty Procedure: " + beautyProcedureDTO.toString());
            beautyProcedureRepository.save(beautyProcedureDTO);
        } catch (Exception e) {
            SyncLogger.error(e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
