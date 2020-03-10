package switch2019.project.model.person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PersonName {

    /**
     * Private Instance Variable
     */

    private String personName;

    /**
     * Constructor of PersonName
     * @param personName
     */

    public PersonName(String personName) {
        this.personName = setPersonName(personName);
    }

    /**
     * Get Person Name
     * @return personName
     */

    public String getPersonName() {
        return personName;
    }

    /**
     * Capitalize all the first letters and remove all the extra spaces
     * @param personName
     */

    private String setPersonName(String personName) {
        personName = removeAllExtraSpaces(personName);
        personName = capitalizeEachWords(personName);
        return exceptionalCases(personName);
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

    private String capitalizeEachWords(String personName) {
        personName = personName.toLowerCase();
        personName = personName.substring(0, 1).toUpperCase() + personName.substring(1);
        String[] words = personName.split(" ");
        for(int i = 1 ; i < words.length ; i++){
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
        return Objects.equals(personName, that.personName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personName);
    }
}
