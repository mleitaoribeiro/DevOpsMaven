package switch2019.project.dataModel.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity(name = "addresses")
public class AddressJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    private String city;
    private String postalCode;

    @ManyToOne()
    @JoinColumn(name = "persons", nullable=false)
    //because it is in the same aggregate, a foreign key constraint is used
    private PersonJpa person;

    protected AddressJpa() {};

    public AddressJpa(String street, String city, String postalCode, PersonJpa personJpa) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.person = personJpa;
    }
}
