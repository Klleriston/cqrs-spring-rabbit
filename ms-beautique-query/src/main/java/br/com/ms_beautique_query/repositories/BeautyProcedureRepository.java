package br.com.ms_beautique_query.repositories;

import br.com.ms_beautique_query.dtos.beautyProcedures.BeautyProcedureDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BeautyProcedureRepository extends MongoRepository<BeautyProcedureDTO, Long> {
}
