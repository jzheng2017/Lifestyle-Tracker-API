package webservice.dto;

public class TransactionRequest {
    private String name;
    private String comment;
    private int amount;
    private int transactionTypeId;
    private int userId;
    private int transactionOccurrenceId;
    private int categoryId;
    private String transactionDate;

    public TransactionRequest(){}

    public TransactionRequest(String name, String comment, int amount, int transactionTypeId, int userId, int transactionOccurenceId, int categoryId, String transactionDate) {
        this.name = name;
        this.comment = comment;
        this.amount = amount;
        this.transactionTypeId = transactionTypeId;
        this.userId = userId;
        this.transactionOccurrenceId = transactionOccurenceId;
        this.categoryId = categoryId;
        this.transactionDate = transactionDate;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(int transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTransactionOccurrenceId() {
        return transactionOccurrenceId;
    }

    public void setTransactionOccurrenceId(int transactionOccurrenceId) {
        this.transactionOccurrenceId = transactionOccurrenceId;
    }
}
