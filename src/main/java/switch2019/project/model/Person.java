package switch2019.project.model;

import java.util.Calendar;
import java.util.HashSet;

public class Person {
    // Private instance variables
    private String name;
    private Calendar birthDate;
    private HashSet<Person> siblingList;

    /**
     * Default Person constructor
     * @param name
     * @param year
     * @param month
     * @param day
     */

    public Person(String name, int year, int month, int day) {
        this.name = name;
        birthDate = Calendar.getInstance();
        setBirthDate(year, month, day);
        siblingList = new HashSet<>();
    }

    public void setBirthDate(int year, int month, int day) {
        birthDate.set(year, month, day);
    }
}
