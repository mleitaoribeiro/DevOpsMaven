package switch2019.project.domain.domainEntities.person;

import switch2019.project.domain.domainEntities.frameworks.Owner;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.PersonID;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Person implements Owner {

    // Private Person variables
    final private PersonID personID;
    final private PersonName name;
    final private DateAndTime birthDate; // year[¨], month [0-12], day[0-31] && Birth Date =< now()
    private final Set<Person> siblingList;
    private PersonID mother;
    private PersonID father;
    private Address address;
    final private Address birthPlace;

    public Person(String name, DateAndTime birthDate, Address birthPlace, Email email) {
        personID = new PersonID(email);
        this.name = new PersonName(name);
        this.birthPlace = birthPlace;
        this.birthDate = birthDate;
        siblingList = new HashSet<>();
    }

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
        siblingList = new HashSet<>(siblings);
        address = homeAddress;
    }

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
                  PersonID mother, PersonID father, Set<Person> siblings, Email email) {
        personID = new PersonID(email);
        this.name = new PersonName(name);
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        address = homeAddress;
        this.mother = mother;
        this.father = father;
        this.siblingList = new HashSet<>(siblings);
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

    public PersonID getID() {
        return personID;
    }

    public String getName() {
        return name.toString();
    }

    public Address getBirthPlace() {
        return this.birthPlace;
    }

    public String getBirthDate() {
        return birthDate.yearMonthDayToString();
    }

    public Address getAddress() {
        return address;
    }

    public Set<Person> getSiblingList() {
        return new HashSet<>(siblingList);
    }

    public Set<PersonID> getSiblingsIDList() {
        Set<PersonID> aux = new HashSet<>();
        for(Person p: siblingList)
            aux.add(p.getID());
        return aux;
    }

    public void setMother(PersonID mother) {
        this.mother = mother;
    }

    public void setFather(PersonID father) {
        this.father = father;
    }

    public boolean addSibling(Person newSibling) {
        return siblingList.add(newSibling) && newSibling.siblingList.add(this);
    }

    public void addMultipleSiblings(Set<Person> newSiblings) {
        for (Person person : newSiblings) {
            addSibling(person);
        }
    }

    public void removeSibling(Person sibling) {
        siblingList.remove(sibling);
        sibling.siblingList.remove(this);
    }

    public void removeMultipleSibling(Set<Person> toRemove) {
        for (Person person : toRemove) {
            removeSibling(person);
        }
    }

    public boolean isMother(PersonID mother) {
        if (this.mother == null || mother == null) return false;
        else return this.mother.equals(mother);
    }

    public boolean isFather(PersonID father) {
        if (this.father == null || father == null) return false;
        else return this.father.equals(father);
    }

    public boolean checkSameSiblings(Person otherPerson) {
        Set<Person> list1 = this.getSiblingList();
        Set<Person> list2 = otherPerson.getSiblingList();
        list1.remove(otherPerson);
        list2.remove(this);
        return list1.equals(list2);
    }

    public boolean checkSameMother(Person otherPerson) {
        if (otherPerson == null || this.mother == null || otherPerson.mother == null) {
            return false;
        } else return mother.equals(otherPerson.mother);
    }

    public boolean checkSameFather(Person otherPerson) {
        if (otherPerson == null || this.father == null || otherPerson.father == null) {
            return false;
        } else return father.equals(otherPerson.father);
    }

    public boolean personExistsOnSiblingsList(Person otherPerson) {
        return siblingList.contains(otherPerson);
    }

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


