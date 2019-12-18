package switch2019.project.model;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;

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

    /**
     * Set Person Name
     * @param newName
     */

    public void setName (String newName) {
        this.name = newName;
    }

    /**
     * Get Person Name
     * @return
     */

    public String getName () {
        return this.name;
    }

    /**
     * Add a new Sibling to siblingList
     * @param newSibling
     */

    public void addSibling(Person newSibling) {
        siblingList.add(newSibling);
        newSibling.siblingList.add(this);
    }

    /**
     * Add Multiple Siblings
     * @param newSiblings
     */

    public void addMultipleSiblings(HashSet<Person>newSiblings) {
        for(Person person : newSiblings) {
            addSibling(person);
        }
    }

    /**
     *
     * @return siblingList
     */

    public HashSet<Person> getSiblingList() {
        return siblingList;
    }
    /**
     * override of equals for Person Instance and @overrode hashcode
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(birthDate, person.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate);
    }
}
