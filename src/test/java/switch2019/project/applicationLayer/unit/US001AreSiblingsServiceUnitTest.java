package switch2019.project.applicationLayer.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import switch2019.project.DTO.ServiceDTO.AreSiblingsDTO;
import switch2019.project.applicationLayer.US001AreSiblingsService;
import switch2019.project.assemblers.PersonDTOAssembler;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.infrastructure.repositories.PersonRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class US001AreSiblingsServiceUnitTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private US001AreSiblingsService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("Test if two individuals are siblings - same mother, same father and are in each other list")
    void AreSiblingsSameMotherFatherAndList() {
        //Arrange
        //Family with same mother, same father and are in each others list
        Person father = personRepository.createPerson("José", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("father1@isep.ipp.pt"));
        Person mother = personRepository.createPerson("Maria", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("mother1@isep.ipp.pt"));

        Person antonio = new Person("António", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), mother, father, new Email("antonio@isep.ipp.pt"));

        Person manuel = new Person("Manuel", new DateAndTime(1995, 12, 13), new Address("Matosinhos"),
                new Address("Rua X", "Porto", "4520-266"), mother, father, new Email("manuel@isep.ipp.pt"));
        antonio.addSibling(manuel);

        //Input DTO
        AreSiblingsDTO siblingsDTO = new AreSiblingsDTO("antonio@isep.ipp.pt", "manuel@isep.ipp.pt");

        //Act
        Mockito.when(personRepository.findPersonByEmail(new Email(siblingsDTO.getEmailPersonOne()))).thenReturn(antonio);
        Mockito.when(personRepository.findPersonByEmail(new Email(siblingsDTO.getEmailPersonTwo()))).thenReturn(manuel);

        boolean areSiblings =service.areSiblings(siblingsDTO);

        //Assert
        assertTrue(areSiblings);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - not related")
    void AreSiblingsFalse() {
        //Arrange
        Person fatherOfHugo =  new Person("Roberto Azevedo", new DateAndTime(1967, 1, 9),
                new Address("Lisboa"), new Address("Avenida Antonio Domingues dos Santos", "Senhora da Hora", "4460-237"),
                new Email("roberto@gmail.com"));

        Person motherOfHugo =  new Person("Margarida Azevedo", new DateAndTime(1964, 12, 1),
                new Address("Guimarães"),  new Address("Avenida Antonio Domingues dos Santos", "Senhora da Hora", "4460-237"),
                new Email("maria@gmail.com"));

        Person personHugo = new Person("Hugo Azevedo", new DateAndTime(1993, 9, 1),
                new Address("Porto"), new Address("Rua das Flores", "Porto", "4050-262"),
                motherOfHugo, fatherOfHugo, new Email("hugo.azevedo@gmail.com"));

        Person personMaria =  new Person("Maria Cardoso", new DateAndTime(1964, 1, 19),
                new Address("Porto"), new Address("Rua de Requeixos", "Vizela", "4620-580"),
                new Email("maria.cardoso_1@gmail.com"));

        //Input DTO
        AreSiblingsDTO siblingsDTO = new AreSiblingsDTO("hugo.azevedo@gmail.com", "maria.cardoso_1@gmail.com");

        //Act
        Mockito.when(personRepository.findPersonByEmail(new Email(siblingsDTO.getEmailPersonOne()))).thenReturn(personHugo);
        Mockito.when(personRepository.findPersonByEmail(new Email(siblingsDTO.getEmailPersonTwo()))).thenReturn(personMaria);

        //Act
        boolean areSiblings1 = service.areSiblings(siblingsDTO);

        //Assert
        assertFalse(areSiblings1);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - Exception - Person does't exist on Person Repository")
    void AreSiblingsPersonDoesntExist() {
        //Arrange
        String emailPersonInvalid = "father@isep.ipp.pt";
        String emailPersonValid = "maria.cardoso_1@gmail.com";

        // Input DTO
        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(emailPersonInvalid, emailPersonValid);

        //Act
        Mockito.when(personRepository.findPersonByEmail(new Email(emailPersonInvalid))).
                thenThrow(new IllegalArgumentException("No person found with that email."));

        Throwable thrown = catchThrowable(() -> { service.areSiblings(siblingsDTO); });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No person found with that email.");
    }

    @Test
    @DisplayName("Test if two individuals are siblings - Exception - null email")
    void AreSiblingsInvalidEmailNull() {
        //Arrange
        // Input DTO
        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(null, "maria.cardoso_1@gmail.com");
        Email emailPersonNull = null;

        //Act
        Mockito.when(personRepository.findPersonByEmail(emailPersonNull)).
                thenThrow(new IllegalArgumentException("No person found with that email."));

        Throwable thrown = catchThrowable(() -> { service.areSiblings(siblingsDTO); });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email can't be null.");
    }
}
