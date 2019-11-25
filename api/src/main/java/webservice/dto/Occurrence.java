package webservice.dto;

public class Occurrence {
    private int id;
    private String occurrence;

    public Occurrence(int id, String occurrence) {
        this.id = id;
        this.occurrence = occurrence;
    }

    public String getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(String occurrence) {
        this.occurrence = occurrence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
