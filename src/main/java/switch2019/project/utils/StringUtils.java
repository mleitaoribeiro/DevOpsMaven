package switch2019.project.utils;

import switch2019.project.domain.domainEntities.shared.DateAndTime;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class StringUtils {

    private StringUtils() {
    }

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
     *
     * @param personName
     */

    public static String normalizePersonName(String personName) {
        personName = removeExtraSpaces(personName);
        personName = capitalizeEachWord(personName);
        return exceptionalCases(personName);
    }

    /**
     * Capitalize all the individual words of a String
     *
     * @param personName
     */

    private static String capitalizeEachWord(String personName) {
        personName = personName.toLowerCase();
        personName = personName.substring(0, 1).toUpperCase() + personName.substring(1);
        String[] words = personName.split(" ");
        for (int i = 1; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
        }
        return String.join(" ", words);
    }

    /**
     * Exceptional cases so it doesn't capitalize "de", "da", "do", "das" and "dos" and capitalizes a single letter before a break
     *
     * @param personName
     */

    private static String exceptionalCases(String personName) {
        ArrayList<String> exceptions = new ArrayList<>(Arrays.asList("De", "Da", "Do", "Das", "Dos"));
        String[] words = personName.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (exceptions.contains(words[i]))
                words[i] = words[i].toLowerCase();
        }
        return String.join(" ", words);
    }

    /**
     * Removes special characters, word accents and extra white spaces
     *
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
        }
        return buildNewStringArray.toString().replaceFirst(" ", "");
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

    /**
     * Format a String into a LocalDate
     *
     * @param date
     * @return formatted LocalDate
     */
    public static DateAndTime toDateAndTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return new DateAndTime(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
    }

    /**
     * Format a String into a LocalDateTime
     *
     * @param date
     * @return formatted LocalDateTime
     */
    public static DateAndTime toDateHourMinute(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDate = LocalDateTime.parse(date, formatter);
        return new DateAndTime(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), localDate.getHour(), localDate.getMinute());
    }

    /**
     * method to compare dates as Strings
     *
     * @param date, otherDate
     * @return boolean
     */

    public static boolean isSameDate(String date, String otherDate) {

        LocalDateTime firstDate = StringUtils.toDateHourMinute(date.replace("T", " ")).getYearMonthDayHourMinute();
        LocalDateTime secondDate = StringUtils.toDateHourMinute(otherDate.replace("T", " ")).getYearMonthDayHourMinute();

        return (firstDate.isEqual(secondDate) || firstDate.plusMinutes(1).isEqual(secondDate)
                || firstDate.minusMinutes(1).isEqual(secondDate));

    }

    /**
     *
     * Method to Check If a String Is a Valid Date (yyyy-MM-dd)
     *
     *
     * @param date
     * @return boolean
     */

    public static boolean isValidDate(String date) {

        if (date == null || date.isEmpty())
            return false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     *
     * Method to Check If a String Is a Valid Date And Time (yyyy-MM-dd HH:mm)
     *
     * @param date
     * @return boolean
     */

    public static boolean isValidDateAndTime(String date) {

        if (date == null || date.isEmpty())
            return false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     *
     * Method to check if Date Range is correct (Initial Date <= Final Date && <= Actual Date)
     *
     * @param initialDate
     * @param finalDate
     * @return boolean
     */

    public static boolean isCorrectDateRange(String initialDate, String finalDate) {

        if (!isValidDateAndTime(initialDate) || !isValidDateAndTime(finalDate))
            return false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime firstLocalDate = LocalDateTime.parse(initialDate, formatter);
        LocalDateTime secondLocalDate = LocalDateTime.parse(finalDate, formatter);

        return !firstLocalDate.isAfter(LocalDateTime.now()) &&
                !secondLocalDate.isAfter(LocalDateTime.now()) &&
                !firstLocalDate.isAfter(secondLocalDate);
    }

}
