package webservice.repositories;

import org.springframework.data.repository.CrudRepository;
import webservice.entities.TransactionType;

public interface TransactionTypeRepository extends CrudRepository<TransactionType, Integer> {
}
