package webservice.repositories;

import org.springframework.data.repository.CrudRepository;
import webservice.entities.Transaction;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    List<Transaction> findAllByUserId(int userId);
}
