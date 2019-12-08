package webservice.dto;

public class Transaction {
    private int id;
    private String name;
    private String comment;
    private int amount;
    private TransactionType type;
    private int userId;
    private Occurrence occurrence;
    private String transactionDate;
    private Category category;
    private String createdAt;
    private String updatedAt;

    public Transaction() {
    }

    public Transaction(int id, String name, String comment, int amount, TransactionType type, int userId, Occurrence occurrence, String transactionDate, Category category, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.amount = amount;
        this.type = type;
        this.userId = userId;
        this.occurrence = occurrence;
        this.transactionDate = transactionDate;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Occurrence getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(Occurrence occurrence) {
        this.occurrence = occurrence;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
