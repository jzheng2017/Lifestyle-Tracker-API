package webservice.dto;

public class OccurrenceDTO {
    private int id;
    private String name;

    public OccurrenceDTO(){}
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
