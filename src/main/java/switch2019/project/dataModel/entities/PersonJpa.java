package switch2019.project.dataModel.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(mappedBy = "id.ownerEmail", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SiblingsJpa> siblings;

    protected PersonJpa() {};

    public PersonJpa(String id, String name, String birthDate, String birthPlace, AddressJpa address) {
        this.email = id;
        this.name = name;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.address = address;
        siblings = new ArrayList<>();
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
        siblings = new ArrayList<>();
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

    public boolean addSibling(String personID) {
        SiblingsJpa siblingsJpa = new SiblingsJpa(this, personID);
        if (!siblings.contains(siblingsJpa))
            return siblings.add(siblingsJpa);
        else return false;
    }

    public List<SiblingsJpa> getSiblings() {
        return new ArrayList<>(siblings);
    }

}