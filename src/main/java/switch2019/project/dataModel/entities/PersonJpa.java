package switch2019.project.dataModel.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity(name ="persons")
public class PersonJpa {

    @Id
    @Column(name ="person_id")
    private String email;
    private String name;
    private String birthDate;
    private String birthPlace;
    private String motherId;
    private String fatherId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressJpa address;

    protected PersonJpa() {};

    public PersonJpa(String id, String name, String birthDate, String birthPlace, AddressJpa address) {
        this.email = id;
        this.name = name;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.address = address;
    }

    public PersonJpa(String id, String name, String birthDate, String birthPlace, AddressJpa address,
                     String motherId, String fatherId){
        this.email = id;
        this.name = name;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.address = address;
        this.fatherId = fatherId;
        this.motherId = motherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonJpa personJpa = (PersonJpa) o;
        return Objects.equals(email, personJpa.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public AddressJpa getAddress() {
        return address;
    }

    public String getMotherId() {
        return motherId;
    }

    public String getFatherId() {
        return fatherId;
    }

}