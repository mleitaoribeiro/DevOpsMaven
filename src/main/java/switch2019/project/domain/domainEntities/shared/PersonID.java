package switch2019.project.domain.domainEntities.shared;

import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;

import java.util.Objects;

public class PersonID implements OwnerID {

    // Private PersonID attributes
    private final Email email;

    public PersonID(Email email) {
        if(email != null) this.email = email;
        else throw new IllegalArgumentException("email can't be null.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonID personID = (PersonID) o;
        return Objects.equals(email, personID.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return email.toString();
    }

    /**
     * Method to return Person Name
     * @return personName
     */
    public String getEmail() {
        return email.getEmailAddress();
    }
}
