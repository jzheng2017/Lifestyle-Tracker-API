package webservice.dto;

public class OccurrenceDTO {
    private Integer id;
    private String name;

    public OccurrenceDTO() {
    }

    public OccurrenceDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
