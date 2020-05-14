package switch2019.project.infrastructure.dataBaseRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2019.project.dataModel.dataAssemblers.PersonDomainDataAssembler;
import switch2019.project.dataModel.entities.PersonJpa;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.domain.repositories.PersonRepository;
import switch2019.project.infrastructure.jpa.PersonJpaRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;

import java.util.Optional;

@Component("PersonDbRepository")
public class PersonDbRepository implements PersonRepository {

    @Autowired
    PersonJpaRepository personJpaRepository;

    //String literal - Exceptions
    private static final String PERSON_NOT_FOUND = "No person found with that email.";

    /**
     * Method do create Person without mother/father
     * @param name
     * @param birthDate
     * @param birthPlace
     * @param homeAddress
     * @param email
     * @return
     */
    public Person createPerson(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress, Email email) {
        Person person = new Person(name, birthDate, birthPlace, homeAddress, email);
        personJpaRepository.save(PersonDomainDataAssembler.toData(person));
        return person;
    }

    /**
     * Method do create Person with mother/father
     * @param name
     * @param birthDate
     * @param birthPlace
     * @param homeAddress
     * @param mother
     * @param father
     * @param email
     * @return
     */

    public Person createPerson(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress,
                               PersonID mother, PersonID father, Email email) {
        Person person = new Person(name, birthDate, birthPlace, homeAddress, mother, father, email);
        personJpaRepository.save(PersonDomainDataAssembler.toData(person));
        return person;
    }


    /**
     * Method to return the person corespondent to the given PersonID
     *
     * @param personID
     */
    public Person getByID(ID personID) {
        Optional<PersonJpa> personJpa = personJpaRepository.findById(personID.toString());
        if(personJpa.isPresent())
            return PersonDomainDataAssembler.toDomain(personJpa.get());
        else throw new ArgumentNotFoundException("PERSON_NOT_FOUND");
    }

    /**
     * Method to return the person corespondent to the given attributes
     * This is to be updated later but for now, the only attribute being used is the name
     *
     * @param personEmail
     */
    public Person findPersonByEmail(Email personEmail) {
        Optional<PersonJpa> personJpa = personJpaRepository.findByEmail(personEmail.toString());
        if(personJpa.isPresent())
            return PersonDomainDataAssembler.toDomain(personJpa.get());
        throw new ArgumentNotFoundException("No person found with that email.");
    }

    /**
     * Verify if e-mail is on person repository
     * @param personEmail
     * @return
     */
    public boolean isPersonEmailOnRepository(Email personEmail) {
        Optional<PersonJpa> personJpa = personJpaRepository.findByEmail(personEmail.toString());
        return personJpa.isPresent();
    }

    /**
     * Verify if ID exists on person Repository
     * @param personID
     * @return
     */
    @Override
    public boolean isIDOnRepository (ID personID) {
        Optional<PersonJpa> personJpa = personJpaRepository.findById(personID.toString());
        return personJpa.isPresent();
    }


    /**
     * Method to check the number of Persons inside the Repository.
     *
     * @return
     */
    public long repositorySize () {
        return personJpaRepository.count();
    }
}
