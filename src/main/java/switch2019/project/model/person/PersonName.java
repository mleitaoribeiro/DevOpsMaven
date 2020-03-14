package switch2019.project.model.person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PersonName {

    /**
     * Private Instance Variable
     */

    private final String name;

    /**
     * Constructor of PersonName
     * @param name
     */

    public PersonName(String name) {
        this.name = standardPersonName(name);
    }

    /**
     * Get Full Person Name
     * @return personName
     */

    public String getPersonName() {
        return name;
    }

    /**
     * Get First and Last Name
     * @return personName
     */

    public String getFirstAndLastName() {
        String[] words = name.split(" ");
        return words[0] + " " + words[words.length - 1];
    }

    /**
     * Capitalize all the first letters and remove all the extra spaces
     * @param personName
     */

    private String standardPersonName(String personName) {
        if(personName == null || personName.isEmpty())
            throw new IllegalArgumentException("The name can't be empty or null.");
        else {
            personName = removeAllExtraSpaces(personName);
            personName = capitalizeEachWord(personName);
            return exceptionalCases(personName);
        }
    }

    /**
     * Remove all extra spaces of a string
     * @param personName
     */

    private String removeAllExtraSpaces(String personName) {
        return personName.trim().replaceAll(" +", " ");
    }

    /**
     * Capitalize all the individual words of a String
     * @param personName
     */

    private String capitalizeEachWord(String personName) {
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

    private String exceptionalCases(String personName) {
        ArrayList<String> exceptions = new ArrayList<>(Arrays.asList("De", "Da", "Do", "Das", "Dos"));
        String[] words = personName.split(" ");
        for(int i = 0; i < words.length; i++) {
            if(exceptions.contains(words[i]))
                words[i] = words[i].toLowerCase();
        } return String.join(" ", words);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonName that = (PersonName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
