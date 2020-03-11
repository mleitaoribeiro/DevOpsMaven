package switch2019.project.model.person;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Email {

    private final String email;

    private static final String EMAIL_NOT_VALID = "The email it´s not valid";

    public Email(String email) {
        this.email = setValidEmail(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return email.equals(email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    /**
     * Method to set a valid email
     * @param email
     * @return
     */
    private String setValidEmail (String email) {
        if (isValid(email))
             return email;
        else {
            throw new IllegalArgumentException(EMAIL_NOT_VALID);
        }
    }

    /**
     * Auxiliary method to verify if email it´s valid
     * @param email
     * @return
     */
   private static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    /**
     * Get email
     * @return
     */
    public String getEmail() {
        return email;
    }
}
