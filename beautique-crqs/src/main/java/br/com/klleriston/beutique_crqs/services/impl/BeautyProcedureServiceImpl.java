package br.com.klleriston.beutique_crqs.services.impl;

import br.com.klleriston.beutique_crqs.dtos.BeautyProcedureDTO;
import br.com.klleriston.beutique_crqs.entitites.BeautyProceduresEntity;
import br.com.klleriston.beutique_crqs.repositories.BeautyProcedureRepository;
import br.com.klleriston.beutique_crqs.services.BeautyProcedureService;
import br.com.klleriston.beutique_crqs.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {
    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    private final ConverterUtil<BeautyProceduresEntity, BeautyProcedureDTO> converterUtil = new ConverterUtil<>(BeautyProceduresEntity.class, BeautyProcedureDTO.class);
    @Override
    public BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedureDTO) {
        BeautyProceduresEntity beautyProceduresEntity = converterUtil.convertToSource(beautyProcedureDTO);
        BeautyProceduresEntity newBeautyProceduresEntity = beautyProcedureRepository.save(beautyProceduresEntity);
        return converterUtil.convertToTarget(newBeautyProceduresEntity);
    }

    @Override
    public void delete(Long id) {
        Optional<BeautyProceduresEntity> beautyProceduresEntity = beautyProcedureRepository.findById(id);
        if (beautyProceduresEntity.isEmpty()) {
            throw new RuntimeException("Beauty procedure not found");
        }
        beautyProcedureRepository.delete(beautyProceduresEntity.get());
    }

    @Override
    public BeautyProcedureDTO update(BeautyProcedureDTO beautyProcedureDTO) {
        Optional<BeautyProceduresEntity> beautyProceduresEntityOptional = beautyProcedureRepository.findById(beautyProcedureDTO.getId());
        if (beautyProceduresEntityOptional.isEmpty()) {
            throw new RuntimeException("Beauty procedure not found");
        }
        BeautyProceduresEntity  beautyProceduresEntity = converterUtil.convertToSource(beautyProcedureDTO);

        beautyProceduresEntity.setAppointments(beautyProceduresEntityOptional.get().getAppointments());
        beautyProceduresEntity.setCreatedAt(beautyProceduresEntityOptional.get().getCreatedAt());

        return converterUtil.convertToTarget(this.beautyProcedureRepository.save(beautyProceduresEntity));

    }
}
