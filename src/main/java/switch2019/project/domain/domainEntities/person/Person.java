package switch2019.project.domain.domainEntities.person;

import switch2019.project.domain.domainEntities.frameworks.Owner;
import switch2019.project.domain.domainEntities.ledger.Periodicity;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.PersonID;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Person implements Owner {

    // Private Person variables
    private PersonID personID;
    private PersonName name;
    private DateAndTime birthDate; // year[¨], month [0-12], day[0-31] && Birth Date =< now()
    private Set<Person> siblingList;
    private PersonID mother;
    private PersonID father;
    private Address address;
    private Address birthPlace;

    /**
     * Default Person constructor
     *
     * @param name
     * @param birthDate
     * @param birthPlace
     */

    public Person(String name, DateAndTime birthDate, Address birthPlace, Email email) {
        personID = new PersonID(email);
        this.name = new PersonName(name);
        this.birthPlace = birthPlace;
        this.birthDate = birthDate;
        siblingList = new HashSet<>();
    }

    /**
     * Overload Person constructor
     *
     * @param name
     * @param birthDate
     * @param birthPlace
     * @param homeAddress
     */

    public Person(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress, Email email) {
        personID = new PersonID(email);
        this.name = new PersonName(name);
        this.birthPlace = birthPlace;
        this.birthDate = birthDate;
        siblingList = new HashSet<>();
        address = homeAddress;
    }

    public Person(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress,
                  HashSet<Person> siblings, Email email) {
        personID = new PersonID(email);
        this.name = new PersonName(name);
        this.birthPlace = birthPlace;
        this.birthDate = birthDate;
        siblingList = siblings;
        address = homeAddress;
    }

    /**
     * Overload Person constructor
     *
     * @param name
     * @param birthDate
     * @param birthPlace
     * @param homeAddress
     * @param mother
     * @param father
     */

    public Person(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress,
                  PersonID mother, PersonID father, Email email) {
        personID = new PersonID(email);
        this.name = new PersonName(name);
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        address = homeAddress;
        this.mother = mother;
        this.father = father;
        this.siblingList = new HashSet<>();
    }

    public Person(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress,
                  PersonID mother, PersonID father, HashSet<Person> siblings, Email email) {
        personID = new PersonID(email);
        this.name = new PersonName(name);
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        address = homeAddress;
        this.mother = mother;
        this.father = father;
        this.siblingList = siblings;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(personID, person.personID);

    }

    @Override
    public int hashCode() {
        return Objects.hash(personID);
    }

    @Override
    public String toString() {
        return "Email: " + personID.getEmail() + ", Person " + name.getPersonName() + ", currently lives in " + address.toString() +
                ", was born in " + birthPlace.getBirthPlace() + ", on " + birthDate.getYearMonthDay() + ".";
    }

    /**
     * Method to get Person ID
     *
     * @return personID
     */

    public PersonID getID() {
        return personID;
    }


    /**
     * Method to get PersonName
     * @return persons name
     */
    public String getName() {
        return name.toString();
    }

    /**
     * Return birthPlace as a String
     *
     * @return String birthPlace
     */

    public Address getBirthPlace() {
        return this.birthPlace;
    }

    /**
     * Return birtDate as a String
     *
     * @return String birthDate
     */
    public String getBirthDate() {
        return birthDate.yearMonthDayToString();
    }

    /**
     * Return Address VO
     * @return
     */
    public Address getAddress() {
        return address;
    }
    /**
     * Getter function for the sibling's list
     *
     * @return siblingList
     */
    public Set<Person> getSiblingList() {
        return new HashSet<>(siblingList);
    }

    /**
     * Getter function for the sibling's list ID
     *
     * @return siblingList
     */
    public Set<PersonID> getSiblingsIDList() {
        Set<PersonID> aux = new HashSet<>();
        for(Person p: siblingList)
            aux.add(p.getID());
        return aux;
    }

    /**
     * Set Mother
     *
     * @param mother new mother Person
     */
    public void setMother(PersonID mother) {
        this.mother = mother;
    }

    /**
     * Set Father
     *
     * @param father new father Person
     */

    public void setFather(PersonID father) {
        this.father = father;
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

    public void addMultipleSiblings(Set<Person> newSiblings) {
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
        siblingList.remove(sibling);
        sibling.siblingList.remove(this);
    }

    /**
     * Remove multiple Siblings
     *
     * @param toRemove HashSet of siblings that are going to be removed.
     */
    public void removeMultipleSibling(Set<Person> toRemove) {
        for (Person person : toRemove) {
            removeSibling(person);
        }
    }


    /**
     * Validate if a person is the Mother of another person
     *
     * @param mother Person to validate if it's the mother
     * @return boolean
     */
    public boolean isMother(PersonID mother) {
        if (this.mother == null) return false;
        else return this.mother.equals(mother);
    }

    /**
     * Validate if a person is the Father of another person
     *
     * @param father Person to validate if it's the father
     * @return boolean
     */

    public boolean isFather(PersonID father) {
        if (this.father == null || father == null) return false;
        else return this.father.equals(father);
    }

    /**
     * Method used to compare 2 sibling Lists to check if they are equal(True) or different(False)
     *
     * @param otherPerson
     */
    public boolean checkSameSiblings(Person otherPerson) {
        Set<Person> list1 = this.getSiblingList();
        Set<Person> list2 = otherPerson.getSiblingList();
        list1.remove(otherPerson);
        list2.remove(this);
        return list1.equals(list2);
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
        if (father == null || otherPerson.father == null) {
            return false;
        } else return father.equals(otherPerson.father);
    }


    /**
     * Person exists on the other Person siblings list (USER STORIES)
     *
     * @return boolean
     */
    public boolean personExistsOnSiblingsList(Person otherPerson) {
        return siblingList.contains(otherPerson);
    }


    /**
     * Develop method to check if two individuals are siblings (USER STORIES)
     *
     * @return boolean
     */

    public boolean isSibling(Person otherPerson) {
        return (personExistsOnSiblingsList(otherPerson) ||
                checkSameFather(otherPerson) || checkSameMother(otherPerson));
    }

    public PersonID getMother() {
        return mother;
    }

    public PersonID getFather() {
        return father;
    }
}


