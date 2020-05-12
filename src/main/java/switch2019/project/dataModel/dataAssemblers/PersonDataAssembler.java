package switch2019.project.dataModel.dataAssemblers;

import switch2019.project.dataModel.entities.PersonJpa;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonDataAssembler {

    public static PersonJpa toData(Person person ) {
        return new PersonJpa( person.getID().toString(), person.getName().toString(), person.getBirthDate(),
                person.getBirthPlace());
    }

    public static Person toDomain( PersonJpa personJpa ) {

        String birhtDateJpa = personJpa.getBirthDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        //convert String to LocalDate
        LocalDate birhthDate = LocalDate.parse(birhtDateJpa, formatter);

        return new Person(personJpa.getName(), new DateAndTime (birhthDate.getYear(), birhthDate.getMonthValue(),
                birhthDate.getDayOfMonth()), new Address(personJpa.getBirthPlace()), new Email(personJpa.getEmail()));
    }
}
