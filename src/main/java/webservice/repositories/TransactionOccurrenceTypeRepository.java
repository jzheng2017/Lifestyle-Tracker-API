package webservice.repositories;

import org.springframework.data.repository.CrudRepository;
import webservice.entities.OccurrenceType;

public interface TransactionOccurrenceTypeRepository extends CrudRepository<OccurrenceType, Integer> {
}
