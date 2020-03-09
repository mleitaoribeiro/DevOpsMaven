package switch2019.project.model.valueObject;

import java.text.Normalizer;

public class Denomination {

    //Private instance of denomination
    private String denomination;

    //Denomination constructor
    public Denomination(String denomination) {
        setDenomination(denomination);
    }

    /**
     * Override toString
     * @return
     */

    public String ToString() {
        return denomination;
    }

    /**
     * Public set for denomination: Can't not be null.
     *
     * @param newDenomination
     */

    private void setDenomination(String newDenomination) {

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


}
