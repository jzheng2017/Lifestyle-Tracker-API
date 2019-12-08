package webservice.dao.interfaces;

import webservice.dto.Transaction;
import webservice.dto.TransactionRequest;

import java.util.List;

public interface TransactionDao {

    Transaction getTransaction(int transactionId);

    boolean updateTransaction(Transaction transaction);

    boolean deleteTransaction(int transactionId);

    boolean insertTransaction(TransactionRequest transaction);

    List<Transaction> getAll();

    List<Transaction> getAllIncome();

    List<Transaction> getAllExpenses();

    List<Transaction> getAllByUserId(int userId);

    List<Transaction> getAllUserExpenses(int userId);

    List<Transaction> getAllUserIncome(int userId);



}
