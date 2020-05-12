package switch2019.project.dataModel.dataAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.dataModel.entities.AddressJpa;
import switch2019.project.dataModel.entities.PersonJpa;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;

import static org.junit.jupiter.api.Assertions.*;

class PersonDataAssemblerTest {

    /**
     * Test if the toData method can transform an object Person to a PersonJpa
     */
    @Test
    @DisplayName("Test if a Person is transform in a PersonJpa - true")
    public void toData() {

        //Arrange
        Person jerrySmith  = new Person ("Jerry Smith", new DateAndTime(1967, 02, 03),
                new Address("Seattle"), new Address("Requeixos", "Vizela", "4620-585"), new Email("jerry.smith@gmail.com"));

        PersonJpa expected = new PersonJpa("jerry.smith@gmail.com", "Jerry Smith", "1967-02-03",
                "Seattle", new AddressJpa("Requeixos", "Vizela", "4620-585"));

        //Act
        PersonJpa result = PersonDataAssembler.toData(jerrySmith);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test if a Person is transform in a PersonJpa - false")
    public void toDataNotTrue() {

        //Arrange
        Person jerrySmith  = new Person ("Jerry Smith", new DateAndTime(1967, 02, 03),
                new Address("Seattle"),new Address( "Requeixos", "Vizela", "4620-585"), new Email("jerry.smith@gmail.com"));

        PersonJpa expected = new PersonJpa("maggie.smith@gmail.com", "Jerry Smith", "1967-02-03",
                "Seattle", new AddressJpa( "Requeixos", "Vizela", "4620-585"));

        //Act
        PersonJpa result = PersonDataAssembler.toData(jerrySmith);

        //Assert
        assertNotEquals(expected, result);
    }

    /**
     * Test if the toDomain method can transform an object PersonJpa to a Person
     */
    @Test
    @DisplayName("Test if a PersonJpa is transform in a Person - true")
    public void toDomain() {

        //Arrange
        Person expected  = new Person ("Jerry Smith", new DateAndTime(1967, 02, 03),
                new Address("Seattle"),new Address( "Requeixos", "Vizela", "4620-585"), new Email("jerry.smith@gmail.com"));

        PersonJpa jerrySmithJpa = new PersonJpa("jerry.smith@gmail.com", "Jerry Smith", "1967-02-03",
                "Seattle", new AddressJpa( "Requeixos", "Vizela", "4620-585"));

        //Act
        Person result = PersonDataAssembler.toDomain(jerrySmithJpa);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test if a PersonJpa is transform in a Person - false")
    public void toDomainNotTrue() {

        //Arrange
        Person expected = new Person ("Jerry Smith", new DateAndTime(1967, 02, 03),
                new Address("Seattle"), new Address( "Requeixos", "Vizela", "4620-585"), new Email("jerry.smith@gmail.com"));

        PersonJpa jerrySmithJpa = new PersonJpa("maggie.smith@gmail.com", "Jerry Smith", "1967-02-03",
                "Seattle", new AddressJpa( "Requeixos", "Vizela", "4620-585"));

        //Act
        Person result = PersonDataAssembler.toDomain(jerrySmithJpa);

        //Assert
        assertNotEquals(expected, result);
    }

}