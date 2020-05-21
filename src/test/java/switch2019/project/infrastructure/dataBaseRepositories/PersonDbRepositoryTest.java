package switch2019.project.infrastructure.dataBaseRepositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonDbRepositoryTest {

    @Autowired
    private PersonDbRepository repository;

    private Person person;
    private Person personNotPersisted;
    private Person father;
    private Person mother;

    @BeforeEach
    public void setup() {
        person =  repository.createPerson ("Marta", new DateAndTime(1995, 11, 12),
                new Address("Porto"), new Address("Miguel Bombarda", "Porto", "4620-223"),
                new Email("marta@gmail.com"));

        personNotPersisted = new Person("António", new DateAndTime(1990, 01, 23),
                new Address("Guimarães"), new Address("Avenida de França", "Porto", "4620-262"),
                new Email("antonio@gmail.com"));

        father = new Person("Roberto Azevedo", new DateAndTime(1967, 1, 9),
                new Address("Lisboa"), new Address("Avenida Antonio Domingues dos Santos", "Senhora da Hora", "4460-237"),
                new Email("roberto@gmail.com"));

        mother = new Person("Margarida Azevedo", new DateAndTime(1964, 12, 1),
                new Address("Guimarães"), new Address("Avenida Antonio Domingues dos Santos", "Senhora da Hora", "4460-237"),
                new Email("maria@gmail.com"));

    };

    @Test
    @DisplayName("Test if a person is saved in Jpa Repository")
    void createPerson() throws Exception {

        //Arrange
        Long expectedSize = repository.repositorySize() + 1;

        //Act
       repository.createPerson ("João", new DateAndTime(1993, 10, 12),
                new Address("Porto"), new Address("Avenida Brasil", "Porto", "4620-262"),
                new Email("joao@gmail.com"));

       ///Assert
        Assertions.assertAll(
                () -> Assert.notNull(repository.findPersonByEmail(new Email("joao@gmail.com")), "Persons saved is found"),
                () -> assertEquals(expectedSize, repository.repositorySize())
        );
    }

    @Test
    @DisplayName("Test if a person is saved in Jpa Repository - Person Already Exists - size")
    void createPersonAlreadyExists() throws Exception {

        //Arrange
        Long expectedSize = repository.repositorySize();

        //Act
        repository.createPerson ("Marta", new DateAndTime(1995, 11, 12),
                new Address("Porto"), new Address("Miguel Bombarda", "Porto", "4620-223"),
                new Email("marta@gmail.com"));

        ///Assert
        Assertions.assertAll(
                () -> assertEquals(expectedSize, repository.repositorySize())
        );
    }

    @Test
    @DisplayName("Test if a person with mother and father is saved in Jpa Repository")
    void testCreatePerson() throws Exception {
        //Arrange
        Long expectedSize = repository.repositorySize() + 1;

        //Act
        repository.createPerson ("Manuela", new DateAndTime(2000, 12, 13),
                new Address("Porto"), new Address("Avenida Brasil", "Porto", "4620-262"),
              father.getID(), mother.getID(),   new Email("manuela@gmail.com"));
        //Assert
        Assertions.assertAll(
                () -> Assert.notNull(repository.findPersonByEmail(new Email("manuela@gmail.com")), "Persons saved is found"),
                () -> assertEquals(expectedSize, repository.repositorySize())
        );
    }

    @Test
    @DisplayName("Test if a person is returned form JPA - TRUE")
    void getByID() throws Exception {
        //Arrange
        Person expected = person;

        //Act
        Person result = repository.getByID(new PersonID( new Email("marta@gmail.com")));

        //Assert
        assertEquals(expected, person);
    }

    @Test
    @DisplayName("Test if a person is returned form JPA - Exception")
    void getByIDException() throws Exception {
        //Act
        Throwable thrown = catchThrowable(() -> { repository.getByID(new PersonID( new Email("exception@gmail.com")));});

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No person found with that email.");
    }

    @Test
    @DisplayName("Find person by Email - true")
    void findByEmail() {
        //Arrange
        Person expected = person;

        //Act
        Person result = repository.findPersonByEmail(new Email("marta@gmail.com"));

        //Arrange
        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Find person by Email - Exception")
    void findByEmailException() {
        //Act
        Throwable thrown = catchThrowable(() -> { repository.findPersonByEmail(new Email("exception@gmail.com"));});

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No person found with that email.");
    }


    @Test
    @DisplayName("Test if PersonEmail is on JPA repository")
    void isPersonEmailOnRepositoryTrue() throws Exception {
        assertTrue(repository.isPersonEmailOnRepository(new Email(person.getID().toString())));
    }

    @Test
    @DisplayName("Test if PersonEmail is not in  the JPA repository")
    void isPersonEmailOnRepositoryFalse() throws Exception {
        assertFalse(repository.isPersonEmailOnRepository(new Email(personNotPersisted.getID().toString())));
    }

    @Test
    @DisplayName("Test if ID is on JPA repository")
    void isIDOnRepositoryTrue() throws Exception {
        assertTrue(repository.isIDOnRepository(person.getID()));
    }

    @Test
    @DisplayName("Test if ID is on JPA repository")
    void isIDOnRepositoryFalse() throws Exception {
        assertFalse(repository.isIDOnRepository(personNotPersisted.getID()));
    }

}