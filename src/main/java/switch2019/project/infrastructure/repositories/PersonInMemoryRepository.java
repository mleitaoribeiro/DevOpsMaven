package switch2019.project.infrastructure.repositories;

import org.springframework.stereotype.Component;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.domain.repositories.PersonRepository;
import switch2019.project.domain.repositories.Repository;

import java.util.HashSet;
import java.util.Set;

@Component
public class PersonInMemoryRepository implements PersonRepository {

    // Private instance variable
    private final Set<Person> listOfPersons;

    //1st Constructor
    public PersonInMemoryRepository() {
        listOfPersons = new HashSet<>();
    }

    //2nd Constructor
    //This is to be updated later but for now, the creator of the Person Objects is the PersonRepository
    public Person createPerson(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress, Email email) {
        listOfPersons.add(new Person(name, birthDate, birthPlace, homeAddress, email));
        return this.getByID(new PersonID(email));
    }

    //3rd constructor - Alternative constructor for people with mother and father
    public Person createPerson(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress,
                               PersonID mother, PersonID father, Email email) {
        listOfPersons.add(new Person(name, birthDate, birthPlace, homeAddress, mother, father, email));
        return this.getByID(new PersonID(email));
    }

    /**
     * Method to return the person corespondent to the given PersonID
     *
     * @param personID
     */
    public Person getByID(ID personID) {
        for (Person person : listOfPersons) {
            if (person.getID().equals(personID))
                return person;
        } throw new IllegalArgumentException("No person found with that ID.");
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


    /**
     * Method to check the number of Persons inside the Repository.
     *
     * @return
     */

    public int repositorySize () {
        return listOfPersons.size();
    }


}
