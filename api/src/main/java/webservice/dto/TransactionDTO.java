package webservice.dto;

import java.time.LocalDateTime;

public class TransactionDTO {
    private int id;
    private String transactionName;
    private String comment;
    private float amount;
    private int transactionTypeId;
    private int occurrenceId;
    private LocalDateTime transactionDate;
    private int categoryId;
    private int userId;

    public TransactionDTO(){

    }

    public TransactionDTO(int id, String transactionName, String comment, float amount, int transactionTypeId, int occurenceId, LocalDateTime transactionDate, int categoryId, int userId) {
        this.id = id;
        this.transactionName = transactionName;
        this.comment = comment;
        this.amount = amount;
        this.transactionTypeId = transactionTypeId;
        this.occurrenceId = occurenceId;
        this.transactionDate = transactionDate;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(int transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public int getOccurrenceId() {
        return occurrenceId;
    }

    public void setOccurrenceId(int occurrenceId) {
        this.occurrenceId = occurrenceId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
