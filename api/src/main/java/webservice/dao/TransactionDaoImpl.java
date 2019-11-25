package webservice.dao;

import webservice.dao.interfaces.TransactionDao;
import webservice.datasource.core.Database;
import webservice.dto.Category;
import webservice.dto.Occurrence;
import webservice.dto.Transaction;
import webservice.dto.TransactionType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {
    private Database db = new Database("transaction");

    @Override
    public Transaction getTransaction(int transactionId) {
        return null;
    }

    @Override
    public boolean updateTransaction(Transaction transaction) {
        return false;
    }

    @Override
    public boolean deleteTransaction(int transactionId) {
        return false;
    }

    @Override
    public boolean insertTransaction(Transaction transaction) {
        return false;
    }

    @Override
    public List<Transaction> getAll() {
        return null;
    }

    @Override
    public List<Transaction> getAllIncome() {
        return null;
    }

    @Override
    public List<Transaction> getAllExpenses() {
        return null;
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
