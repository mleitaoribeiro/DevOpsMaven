package switch2019.project.domain.domainEntities.person;

import switch2019.project.utils.StringUtils;

import java.io.Serializable;
import java.util.Objects;

public class Email implements Serializable {

    private final String emailAddress;

    private static final String EMAIL_NOT_VALID = "The email is not valid.";
    private static final String EMAIL_NULL = "The email can't be null.";

    public Email(String email) {
        this.emailAddress = setValidEmail(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return emailAddress.equals(email.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress);
    }

    @Override
    public String toString() {
        return emailAddress;
    }

    private String setValidEmail(String email) {
        if (email == null)
            throw new IllegalArgumentException(EMAIL_NULL);
        else if (StringUtils.isEmail(email))
            return email.toLowerCase();
        else
            throw new IllegalArgumentException(EMAIL_NOT_VALID);

    }

    public String getEmailAddress() {
        return emailAddress;
    }

}