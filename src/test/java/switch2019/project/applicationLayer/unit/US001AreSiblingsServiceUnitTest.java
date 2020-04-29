package switch2019.project.applicationLayer.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.serviceDTO.AreSiblingsDTO;
import switch2019.project.applicationLayer.US001AreSiblingsService;
import switch2019.project.assemblers.PersonDTOAssembler;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.repositories.PersonRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class US001AreSiblingsServiceUnitTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private US001AreSiblingsService service;

    private Person father;
    private Person mother;
    private Person sibling;
    private Person relatedSibling;
    private Person notRelatedSibling;


    @BeforeEach
    public void setup() {

        MockitoAnnotations.initMocks(this);

        father = new Person("Roberto Azevedo", new DateAndTime(1967, 1, 9),
                new Address("Lisboa"), new Address("Avenida Antonio Domingues dos Santos", "Senhora da Hora", "4460-237"),
                new Email("roberto@gmail.com"));

        mother = new Person("Margarida Azevedo", new DateAndTime(1964, 12, 1),
                new Address("Guimarães"), new Address("Avenida Antonio Domingues dos Santos", "Senhora da Hora", "4460-237"),
                new Email("maria@gmail.com"));

        sibling = new Person("Beatriz Azevedo", new DateAndTime(1995, 04, 12),
                new Address("Porto"), new Address("Avenida Antonio Domingues dos Santos", "Senhora da Hora", "4460-237"),
                mother.getID(), father.getID(), new Email("beatriz.azevedo@gmail.com"));

        relatedSibling = new Person("Hugo Azevedo", new DateAndTime(1993, 9, 1),
                new Address("Porto"), new Address("Rua das Flores", "Porto", "4050-262"),
                mother.getID(), father.getID(), new Email("hugo.azevedo@gmail.com"));

        sibling.addSibling(relatedSibling);

        notRelatedSibling = new Person ("Richard Sanchez", new DateAndTime(1950, 9, 1),
                new Address("Seattle"), new Address("Smiths house", "Seattle", "4520-266"),
                new Email("rick@gmail.com"));
    }


    @Test
    @DisplayName("Test if two individuals are siblings - same mother, same father and are in each other list")
    void AreSiblingsSameMotherFatherAndList() {
        //Arrange
        String emailSibling = "beatriz.azevedo@gmail.com";
        String emailRelatedSiblings = "hugo.azevedo@gmail.com";

        //Input DTO
        AreSiblingsDTO siblingsDTO = new AreSiblingsDTO(emailSibling, emailRelatedSiblings);

        //Act
        Mockito.when(personRepository.findPersonByEmail(new Email(siblingsDTO.getEmailPersonOne()))).thenReturn(sibling);
        Mockito.when(personRepository.findPersonByEmail(new Email(siblingsDTO.getEmailPersonTwo()))).thenReturn(relatedSibling);

        boolean areSiblings =service.areSiblings(siblingsDTO);

        //Assert
        assertTrue(areSiblings);
    }

    @Test
    @DisplayName("Test if two individuals are siblings - not related")
    void AreSiblingsFalse() {
        //Arrange
        String emailSibling = "hugo.azevedo@gmail.com";
        String emailNotRelatedSiblings = "rick@gmail.com";

        //Input DTO
        AreSiblingsDTO siblingsDTO = new AreSiblingsDTO(emailSibling, emailNotRelatedSiblings);

        //Act
        Mockito.when(personRepository.findPersonByEmail(new Email(siblingsDTO.getEmailPersonOne()))).thenReturn(sibling);
        Mockito.when(personRepository.findPersonByEmail(new Email(siblingsDTO.getEmailPersonTwo()))).thenReturn(notRelatedSibling);

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
                thenThrow(new ArgumentNotFoundException("No person found with that email."));

        Throwable thrown = catchThrowable(() -> { service.areSiblings(siblingsDTO); });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No person found with that email.");
    }

    @Test
    @DisplayName("Test if two individuals are siblings - Exception - null email")
    void AreSiblingsNullEmail() {
        //Arrange
        // Input DTO
        AreSiblingsDTO siblingsDTO = PersonDTOAssembler.createAreSiblingsDTO(null, "roberto@gmail.com");
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
