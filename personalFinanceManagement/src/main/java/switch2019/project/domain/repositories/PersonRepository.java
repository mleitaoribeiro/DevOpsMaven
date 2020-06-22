package switch2019.project.domain.repositories;

import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.PersonID;

public interface PersonRepository extends Repository{

    /**
     * Person Constructor - simple
     *
     * @param name - name
     * @param birthDate - birthDate
     * @param birthPlace - birthPlace
     * @param homeAddress - homeAddress
     * @param email - email
     * @return - created Person
     */
    //2nd constructor
    Person createPerson(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress, Email email);

    /**
     * Person Constructor - detailed
     *
     * @param name - name
     * @param birthDate - birthDate
     * @param birthPlace - birthPlace
     * @param homeAddress - homeAddress
     * @param mother - mother
     * @param father - father
     * @param email - email
     * @return - created Person
     */
    //3th constructor
    Person createPerson(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress,
                        PersonID mother, PersonID father, Email email);

    /**
     * Find person by ID
     *
     * @param personID - personID
     * @return - Person
     */
    Person getByID (ID personID);

    /**
     * Method to return the person corespondent to the given attributes
     *
     * @param personEmail - personEmail
     * @return - Person
     */
    Person findPersonByEmail (Email personEmail);

    /**
     * Verify if e-mail is on person repository
     *
     * @param personEmail - personEmail
     * @return - Boolean
     */
    boolean isPersonEmailOnRepository (Email personEmail);

    /**
     * add a Sibling to a Person
     *
     * @param person - person
     * @param siblingID -siblingID (personID)
     * @return - Boolean
     */
    boolean addSibling(Person person, String siblingID);
}
