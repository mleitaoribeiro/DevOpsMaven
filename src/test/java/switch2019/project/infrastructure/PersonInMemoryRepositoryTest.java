package switch2019.project.infrastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.domain.repositories.PersonRepository;

import static org.junit.jupiter.api.Assertions.*;

class PersonInMemoryRepositoryTest {

    @Test
    @DisplayName("Create new Person - Main scenario")
    void createPerson() {

        //Arrange:
        PersonRepository personRepository = new PersonInMemoryRepository();
        Person expected = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        //Act:
        Person marta = personRepository.createPerson("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        //Assert:
        assertEquals(expected, marta);
    }

    @Test
    @DisplayName("Find Person By ID - Main scenario")
    void findPersonByID() {

        //Arrange:
        PersonRepository personRepository = new PersonInMemoryRepository();
        personRepository.createPerson("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));
        Person expected = new Person("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        //Act:
        Person actual = personRepository.getByID(new PersonID(new Email("1234@isep.pt")));

        //Assert:
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Find Person By ID - False - Exception")
    void findPersonByIDException() {

        //Arrange:
        PersonRepository personRepository = new PersonInMemoryRepository();
        personRepository.createPerson("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        //Act:
        try {
            personRepository.getByID(new PersonID(new Email("1234@isep.pt")));
        }

        catch (IllegalArgumentException exception) {
            assertEquals("No person found with that ID.", exception.getMessage());
        }
    }

    @Test
    @DisplayName("Find Person By Email - Main scenario")
    void findPersonByAttributes() {

        //Arrange:
        PersonRepository personRepository = new PersonInMemoryRepository();
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
    @DisplayName("Find Person By Email - False - Exception")
    void findPersonByEmailException() {

        //Arrange:
        PersonRepository personRepository = new PersonInMemoryRepository();
        personRepository.createPerson("Marta", new DateAndTime(1996, 4, 27),
                new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));

        //Act:
        try {
            personRepository.findPersonByEmail(new Email("1234@isep.pt"));
        }

        catch (IllegalArgumentException exception) {
            assertEquals("No person found with that email.", exception.getMessage());
        }
    }

    @Test
    @DisplayName("Verify If Person ID exists On Person Repository - Main Scenario")
    void verifyIfPersonIDExistsOnPersonRepository() {
        //Arrange
        PersonRepository personRepository = new PersonInMemoryRepository();

        Person personJose = new Person("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));

        personRepository.createPerson("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));

        //Act
        boolean personIDExists = personRepository.isIDOnRepository(personJose.getID());

        //Assert
        assertTrue(personIDExists);
    }

    @Test
    @DisplayName("Verify If Person ID exists On Person Repository - False Case")
    void verifyIfPersonIDExistsOnPersonRepositoryFalse() {
        //Arrange
        PersonRepository personRepository = new PersonInMemoryRepository();

        Person personJose = new Person("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));
        personRepository.createPerson("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));

        //Act
        boolean isIDOnRepo = personRepository.isIDOnRepository(new PersonID(new Email("fake@sofake.com")));

        //Assert
        assertFalse(isIDOnRepo);
    }


    @Test
    @DisplayName("Verify If Person Email exists On Person Repository - False Scenario")
    void verifyIfPersonEmailExistsOnPersonRepository() {
        //Arrange
        PersonRepository personRepository = new PersonInMemoryRepository();

        Person personJose = new Person("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));
        personRepository.createPerson("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));
        //Act
        boolean personEmailExists = personRepository.isPersonEmailOnRepository(new Email(personJose.getID().getEmail()));

        //Assert
        assertTrue(personEmailExists);
    }

    @Test
    @DisplayName("Verify If Person Email exists On Person Repository - False Case")
    void verifyIfPersonEmailExistsOnRepositoryFalse() {
        //Arrange
        PersonRepository personRepository = new PersonInMemoryRepository();
        personRepository.createPerson("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));

        //Act
        boolean personEmailExists = personRepository.isPersonEmailOnRepository(new Email("fake@sofake.com"));

        //Assert
        assertFalse(personEmailExists);
    }

    @Test
    @DisplayName("Repository Size")
    void verifyRepoSize() {
        //Arrange
        PersonRepository personRepository = new PersonInMemoryRepository();
        int expected = 1;

        Person personJose = new Person("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));
        personRepository.createPerson("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));

        //Act
        int realResult = personRepository.repositorySize();

        //Assert
        assertEquals(expected, realResult);
    }
}