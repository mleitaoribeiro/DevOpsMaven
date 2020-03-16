package switch2019.project.repository;

import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.DateAndTime;
import switch2019.project.model.shared.PersonID;

import javax.lang.model.util.ElementScanner7;
import java.util.HashSet;
import java.util.Set;

public class PersonRepository implements Repository{

    // Private instance variable
    private Set<Person> listOfPersons;

    /**
     * Default Constructor for Person Repository
     */
    public PersonRepository() { listOfPersons = new HashSet<>(); }

    /**
     * This is to be updated later but for now, the creator of the Person Objects is the PersonRepository
     *
     * @param name
     * @param birthDate
     * @param birthPlace
     * @param homeAddress
     */
    public boolean createPerson(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress, Email email) {
        return listOfPersons.add(new Person(name, birthDate, birthPlace, homeAddress,email));
    }

    /**
     * Alterneate constructor for people with mother and father
     *
     */
    public boolean createPersonWithParents(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress,
                                           Person mother, Person father, Email email) {
        return listOfPersons.add(new Person(name,birthDate,birthPlace,homeAddress,mother,father,email));
    }

    /**
     * Method to return the person corespondent to the given PersonID
     * @param personID
     */
    public Person findPersonByID(PersonID personID) {
        for(Person person : listOfPersons) {
            if(person.getID().equals(personID))
                return person;
        } throw new IllegalArgumentException("No person found with that ID.");
    }

    /**
     * Method to return the person corespondent to the given attributes
     * This is to be updated later but for now, the only attribute being used is the name
     * @param personName
     */
    public Person findPersonByAttributes(String personName) {
        for(Person person : listOfPersons) {
            if(person.getPersonName().equals(personName))
                return person;
        } throw new IllegalArgumentException("No person found with that attributes.");
    }
}
