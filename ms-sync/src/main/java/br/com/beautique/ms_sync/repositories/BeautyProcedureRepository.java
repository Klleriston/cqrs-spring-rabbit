package br.com.beautique.ms_sync.repositories;

import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeautyProcedureRepository extends MongoRepository<BeautyProcedureDTO, Long> {
}
