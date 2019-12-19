package webservice.repositories;

import org.springframework.data.repository.CrudRepository;
import webservice.entities.TransactionOccurrenceType;

public interface TransactionOccurrenceTypeRepository extends CrudRepository<TransactionOccurrenceType, Integer> {
}
