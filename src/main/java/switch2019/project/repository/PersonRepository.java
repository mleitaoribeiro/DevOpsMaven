package switch2019.project.repository;

import switch2019.project.model.person.Address;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.DateAndTime;
import switch2019.project.model.shared.PersonID;

import java.util.HashSet;
import java.util.Set;

public class PersonRepository implements Repository{

    // Private instance variable
    private Set<Person> listOfPersons;

    /**
     * Default Constructor for Person Repository
     */
    public PersonRepository() {
        listOfPersons = new HashSet<>();
        createPerson("Francisco", new DateAndTime(2000, 1, 1),
                new Address("Porto"), new Address("Rua de S Tomés", "Porto", "4000-001"));
        createPerson("Joana", new DateAndTime(2001, 2, 3),
                new Address("Lisboa"), new Address("Rua de S Tomé", "Portimao", "4001-001"));
        createPerson("Maria", new DateAndTime(2003, 11, 30),
                new Address("Maia"), new Address("Rua de S Tomé", "Porto", "4000-100"));
            //FAMILY:
        createPerson("Homer", new DateAndTime(1990, 12, 4),
                new Address("Springfield"), new Address("Rua B", "Porto", "4520-233"));
        createPerson("Marge",new DateAndTime(1990, 12, 4),
                new Address("Springfield"), new Address("Rua B", "Porto", "4520-233"));
        createPersonWithParents("Bart", new DateAndTime(1990, 12, 4),
                new Address("Springfield"), new Address("Rua B", "Porto", "4520-233"),
                findPersonByAttributes("Marge"), findPersonByAttributes("Homer"));
        createPersonWithParents("Lisa", new DateAndTime(1990, 12, 4),
                new Address("Springfield"), new Address("Rua B", "Porto", "4520-233"),
                findPersonByAttributes("Marge"), findPersonByAttributes("Homer"));
    }

    /**
     * This is to be updated later but for now, the creator of the Person Objects is the PersonRepository
     *
     * @param name
     * @param birthDate
     * @param birthPlace
     * @param homeAddress
     */
    public boolean createPerson(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress) {
        return listOfPersons.add(new Person(name, birthDate, birthPlace, homeAddress));
    }

    /**
     * Alterneate constructor for people with mother and father
     *
     */
    public boolean createPersonWithParents(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress, Person mother, Person father) {
        return listOfPersons.add(new Person(name,birthDate,birthPlace,homeAddress,mother,father));
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
