package switch2019.project.model;

import java.text.Normalizer;
import java.util.Objects;

public class Account {

    private String denomination;
    private String description;
    private double balance;

    public Account (String accountDenomination, String accountDescription) {
        setDenomination(accountDenomination);
        setDescription(accountDescription);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account oneAccount = (Account) o;
        return Objects.equals(this.denomination.toUpperCase(), oneAccount.denomination.toUpperCase())
                && Objects.equals(this.description.toUpperCase(), oneAccount.description.toUpperCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, description, balance);
    }

    /**
     * Public set for denomination: Can not be Null.
     * @param newDenomination
     */

    public void setDenomination (String newDenomination) {
        if (newDenomination == null) {
            throw new IllegalArgumentException("The denomination can´t be null. Please try again.");
        } else {
            newDenomination = removeWordAccents(newDenomination);
            newDenomination = removeSpecialCharacters(newDenomination);
            this.denomination = newDenomination.toUpperCase();
        }
    }

    //Auxiliary method to remove word accents
    private static String removeWordAccents(String sentence) {
        sentence = Normalizer.normalize(sentence, Normalizer.Form.NFD);
        sentence = sentence.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        return sentence;
    }

    //Auxiliary method to remove special Characters
    private static String removeSpecialCharacters(String sentence) {

        if (sentence == null || sentence.length () == 0)
            return sentence;

        String [] str = sentence.split ("[, &´#!%()`>?+.<@;-]+");
        StringBuilder buildNewStringArray = new StringBuilder();

        for (String element : str) {
            String word = element;
            buildNewStringArray.append(" ").append(word);
        }
        String newSentence = buildNewStringArray.toString().replaceFirst(" ", "");
        return newSentence;
    }

    /**
     * Public get for denomination
     */

    public String getDenomination (){
        return this.denomination;
    }

    /**
     * Public set for description: Can not be Null.
     * @param description
     */

    public void setDescription (String description) {
        if (description == null) {
            throw new IllegalArgumentException("The description can´t be null. Please try again.");
        } else {
            this.description = description;
        }
    }

    /**
     * Public get for description
     */

    public String getDescription (){
        return this.description;
    }

}
