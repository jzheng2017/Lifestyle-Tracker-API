package webservice.entities;

import javax.persistence.*;

@Entity
@Table(name = "transaction_occurrence_types")
public class TransactionOccurrenceType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "occurrence_name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
}
