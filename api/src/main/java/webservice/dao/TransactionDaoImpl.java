package webservice.dao;

import webservice.dao.interfaces.TransactionDao;
import webservice.datasource.core.Database;
import webservice.dto.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {
    private Database db = new Database("transaction");

    @Override
    public Transaction getTransaction(int transactionId) {
        return createTransaction(db.query("select.transaction.by.id", new String[]{Integer.toString(transactionId)}).execute());
    }

    @Override
    public boolean updateTransaction(Transaction transaction) {
        String[] parameters = {
                transaction.getName(),
                transaction.getComment(),
                Integer.toString(transaction.getAmount()),
                Integer.toString(transaction.getType().getId()),
                Integer.toString(transaction.getOccurrence().getId()),
                transaction.getTransactionDate(),
                Integer.toString(transaction.getCategory().getId())
        };
        return db.query("update.transaction", parameters).executeUpdate() > 0;
    }

    @Override
    public boolean deleteTransaction(int transactionId) {
        return db.query("delete.transaction.by.id", new String[]{Integer.toString(transactionId)}).executeUpdate() > 0;
    }

    @Override
    public boolean insertTransaction(TransactionRequest transaction) {
        String[] parameters = {
                transaction.getName(),
                transaction.getComment(),
                Integer.toString(transaction.getAmount()),
                Integer.toString(transaction.getTransactionTypeId()),
                Integer.toString(transaction.getTransactionOccurrenceId()),
                transaction.getTransactionDate(),
                Integer.toString(transaction.getCategoryId()),
                Integer.toString(transaction.getUserId())

        };
        return db.query("insert.transaction", parameters).executeUpdate() > 0;
    }

    @Override
    public List<Transaction> getAll() {
        return createTransactions(db.query("select.all.transactions", null).execute());
    }

    @Override
    public List<Transaction> getAllIncome() {
        return createTransactions(db.query("select.all.transactions.of.type", new String[]{"1"}).execute());

    }

    @Override
    public List<Transaction> getAllExpenses() {
        return createTransactions(db.query("select.all.transactions.of.type", new String[]{"2"}).execute());
    }

    @Override
    public List<Transaction> getAllByUserId(int userId) {
        return createTransactions(db.query("select.all.transactions.by.user.id", new String[]{Integer.toString(userId)}).execute());
    }

    @Override
    public List<Transaction> getAllUserIncome(int userId) {
        return createTransactions(db.query("select.all.transactions.from.user.of.type", new String[]{Integer.toString(userId), "1"}).execute());
    }

    @Override
    public List<Transaction> getAllUserExpenses(int userId) {
        return createTransactions(db.query("select.all.transactions.from.user.of.type", new String[]{Integer.toString(userId), "2"}).execute());
    }

    private List<Transaction> createTransactions(ResultSet result) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            while (result.next()) {
                transactions.add(createTransaction(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    private Transaction createTransaction(ResultSet result) {
        try {
            if (result.getRow() < 1 && result.next() && result.getRow() > 1) result.previous();
            if (result.getRow() > 0) {

                //Transaction
                int transactionId = result.getInt("transaction_id");
                String transactionName = result.getString("transaction_name");
                String comment = result.getString("comment");
                int amount = result.getInt("amount");
                String transactionDate = result.getString("transaction_date");
                String createdAt = result.getString("created_at");
                String updatedAt = result.getString("updated_at");
                int userId = result.getInt("user_id");

                //Transaction Type
                int transactionTypeId = result.getInt("transaction_type_id");
                String transactionTypeName = result.getString("type_name");

                //Transaction Occurence
                int transactionOccurrenceId = result.getInt("transaction_occurrence_id");
                String transactionOccurenceName = result.getString("occurrence_name");

                //Category
                int categoryId = result.getInt("category_id");
                String categoryName = result.getString("category_name");
                int parentId = result.getInt("parent_id");

                return new Transaction(transactionId, transactionName, comment, amount,
                        new TransactionType(transactionTypeId, transactionTypeName),
                        userId, new Occurrence(transactionOccurrenceId, transactionOccurenceName),
                        transactionDate,
                        new Category(categoryId, categoryName, parentId),
                        createdAt, updatedAt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
