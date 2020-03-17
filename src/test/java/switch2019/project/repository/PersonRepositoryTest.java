package switch2019.project.repository;

import org.junit.jupiter.api.Test;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.DateAndTime;
import switch2019.project.model.shared.PersonID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonRepositoryTest {

    @Test
    void createPerson() {
        //Arrange:
        PersonRepository personRepository = new PersonRepository();

        //Act:
        boolean result = personRepository.createPerson("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        //Assert:
        assertTrue(result);
    }

    @Test
    void findPersonByID() {
        //Arrange:
        PersonRepository personRepository = new PersonRepository();
        personRepository.createPerson("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));
        Person expected = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        //Act:
        Person actual = personRepository.findPersonByID(new PersonID(new Email("1234@isep.pt")));

        //Assert:
        assertEquals(expected, actual);
    }

    @Test
    void findPersonByAttributes() {
        //Arrange:
        PersonRepository personRepository = new PersonRepository();
        personRepository.createPerson("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));
        Person expected = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        //Act:
        Person actual = personRepository.findPersonByEmail(new Email("1234@isep.pt"));

        //Assert:
        assertEquals(expected, actual);
    }
}