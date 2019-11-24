package webservice.dao.interfaces;

import webservice.dto.Transaction;

import java.util.List;

public interface TransactionDao {

    Transaction getTransaction(int transactionId);

    boolean updateTransaction(Transaction user);

    boolean deleteTransaction(int userId);

    boolean insertTransaction(Transaction user);

    List<Transaction> getAll();

    List<Transaction> getAllIncome();

    List<Transaction> getAllExpenses();


}
