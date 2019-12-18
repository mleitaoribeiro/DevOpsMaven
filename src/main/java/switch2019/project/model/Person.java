package switch2019.project.model;

import java.util.Calendar;
import java.util.HashSet;

public class Person {
    // Private instance variables
    private String name;
    private Calendar birthDate;
    private HashSet<Person> siblingList;

    public Person(String name, Calendar birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        siblingList = new HashSet<>();
    }
}
