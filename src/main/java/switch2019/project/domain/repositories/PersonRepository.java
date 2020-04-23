package switch2019.project.domain.repositories;

import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;

public interface PersonRepository extends Repository{

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
