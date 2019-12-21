package webservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class TransactionRequestDTO {
    private int id;
    private String name;
    private String comment;
    private float amount;
    private int transactionTypeId;
    private int occurrenceTypeId;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime transactionDate;
    private int categoryId;
    private int userId;
    
    public TransactionRequestDTO(){

    }

    public TransactionRequestDTO(int id, String name, String comment, float amount, int transactionTypeId, int occurrenceTypeId, LocalDateTime transactionDate, int categoryId, int userId) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.amount = amount;
        this.transactionTypeId = transactionTypeId;
        this.occurrenceTypeId = occurrenceTypeId;
        this.transactionDate = transactionDate;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getOccurrenceTypeId() {
        return occurrenceTypeId;
    }

    public void setOccurrenceTypeId(int occurrenceTypeId) {
        this.occurrenceTypeId = occurrenceTypeId;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
