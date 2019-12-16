package webservice.dto;

public class TransactionTypeDTO {
    private Integer id;
    private String type;

    public TransactionTypeDTO() {
    }

    public TransactionTypeDTO(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
