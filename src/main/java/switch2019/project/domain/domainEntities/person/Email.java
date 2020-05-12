package switch2019.project.domain.domainEntities.person;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

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

    /**
     * Method to set a valid email
     *
     * @param email
     * @return
     */

    private String setValidEmail(String email) {
        if (email == null)
            throw new IllegalArgumentException(EMAIL_NULL);
        else if (isValid(email))
            return email.toLowerCase();
        else
            throw new IllegalArgumentException(EMAIL_NOT_VALID);

    }

    /**
     * Get email
     *
     * @return
     */

    public String getEmailAddress() {
        return emailAddress;
    }


    /**
     * Auxiliary method to verify if email it´s valid (xxxx@xxxx.xx)
     *
     * @param email
     * @return
     */

    private boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }
}