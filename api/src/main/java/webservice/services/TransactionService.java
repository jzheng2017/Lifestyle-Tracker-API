package webservice.services;

import webservice.dao.interfaces.TransactionDao;
import webservice.dto.Transaction;

import javax.inject.Inject;
import java.util.List;

public class TransactionService {
    private TransactionDao transactionDao;

    @Inject
    public void setTransactionDao(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
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
}
