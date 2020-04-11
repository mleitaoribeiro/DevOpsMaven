package switch2019.project.domain.domainEntities.shared;

import java.text.Normalizer;
import java.util.Objects;

public class Denomination {

    //Private instance of denomination
    private final String denominationValue;

    //Denomination constructor
    public Denomination(String denominationValue) {
        this.denominationValue = setValidDenomination(denominationValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Denomination otherDenominationValue = (Denomination) o;
        return this.denominationValue.equals(otherDenominationValue.denominationValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denominationValue);
    }

    @Override
    public String toString() {
        return denominationValue;
    }

    /**
     * Public set for denomination: Can't not be null or empty
     *
     * @param newDenomination
     */

    private String setValidDenomination(String newDenomination) {

        if (newDenomination == null || newDenomination.isEmpty()){
            throw new IllegalArgumentException("The denomination can't be null or empty.");
        } else return removeWordAccents(removeSpecialCharacters(newDenomination).toUpperCase());
    }

    /**
     * Auxiliary method to remove special Characters
     *
     * @param sentence
     */

    private static String removeSpecialCharacters(String sentence) {

        String[] str = sentence.split("[, &´#!%()`>?+.<@;-]+");
        StringBuilder buildNewStringArray = new StringBuilder();

        for (String element : str) {
            buildNewStringArray.append(" ").append(element);
        } return buildNewStringArray.toString().replaceFirst(" ", "");
    }

    /**
     * Auxiliary method to remove word accents
     *
     * @param sentence
     * @return sentence
     */

    private static String removeWordAccents(String sentence) {
        sentence = Normalizer.normalize(sentence, Normalizer.Form.NFD);
        return sentence.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }
}
