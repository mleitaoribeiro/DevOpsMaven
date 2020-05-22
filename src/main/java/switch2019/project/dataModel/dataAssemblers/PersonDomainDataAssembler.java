package switch2019.project.dataModel.dataAssemblers;

import switch2019.project.dataModel.entities.AddressJpa;
import switch2019.project.dataModel.entities.PersonJpa;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.utils.StringUtils;

import java.time.LocalDate;

public class PersonDomainDataAssembler {

    private PersonDomainDataAssembler() {};

    public static PersonJpa toData(Person person) {

        if (person.getMother() != null){
        return new PersonJpa(person.getID().toString(), person.getName(), person.getBirthDate(),
                person.getBirthPlace().getBirthPlace(), new AddressJpa(person.getAddress().getStreet(),
                person.getAddress().getCity(), person.getAddress().getPostalCode()),
                person.getMother().toString(), person.getFather().toString());}

        else return new PersonJpa(person.getID().toString(), person.getName(), person.getBirthDate(),
                person.getBirthPlace().getBirthPlace(), new AddressJpa(person.getAddress().getStreet(),
                person.getAddress().getCity(), person.getAddress().getPostalCode()));
    }

    public static PersonJpa toData(Person person, AddressJpa addressJpa) {

        if (person.getMother() != null){
            return new PersonJpa(person.getID().toString(), person.getName(), person.getBirthDate(),
                    person.getBirthPlace().getBirthPlace(), addressJpa,
                    person.getMother().toString(), person.getFather().toString());}

        else return new PersonJpa(person.getID().toString(), person.getName(), person.getBirthDate(),
                person.getBirthPlace().getBirthPlace(), addressJpa);
    }

    public static Person toDomain(PersonJpa personJpa) {

        DateAndTime birthDateDateAndTime = StringUtils.toDateAndTime(personJpa.getBirthDate());
        LocalDate birthDate = birthDateDateAndTime.getYearMonthDay();

        if (personJpa.getFatherId() != null) {
            return new Person(personJpa.getName(), new DateAndTime(birthDate.getYear(), birthDate.getMonthValue(),
                    birthDate.getDayOfMonth()), new Address(personJpa.getBirthPlace()),
                    new Address(personJpa.getAddress().getStreet(), personJpa.getAddress().getCity(),
                            personJpa.getAddress().getPostalCode()), new PersonID(new Email(personJpa.getMotherId())),
                    new PersonID(new Email(personJpa.getFatherId())), new Email(personJpa.getEmail()));

        } else return new Person(personJpa.getName(), new DateAndTime(birthDate.getYear(), birthDate.getMonthValue(),
                birthDate.getDayOfMonth()), new Address(personJpa.getBirthPlace()),
                new Address(personJpa.getAddress().getStreet(), personJpa.getAddress().getCity(),
                        personJpa.getAddress().getPostalCode()), new Email(personJpa.getEmail()));
    }
}
