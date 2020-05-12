package switch2019.project.dataModel.dataAssemblers;

import switch2019.project.dataModel.entities.AddressJpa;
import switch2019.project.dataModel.entities.PersonJpa;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonDataAssembler {

    public static PersonJpa toData(Person person ) {
        return new PersonJpa( person.getID().toString(), person.getName(), person.getBirthDate(),
                person.getBirthPlace().getBirthPlace(), new AddressJpa(person.getAddress().getStreet(), person.getAddress().getCity(),
                person.getAddress().getPostalCode()));
    }

    public static Person toDomain( PersonJpa personJpa ) {

        String birthDateJpa = personJpa.getBirthDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //convert String to LocalDate
        LocalDate birthPlace = LocalDate.parse(birthDateJpa, formatter);

        return new Person(personJpa.getName(), new DateAndTime (birthPlace.getYear(), birthPlace.getMonthValue(),
                birthPlace.getDayOfMonth()), new Address(personJpa.getBirthPlace()),
                new Address(personJpa.getAddress().getStreet(),personJpa.getAddress().getCity(), personJpa.getAddress().getPostalCode()),
                new Email(personJpa.getEmail()));
    }
}
