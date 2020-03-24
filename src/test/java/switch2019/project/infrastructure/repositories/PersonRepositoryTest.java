package switch2019.project.infrastructure.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.PersonID;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryTest {

    @Test
    void createPerson() {
        //Arrange:
        PersonRepository personRepository = new PersonRepository();
        Person expected = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        //Act:
        Person marta = personRepository.createPerson("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        //Assert:
        assertEquals(expected, marta);
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

    @Test
    @DisplayName("Verify If Person ID exists On Person Repository - Main Scenario")
    void verifyIfPersonIDExistsOnPersonRepository() {
        //Arrange
        PersonRepository personRepository = new PersonRepository();

        Person personJose = new Person("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));

        //Act
        personRepository.createPerson("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));

        boolean personIDExists = personRepository.isPersonIDOnRepository(personJose.getID());

        //Assert
        assertTrue(personIDExists);
    }

    @Test
    @DisplayName("Verify If Person ID exists On Person Repository -False Case")
    void verifyIfPersonIDExistsOnPersonRepositoryFalse() {
        //Arrange
        PersonRepository personRepository = new PersonRepository();

        //Act
        Person personJose = new Person("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));
        personRepository.createPerson("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));

        //Assert
        assertFalse(personRepository.isPersonIDOnRepository(new PersonID(new Email("fake@sofake.com"))));
    }

    @Test
    @DisplayName("Verify If Person ID exists On Person Repository - Main Scenario")
    void verifyIfPersonEmailExistsOnPersonRepository() {
        //Arrange
        PersonRepository personRepository = new PersonRepository();

        Person personJose = new Person("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));

        //Act
        personRepository.createPerson("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));

        boolean personEmailExists = personRepository.isPersonEmailOnRepository(new Email(personJose.getID().getEmail()));

        //Assert
        assertTrue(personEmailExists);
    }

    @Test
    @DisplayName("Verify If Person Email exists On Person Repository - False Case")
    void verifyIfPersonEmailExistsOnPersonRepositoryFalse() {
        //Arrange
        PersonRepository personRepository = new PersonRepository();

        //Act
        Person personJose = new Person("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));
        personRepository.createPerson("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));

        //Assert
        assertFalse(personRepository.isPersonEmailOnRepository(new Email("fake@sofake.com")));
    }
}