package switch2019.project.model.shared;

import switch2019.project.model.person.Email;
import switch2019.project.model.frameworks.OwnerID;

import java.util.Objects;

public class PersonID implements OwnerID {

    // Private PersonID attributes
    private Email email;

    /**
     * PersonID constructor
     */
    public PersonID(Email email) {
        if(email != null) this.email = email;
        else throw new IllegalArgumentException("email can't be null.");
    }

    /**
     * Method to return Person Name
     * @return personName
     */
    public String getEmail() {
        return email.getEmailAddress();
    }

    /**
     * Override to equals method
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonID personID = (PersonID) o;
        return Objects.equals(email, personID.email);
    }

    /**
     * Override to hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
