package br.com.klleriston.beutique_crqs.services.impl;

import br.com.klleriston.beutique_crqs.dtos.BeautyProcedureDTO;
import br.com.klleriston.beutique_crqs.entitites.BeautyProceduresEntity;
import br.com.klleriston.beutique_crqs.repositories.BeautyProcedureRepository;
import br.com.klleriston.beutique_crqs.services.BeautyProcedureService;
import br.com.klleriston.beutique_crqs.services.BrokerService;
import br.com.klleriston.beutique_crqs.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {
    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Autowired
    private BrokerService brokerService;

    private final ConverterUtil<BeautyProceduresEntity, BeautyProcedureDTO> converterUtil = new ConverterUtil<>(BeautyProceduresEntity.class, BeautyProcedureDTO.class);
    @Override
    public BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedureDTO) {
        BeautyProceduresEntity beautyProceduresEntity = converterUtil.convertToSource(beautyProcedureDTO);
        BeautyProceduresEntity newBeautyProceduresEntity = beautyProcedureRepository.save(beautyProceduresEntity);
        sendBeautyProcedureToQueue(newBeautyProceduresEntity);
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

        BeautyProcedureDTO updatedBeautyProcedureDTO = converterUtil.convertToTarget(this.beautyProcedureRepository.save(beautyProceduresEntity));
        sendBeautyProcedureToQueue(beautyProceduresEntity);
        return updatedBeautyProcedureDTO;
    }

    private void sendBeautyProcedureToQueue(BeautyProceduresEntity beautyProceduresEntity) {
        BeautyProcedureDTO beautyProcedureDTO = BeautyProcedureDTO.builder()
                .id(beautyProceduresEntity.getId())
                .name(beautyProceduresEntity.getName())
                .description(beautyProceduresEntity.getDescription())
                .price(beautyProceduresEntity.getPrice())
                .build();

        brokerService.send("beautyProcedure", beautyProcedureDTO);
    }
}
