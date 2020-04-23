package switch2019.project.applicationLayer.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.SerializationDTO.AddedMemberDTO;
import switch2019.project.DTO.ServiceDTO.AddMemberDTO;
import switch2019.project.applicationLayer.US003AddMemberToGroupService;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class US003AddMemberToGroupServiceUnitTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private GroupsRepository groupsRepository;

    @InjectMocks
    private US003AddMemberToGroupService service;


    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test if a member was added to group")
    void addMemberToGroup() {
        //Arrange
        String personEmail = "beatriz.azevedo@gmail.com";
        String groupDescription = "Split Expenses";
        AddMemberDTO addMemberDTO = new AddMemberDTO(personEmail, groupDescription);

        Person member = new Person("Beatriz Azevedo", new DateAndTime(1995, 4, 12), new Address("Porto"),
                new Address("Avenida Antonio Domingues dos Santos", "Senhora da Hora", "4460-237"), new Email("beatriz.azevedo@gmail.com"));

        Person admin = new Person("Raquel Rodrigues", new DateAndTime(1989, 4, 12), new Address("Porto"),
                new Address("Avenida Vasco da Gama", "Gaia", "4460-000"), new Email("raquel.rod@gmail.com"));

        AddedMemberDTO addedMemberDTOexpected = new AddedMemberDTO(true, personEmail, groupDescription);

        //Act
        Mockito.when(personRepository.findPersonByEmail(new Email(personEmail)))
                .thenReturn(member);

        Mockito.when(groupsRepository.findGroupByDescription(new Description(addMemberDTO.getGroupDescription())))
                .thenReturn(new Group(new Description(groupDescription), admin));

        //Act
        AddedMemberDTO addedMemberDTOresult = service.addMemberToGroup(addMemberDTO);

        //Assert
        assertEquals(addedMemberDTOexpected, addedMemberDTOresult);
    }

    @Test
    @DisplayName("Test if a member was added to group - Person already in the group")
    void addMemberToGroupAlreadyIn() {
        //Arrange
        String personEmail = "beatriz.azevedo@gmail.com";
        String groupDescription = "Friends";
        AddMemberDTO addMemberDTO = new AddMemberDTO(personEmail, groupDescription);

        Person member = new Person("Beatriz Azevedo", new DateAndTime(1995, 4, 12), new Address("Porto"),
                new Address("Avenida Antonio Domingues dos Santos", "Senhora da Hora", "4460-237"), new Email("beatriz.azevedo@gmail.com"));

        //Act
        Mockito.when(personRepository.findPersonByEmail(new Email(personEmail)))
                .thenReturn(member);

        Mockito.when(groupsRepository.findGroupByDescription(new Description(addMemberDTO.getGroupDescription())))
                .thenThrow(new IllegalArgumentException("beatriz.azevedo@gmail.com is already on group friends."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.addMemberToGroup(addMemberDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("beatriz.azevedo@gmail.com is already on group friends.");
    }

}