package webservice.dto;

public class Transaction {
    private int id;
    private String name;
    private String comment;
    private int amount;
    private String type;
    private String occurence;
    private String transactionDate;
    private String category;
    private String createdAt;
    private String updatedAt;

    public Transaction(int id, String name, String comment, int amount, String type, String occurence, String transactionDate, String category, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.amount = amount;
        this.type = type;
        this.occurence = occurence;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOccurence() {
        return occurence;
    }

    public void setOccurence(String occurence) {
        this.occurence = occurence;
    }
}
