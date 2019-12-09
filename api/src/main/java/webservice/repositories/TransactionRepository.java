package webservice.repositories;

import org.springframework.data.repository.CrudRepository;
import webservice.entities.Transaction;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    List<Transaction> findAllByUserId(int userId);

    List<Transaction> findAllByTransactionType_Type(String type);

    List<Transaction> findAllByUserIdAndTransactionType_Type(int userId, String type);

    List<Transaction> findAllByUserIdAndCategoryId(int userId, int categoryId);
}
