package switch2019.project.model.shared;

import java.text.Normalizer;
import java.util.Objects;

public class Denomination {

    //Private instance of denomination
    private String denomination;

    //Denomination constructor
    public Denomination(String denomination) {
        setValidDenomination(denomination);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Denomination)) return false;
        Denomination denomination = (Denomination) o;
        return this.denomination.equals(denomination.denomination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination);
    }

    @Override
    public String toString() {
        return denomination;
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
            this.denomination = newDenomination;
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
