package switch2019.project.infrastructure.repositories;

import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.domain.repositories.Repository;

import java.util.HashSet;
import java.util.Set;

public class PersonRepository implements Repository {

    // Private instance variable
    private Set<Person> listOfPersons;

    //1st Constructor
    public PersonRepository() {
        listOfPersons = new HashSet<>();
    }

    //2nd Constructor
    //This is to be updated later but for now, the creator of the Person Objects is the PersonRepository
    public Person createPerson(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress, Email email) {
        listOfPersons.add(new Person(name, birthDate, birthPlace, homeAddress, email));
        return this.findPersonByID(new PersonID(email));
    }

    //3rd constructor - Alternative constructor for people with mother and father
    public Person createPersonWithParents(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress,
                                           Person mother, Person father, Email email) {
        listOfPersons.add(new Person(name, birthDate, birthPlace, homeAddress, mother, father, email));
        return this.findPersonByID(new PersonID(email));
    }

    /**
     * Method to return the person corespondent to the given PersonID
     *
     * @param personID
     */
    public Person findPersonByID(ID personID) {
        for (Person person : listOfPersons) {
            if (person.getID().equals(personID))
                return person;
        }
        throw new IllegalArgumentException("No person found with that ID.");
    }

    /**
     * Method to return the person corespondent to the given attributes
     * This is to be updated later but for now, the only attribute being used is the name
     *
     * @param personEmail
     */
    public Person findPersonByEmail(Email personEmail) {
        for (Person person : listOfPersons) {
            if (person.getID().getEmail().equals(personEmail.getEmailAddress()))
                return person;
        }
        throw new IllegalArgumentException("No person found with that email.");
    }

    /**
     * Verify if e-mail is on person repository
     * @param personEmail
     * @return
     */

    public boolean isPersonEmailOnRepository(Email personEmail) {
        for (Person person : listOfPersons)
            if (person.getID().getEmail().equals(personEmail.getEmailAddress()))
                return true;
            return false;
        }

    /**
     * Verify if ID exists on person Repository
     * @param personID
     * @return
     */

    public boolean isIDOnRepository (ID personID) {
        for (Person person : listOfPersons)
            if (person.getID().equals(personID))
                return true;
        return false;
    }
}
