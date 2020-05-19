package switch2019.project.utils;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class StringUtils {

    private StringUtils(){}

    /**
     * Method used to check if an Account is owned by a Group or a Person
     *
     * @param word
     * @return boolean
     */
    public static boolean isEmail(String word) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(word).matches();
    }

    /**
     * Method used to remove all the extra white spaces in a given string
     *
     * @param word
     * @return trimmed word
     */
    public static String removeExtraSpaces(String word) {
        return word.trim().replaceAll(" +", " ");
    }

    /**
     * Removes extra Spaces, capitalizes only first letter and un-capitalizes exceptional cases
     * @param personName
     */

    public static String normalizePersonName(String personName) {
        personName = removeExtraSpaces(personName);
        personName = capitalizeEachWord(personName);
        return exceptionalCases(personName);
    }

    /**
     * Capitalize all the individual words of a String
     * @param personName
     */

    private static String capitalizeEachWord(String personName) {
        personName = personName.toLowerCase();
        personName = personName.substring(0, 1).toUpperCase() + personName.substring(1);
        String[] words = personName.split(" ");
        for(int i = 1 ; i < words.length ; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
        } return String.join(" ", words);
    }

    /**
     * Exceptional cases so it doesn't capitalize "de", "da", "do", "das" and "dos" and capitalizes a single letter before a break
     * @param personName
     */

    private static String exceptionalCases(String personName) {
        ArrayList<String> exceptions = new ArrayList<>(Arrays.asList("De", "Da", "Do", "Das", "Dos"));
        String[] words = personName.split(" ");
        for(int i = 0; i < words.length; i++) {
            if(exceptions.contains(words[i]))
                words[i] = words[i].toLowerCase();
        } return String.join(" ", words);
    }

    /**
     * Removes special characters, word accents and extra white spaces
     * @param denomination
     */

    public static String normalizeDenomination(String denomination) {
        denomination = removeSpecialCharacters(denomination);
        denomination = removeWordAccents(denomination);
        return removeExtraSpaces(denomination.toUpperCase());
    }

    /**
     * Auxiliary method to remove special characters
     *
     * @param sentence
     * @return sentence without special characters
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
     * @return sentence without accents
     */

    private static String removeWordAccents(String sentence) {
        sentence = Normalizer.normalize(sentence, Normalizer.Form.NFD);
        return sentence.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }
}