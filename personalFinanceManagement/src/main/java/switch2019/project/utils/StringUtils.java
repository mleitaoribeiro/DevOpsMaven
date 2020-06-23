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

    public static boolean isEmail(String word) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(word).matches();
    }

    public static String removeExtraSpaces(String word) {
        return word.trim().replaceAll(" +", " ");
    }

    public static String normalizePersonName(String personName) {
        personName = removeExtraSpaces(personName);
        personName = capitalizeEachWord(personName);
        return exceptionalCases(personName);
    }

    private static String capitalizeEachWord(String personName) {
        personName = personName.toLowerCase();
        personName = personName.substring(0, 1).toUpperCase() + personName.substring(1);
        String[] words = personName.split(" ");
        for (int i = 1; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
        }
        return String.join(" ", words);
    }

    private static String exceptionalCases(String personName) {
        ArrayList<String> exceptions = new ArrayList<>(Arrays.asList("De", "Da", "Do", "Das", "Dos"));
        String[] words = personName.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (exceptions.contains(words[i]))
                words[i] = words[i].toLowerCase();
        }
        return String.join(" ", words);
    }

    public static String normalizeDenomination(String denomination) {
        denomination = removeSpecialCharacters(denomination);
        denomination = removeWordAccents(denomination);
        return removeExtraSpaces(denomination.toUpperCase());
    }

    private static String removeSpecialCharacters(String sentence) {

        String[] str = sentence.split("[, &´#!%()`>?+.<@;-]+");
        StringBuilder buildNewStringArray = new StringBuilder();

        for (String element : str) {
            buildNewStringArray.append(" ").append(element);
        }
        return buildNewStringArray.toString().replaceFirst(" ", "");
    }

    private static String removeWordAccents(String sentence) {
        sentence = Normalizer.normalize(sentence, Normalizer.Form.NFD);
        return sentence.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

    public static DateAndTime toDateAndTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return new DateAndTime(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
    }

    public static DateAndTime toDateHourMinute(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDate = LocalDateTime.parse(date, formatter);
        return new DateAndTime(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), localDate.getHour(), localDate.getMinute());
    }

    public static boolean isSameDate(String date, String otherDate) {

        LocalDateTime firstDate = StringUtils.toDateHourMinute(date.replace("T", " ")).getYearMonthDayHourMinute();
        LocalDateTime secondDate = StringUtils.toDateHourMinute(otherDate.replace("T", " ")).getYearMonthDayHourMinute();

        return (firstDate.isEqual(secondDate) || firstDate.plusMinutes(1).isEqual(secondDate)
                || firstDate.minusMinutes(1).isEqual(secondDate));

    }

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

    public static boolean isCorrectDateRange(String initialDate, String finalDate) {

        if (isValidDate(initialDate) && isValidDate(finalDate)) {

            LocalDate firstLocalDate = toDateAndTime(initialDate).getYearMonthDay();
            LocalDate secondLocalDate = toDateAndTime(finalDate).getYearMonthDay();

            return !firstLocalDate.isAfter(LocalDate.now()) &&
                    !secondLocalDate.isAfter(LocalDate.now()) &&
                    !firstLocalDate.isAfter(secondLocalDate);
        }

        else if (isValidDateAndTime(initialDate) && isValidDateAndTime(finalDate)) {

            LocalDateTime firstLocalDateTime = toDateHourMinute(initialDate).getYearMonthDayHourMinute();
            LocalDateTime secondLocalDateTime = toDateHourMinute(finalDate).getYearMonthDayHourMinute();

            return !firstLocalDateTime.isAfter(LocalDateTime.now()) &&
                    !secondLocalDateTime.isAfter(LocalDateTime.now()) &&
                    !firstLocalDateTime.isAfter(secondLocalDateTime);
        }
        else return false;
    }
}
