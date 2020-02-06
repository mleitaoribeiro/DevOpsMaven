package switch2019.project.model;

import java.text.Normalizer;
import java.util.Objects;

public class Account {
    /**
     * Private Instance Variables
     */

    private String denomination;
    private String description;
    private double balance;

    /**
     * Constructor of Account
     *
     * @param accountDenomination
     * @param accountDescription
     */

    public Account(String accountDenomination, String accountDescription) {
        setDenomination(accountDenomination);
        setDescription(accountDescription);
        balance = 0;
    }

    /**
     * override equals method of Acccount object
     *
     * @param o
     * @return
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account oneAccount = (Account) o;
        return Objects.equals(this.denomination, oneAccount.denomination);
    }

    /**
     * override hascode of Account object
     *
     * @return hashcode
     */

    @Override
    public int hashCode() {
        return Objects.hash(denomination, description, balance);
    }

    /**
     * Develop @override of toString()
     */

    @Override
    public String toString() {
        return denomination + ", " + description + ", " + balance + "€";
    }

    /**
     * Public get for denomination
     *
     * @return denomination
     */

    public String denominationToString() {
        return denomination;
    }

    /**
     * Public set for denomination: Can not be Null.
     *
     * @param newDenomination
     */

    public void setDenomination(String newDenomination) {

        if (newDenomination == null || newDenomination.length() == 0) {
            throw new IllegalArgumentException("The denomination can´t be null or empty!");

        } else {
            newDenomination = removeWordAccents(newDenomination);
            newDenomination = removeSpecialCharacters(newDenomination);
            this.denomination = newDenomination.toUpperCase();
        }
    }

    /**
     * Auxiliary method to remove special Characters
     *
     * @param sentence
     * @return
     */

    private static String removeSpecialCharacters(String sentence) {

        String[] str = sentence.split("[, &´#!%()`>?+.<@;-]+");
        StringBuilder buildNewStringArray = new StringBuilder();

        for (String element : str) {
            buildNewStringArray.append(" ").append(element);
        }

        return buildNewStringArray.toString().replaceFirst(" ", "");
    }

    /**
     * Auxiliary method to remove word accents
     *
     * @param sentence
     * @return sentence
     */

    private static String removeWordAccents(String sentence) {

        sentence = Normalizer.normalize(sentence, Normalizer.Form.NFD);
        sentence = sentence.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        return sentence;
    }

    /**
     * Public get for description
     *
     * @return descritpion
     */

    public String getDescription() {
        return this.description;
    }

    /**
     * Public set for description: Can not be Null.
     *
     * @param description
     */

    public void setDescription(String description) {
        if (description == null || description.length() == 0) {
            throw new IllegalArgumentException("The description can´t be null or empty!");
        } else {
            this.description = description;
        }
    }

    /**
     * Public get to access a clone of Account
     *
     * @return copy of Account
     */

    public Account getCopyOfAccount() {
        return new Account(denomination, description);
    }
}
