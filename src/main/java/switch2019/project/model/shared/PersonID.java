package switch2019.project.model.shared;

import switch2019.project.model.person.PersonName;
import java.util.Objects;

public class PersonID {

    private PersonName personName;

    /**
     * PersonID constructor
     */
    public PersonID(String personName) {
        this.personName = new PersonName(personName);
    }

    /**
     * Method to return Person Name
     * @return personName
     */
    public String getPersonName() {
        return personName.getPersonName();
    }

    /**
     * Override to equals method
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonID personID = (PersonID) o;
        return Objects.equals(personName, personID.personName);
    }

    /**
     * Override to hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(personName);
    }
}
