package switch2019.project.DTO.serializationDTO;

import org.springframework.hateoas.RepresentationModel;
import switch2019.project.domain.domainEntities.shared.PersonID;

import java.util.Objects;

public class PersonIDDTO extends RepresentationModel<PersonIDDTO>{

    private String personID;

    public PersonIDDTO(PersonID personID) {
        this.personID = personID.getEmail();
    }


    public String getPersonID() {
        return personID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonIDDTO)) return false;
        PersonIDDTO that = (PersonIDDTO) o;
        return Objects.equals(personID, that.personID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), personID);
    }
}
