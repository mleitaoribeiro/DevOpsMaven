package switch2019.project.dataModel.entities;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.shared.DateAndTime;

@Data
@ToString
@Entity(name ="persons")
public class PersonJpa {

    @Id
    @Column(name ="ID")
    private String email;
    private String name;
    private String birthDate;
    private String birthPlace;

    //@OneToOne(mappedBy = "persons", cascade = CascadeType.ALL)
    //private Address address;

    protected PersonJpa() {};

    public PersonJpa(String id, String name, String birthDate, String birthPlace) {
        this.email = id;
        this.name = name;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
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
}