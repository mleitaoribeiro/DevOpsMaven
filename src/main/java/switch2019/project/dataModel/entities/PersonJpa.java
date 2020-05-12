package switch2019.project.dataModel.entities;
import javax.persistence.*;
import lombok.Data;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.PersonID;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name ="persons")
public class PersonJpa {

    @EmbeddedId
    private PersonID id;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<AddressJpa> addresses;

    protected PersonJpa() {};

    public PersonJpa(String id, String firstName, String lastName) {
        this.id = new PersonID(new Email(id));
        this.firstName = firstName;
        this.lastName = lastName;
        addresses = new ArrayList<AddressJpa>();
    }

    public String toString() {
        return "Person {" +
                "id='" + id.toString() + '\'' +
                '}';
    }

}
