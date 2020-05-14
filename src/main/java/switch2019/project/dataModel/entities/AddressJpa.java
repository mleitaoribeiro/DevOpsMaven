package switch2019.project.dataModel.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "addresses")
public class AddressJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String street;
    private String city;
    private String postalCode;

    //@OneToOne
    //@JoinColumn(name = "persons", nullable=false)
    //private PersonJpa person;

    protected AddressJpa() {};

    public AddressJpa(String street, String city, String postalCode) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        //this.person = personJpa;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }
}

