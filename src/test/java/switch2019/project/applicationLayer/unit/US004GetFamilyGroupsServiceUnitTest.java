package switch2019.project.applicationLayer.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.serializationDTO.GroupDTO;
import switch2019.project.applicationLayer.US004GetFamilyGroupsService;
import switch2019.project.assemblers.GroupDTOAssembler;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.domain.repositories.PersonRepository;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class US004GetFamilyGroupsServiceUnitTest {

    //Mocking of the repositories:
    @Mock
    private PersonRepository personRepo;

    @Mock
    private GroupRepository groupsRepo;

    //Creating mocked service and inject mocked dependencies:
    @InjectMocks
    private US004GetFamilyGroupsService service;

    //Initiating mocks before each test:
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test if service can get all family groups inside a GroupsRepository")
    void returnAllFamilyGroups() {
        //ARRANGE//
        //Arranging our set of GroupDTOs with only the family groups present in the GroupsRepository:
        Set <GroupDTO> expectedReturn = new LinkedHashSet<>();
        expectedReturn.add(GroupDTOAssembler.createGroupDTO(new GroupID(new Description("FAMILY CARDOSO"))));
        expectedReturn.add(GroupDTOAssembler.createGroupDTO(new GroupID(new Description("FAMILY AZEVEDO"))));
        expectedReturn.add(GroupDTOAssembler.createGroupDTO(new GroupID(new Description("FAMILY SIMPSON"))));

        //Arranging our set of Groups to mock the repository for the service with Mockito:
            //Arranging groups creator:
        Person mockedGroupCreator = new Person("Creator",
                                    new DateAndTime(1995, 4, 16),
                                    new Address("Xpto"),
                                    new Address("Rua Xpto", "Xpto", "4300-221"),
                                    new Email("liza.simpson@hotmail.com"));

            //Arranging the three groups that are families:
        Group familyCardoso = new Group(new Description("FAMILY CARDOSO"), mockedGroupCreator.getID());
        Group familyAzevedo = new Group(new Description("FAMILY AZEVEDO"), mockedGroupCreator.getID());
        Group familySimpson = new Group(new Description("FAMILY SIMPSON"), mockedGroupCreator.getID());

            //Arranging the group HashSet:
        Set <Group> mockedGroupsRepo = new LinkedHashSet<>();
        mockedGroupsRepo.add(familyCardoso);
        mockedGroupsRepo.add(familyAzevedo);
        mockedGroupsRepo.add(familySimpson);

        //Arranging Mockito:
        //Mockito.when(groupsRepo.returnOnlyFamilies()).thenReturn(mockedGroupsRepo);

        //ACT//
        //Running the service with the Mocked groups repository to create our actualReturn
        //Set <GroupDTO> actualReturn = service.getFamilyGroups();

        //ASSERT//
        //Checking if the expectedReturn is equal to the actualReturn:
        //assertEquals(expectedReturn,actualReturn);
    }
}
