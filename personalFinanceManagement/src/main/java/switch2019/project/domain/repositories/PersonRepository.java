package switch2019.project.domain.repositories;

import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.PersonID;

public interface PersonRepository extends Repository{

    //2nd constructor
    Person createPerson(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress, Email email);

    //3th constructor
    Person createPerson(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress,
                        PersonID mother, PersonID father, Email email);

    Person getByID (ID personID);

    Person findPersonByEmail (Email personEmail);

    boolean isPersonEmailOnRepository (Email personEmail);

    boolean addSibling(Person person, String siblingID);
}
