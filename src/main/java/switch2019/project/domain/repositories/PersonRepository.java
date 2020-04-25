package switch2019.project.domain.repositories;

import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.PersonID;

public interface PersonRepository extends Repository{
    /**
     *
     * @param name
     * @param birthDate
     * @param birthPlace
     * @param homeAddress
     * @param email
     */

    //2nd constructor
    Person createPerson(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress, Email email);

    //3th constructor
    Person createPerson(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress,
                        PersonID mother, PersonID father, Email email);
    /**
     * Find person by ID
     */

    Person getByID (ID personID);

    /**
     * Method to return the person corespondent to the given attributes
     * @param personEmail
     */

    Person findPersonByEmail (Email personEmail);

    /**
     * Verify if e-mail is on person repository
     * @param personEmail
     */
    boolean isPersonEmailOnRepository (Email personEmail);

}
