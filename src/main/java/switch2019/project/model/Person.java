package switch2019.project.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;

public class Person {
    // Private Person variables
    private String name;
    private LocalDate birthDate; // year[¨], month [0-12], day[0-31] && Birth Date =< now()
    private HashSet<Person> siblingList;
    private Person mother;
    private Person father;
    private Address address;
    private Address birthPlace;

    /**
     * Default Person constructor
     *
     * @param name
     * @param birthDay
     * @param birthMonth
     * @param birthYear
     */

    public Person(String name, int birthYear, int birthMonth, int birthDay) {
        this.name = name;
        birthDate = birthDate.of(birthYear, birthMonth, birthDay);
        setBirthDate(birthYear, birthMonth, birthDay);
        siblingList = new HashSet<>();
    }

    /**
     * Overload Person constructor
     *
     * @param name
     * @param birthdayDay
     * @param birthdayMonth
     * @param birthdayYear
     * @param adress
     * @param mother
     * @param father
     */

    public Person(String name, int birthdayYear, int birthdayMonth, int birthdayDay, Address adress, Person mother, Person father) {
        this.name = name;
        birthDate = birthDate.of(birthdayYear, birthdayMonth, birthdayDay);
        setBirthDate(birthdayYear, birthdayMonth, birthdayDay);
        siblingList = new HashSet<>();
        this.address = adress;
        this.mother = mother;
        this.father = father;
    }

    /**
     * Set Person Birth Date: with input validation
     *
     * @param newYear
     * @param newMonth
     * @param newDay
     */

    public void setBirthDate(int newYear, int newMonth, int newDay) {
        if (birthDate.of(newYear, newMonth, newDay).isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth Date Not Supported.");
        }
        birthDate = birthDate.of(newYear, newMonth, newDay);
    }

    /**
     * Get BirthDate
     *
     * @return birthDate
     */

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    /**
     * Set Person Name: No input Validation
     *
     * @param newName
     */

    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Get Person Name
     *
     * @return Person's name
     */

    public String getName() {
        return this.name;
    }

    /**
     * Add a new Sibling to siblingList
     *
     * @param newSibling
     */

    public void addSibling(Person newSibling) {
        siblingList.add(newSibling);
        newSibling.siblingList.add(this);
    }

    /**
     * Add Multiple Siblings
     *
     * @param newSiblings
     */

    public void addMultipleSiblings(HashSet<Person> newSiblings) {
        for (Person person : newSiblings) {
            addSibling(person);
        }
    }

    /**
     * Remove a Sibling
     *
     * @param sibling
     */
    public void removeSibling(Person sibling) {
        this.siblingList.remove(sibling);
        sibling.siblingList.remove(this);
    }

    /**
     * Remove multiple Siblings
     *
     * @param toRemove HashSet of siblings that are going to be removed.
     */
    public void removeMultipleSibling(HashSet<Person> toRemove) {
        for (Person person : toRemove) {
            removeSibling(person);
        }
    }

    /**
     * Getter function for the sibling's list
     *
     * @return siblingList
     */
    public HashSet<Person> getSiblingList() {
        HashSet<Person> siblingsClone = new HashSet<>(this.siblingList);
        return siblingsClone;
    }

    /**
     * Set Mother
     *
     * @param mother new mother Person
     */
    public void setMother(Person mother) {
        this.mother = mother;
    }

    /**
     * Validate if a person is the Mother of another person
     *
     * @param mother Person to validate if it's the mother
     * @return boolean
     */
    public boolean isMother(Person mother) {
        if (this.mother == null) return false;
        else return this.mother.equals(mother);
    }

    /**
     * Set Father
     *
     * @param father new father Person
     */

    public void setFather(Person father) {
        this.father = father;
    }

    /**
     * Validate if a person is the Father of another person
     *
     * @param father Person to validate if it's the father
     * @return boolean
     */

    public boolean isFather(Person father) {
        if (this.father == null) return false;
        else return this.father.equals(father);
    }

    /**
     * Method used to compare 2 sibling Lists to check if they are equal(True) or different(False)
     *
     * @param otherPerson
     */
    public boolean checkSameSiblings(Person otherPerson) {
        HashSet<Person> list1 = this.getSiblingList();
        HashSet<Person> list2 = otherPerson.getSiblingList();
        list1.remove(otherPerson);
        list2.remove(this);
        if (list1.equals(list2)) {
            return true;
        } else return false;
    }

    /**
     * Develop a method to check if two people have the Same Mother
     *
     * @param otherPerson Person to validate if has the same mother
     * @return boolean
     */
    public boolean checkSameMother(Person otherPerson) {
        if (mother == null || otherPerson.mother == null) {
            return false;
        } else return mother.equals(otherPerson.mother);
    }


    /**
     * Develop a method to check if two people have the Same Father
     *
     * @param otherPerson to validate if has the same father
     */
    public boolean checkSameFather(Person otherPerson) {
        if (this.father == null || otherPerson.father == null) {
            return false;
        } else return father.equals(otherPerson.father);
    }

    /**
     * override of equals for Person Instance and @override hashcode
     *
     * @param o
     * @return boolean
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

    /**
     * Person exists on the other Siblings list
     *
     * @return boolean
     */
    public boolean personExistsOtherSiblingsList() {
        // Check if sibling list is empty:
        if (this.siblingList.isEmpty()) {
            return false;
        }
        // Cycle each Person inside the sibling list, to assert
        // if the current object is contained within each of sibling lists:
        for (Person A : this.siblingList) {
            if (!A.siblingList.contains(this)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Person exists on the other Person siblings list (USER STORIES)
     *
     * @return boolean
     */
    public boolean personExistsOnTheOtherPersonSiblingsList(Person otherPerson) {
        if (! this.siblingList.contains(otherPerson)){
            return false;
        }
        return true;
    }
}

