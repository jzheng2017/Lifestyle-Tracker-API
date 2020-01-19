package webservice.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import webservice.entities.Transaction;

import java.util.List;

public interface TransactionRepository extends BaseRepository<Transaction, Integer> {
}
