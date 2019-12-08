package webservice.services;

import webservice.dao.interfaces.TransactionDao;
import webservice.dto.Transaction;
import webservice.dto.TransactionRequest;
import webservice.exceptions.DatabaseOperationFailedException;

import javax.inject.Inject;
import java.util.List;

public class TransactionService {
    private TransactionDao transactionDao;

    @Inject
    public void setTransactionDao(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public List<Transaction> getAllTransactions() {
        return this.transactionDao.getAll();
    }

    public List<Transaction> getAllTransactionsByUserId(int userId) {
        return this.transactionDao.getAllByUserId(userId);
    }

    public List<Transaction> getAllUserExpenses(int userId) {
        return this.transactionDao.getAllUserExpenses(userId);
    }

    public List<Transaction> getAllUserIncome(int userId) {
        return this.transactionDao.getAllUserIncome(userId);
    }

    public List<Transaction> getAllIncome() {
        return this.transactionDao.getAllIncome();
    }

    public List<Transaction> getAllExpense() {
        return this.transactionDao.getAllExpenses();
    }

    public boolean insertTransaction(TransactionRequest transaction) {
        if (this.transactionDao.insertTransaction(transaction)) {
            return true;
        } else {
            throw new DatabaseOperationFailedException();
        }
    }


    public Transaction getTransaction(int transactionId) {
        return this.transactionDao.getTransaction(transactionId);
    }

    public Transaction updateTransaction(Transaction transaction) {
        if (this.transactionDao.updateTransaction(transaction)) {
            return getTransaction(transaction.getId());
        } else {
            throw new DatabaseOperationFailedException();
        }
    }

    public boolean deleteTransaction(int transactionId) {
        return this.transactionDao.deleteTransaction(transactionId);
    }
}
