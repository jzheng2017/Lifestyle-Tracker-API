package webservice.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import webservice.entities.Transaction;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    List<Transaction> findAllByUserId(int userId);

    List<Transaction> findAllByTransactionType_Type(Sort by, String type);

    List<Transaction> findAllByOccurrence_Name(Sort by, String name);

    List<Transaction> findAllByCategory_Id(Sort by, int categoryId);

    List<Transaction> findAllByUserIdAndTransactionType_Type(int userId, String type);

    List<Transaction> findAllByUserIdAndOccurrence_Name(int userId, String occurence);

    List<Transaction> findAllByUserIdAndCategoryId(int userId, int categoryId);

    List<Transaction> findAll(Sort by);
}
