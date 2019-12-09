package webservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class TransactionDTO {
    private int id;
    private String transactionName;
    private String comment;
    private float amount;
    private TransactionTypeDTO type;
    private OccurrenceDTO occurrence;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime transactionDate;
    private CategoryDTO category;
    private UserDTO user;

    public TransactionDTO(){

    }

    public TransactionDTO(int id, String transactionName, String comment, float amount, TransactionTypeDTO type, OccurrenceDTO occurrenceDTO, LocalDateTime transactionDate, CategoryDTO categoryDTO, UserDTO user) {
        this.id = id;
        this.transactionName = transactionName;
        this.comment = comment;
        this.amount = amount;
        this.type = type;
        this.occurrence = occurrenceDTO;
        this.transactionDate = transactionDate;
        this.category = categoryDTO;
        this.user = user;
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

    public OccurrenceDTO getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(OccurrenceDTO occurrenceDTO) {
        this.occurrence = occurrenceDTO;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO categoryDTO) {
        this.category = categoryDTO;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public TransactionTypeDTO getType() {
        return type;
    }

    public void setType(TransactionTypeDTO type) {
        this.type = type;
    }
}
