package switch2019.project.model.shared;

import java.text.Normalizer;
import java.util.Objects;

public class Denomination {

    //Private instance of denomination
    private String denominationValue;

    //Denomination constructor
    public Denomination(String denominationValue) {
        setValidDenomination(denominationValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Denomination)) return false;
        Denomination denominationValue = (Denomination) o;
        return this.denominationValue.equals(denominationValue.denominationValue);
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

    private void setValidDenomination(String newDenomination) {

        if (newDenomination == null || newDenomination.isEmpty()){
            throw new IllegalArgumentException("The denomination can´t be null or empty!");

        } else {
            newDenomination = removeWordAccents(removeSpecialCharacters(newDenomination).toUpperCase());
            this.denominationValue = newDenomination;
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


}
