package switch2019.project.applicationLayer.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import switch2019.project.applicationLayer.US004GetFamilyGroupsService;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.domain.repositories.PersonRepository;

@SpringBootTest
@Transactional
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
    @DisplayName("Test service-  family groups inside a GroupsRepository")
    void areFamilyGroups() {
        //ARRANGE//

//    @Test
//    @DisplayName("Test if service can get all family groups inside a GroupsRepository")
//    void returnAllFamilyGroups() {
//        //ARRANGE//
//        //Arranging our set of GroupDTOs with only the family groups present in the GroupsRepository:
//        Set <GroupDTO> expectedReturn = new LinkedHashSet<>();
//        expectedReturn.add(GroupDTOAssembler.createGroupDTO(new GroupID(new Description("FAMILY CARDOSO"))));
//        expectedReturn.add(GroupDTOAssembler.createGroupDTO(new GroupID(new Description("FAMILY AZEVEDO"))));
//        expectedReturn.add(GroupDTOAssembler.createGroupDTO(new GroupID(new Description("FAMILY SIMPSON"))));
//
//        //Arranging our set of Groups to mock the repository for the service with Mockito:
//            //Arranging groups creator:
//        Person mockedGroupCreator = new Person("Creator",
//                                    new DateAndTime(1995, 4, 16),
//                                    new Address("Xpto"),
//                                    new Address("Rua Xpto", "Xpto", "4300-221"),
//                                    new Email("liza.simpson@hotmail.com"));
//        Person mockedGroupCreator2 = new Person("Beatriz Azevedo",
//                new DateAndTime(1995, 04, 12),
//                new Address("Porto"),
//                new Address("Avenida Antonio Domingues dos Santos", "Senhora da Hora", "4460-237"),
//                new Email("beatriz.azevedo@gmail.com"));
//
//        Person mockedGroupCreator3 = new Person(
//                "Marta Maria Cardoso",
//                new DateAndTime(1995, 04, 12),
//                new Address("Porto"),
//                new Address("Rua de Requeixos", "Vizela", "4620-580"),
//                new Email("1191780@isep.ipp.pt"));
//
//        Person mockedGroupCreator4  =  new Person(
//                "Jerry Smith",
//                new DateAndTime(1967, 2, 3),
//                new Address("Seattle"),
//                new Address("Smiths house", "Seattle", "4520-266"),
//                new Email("jerry.smith@gmail.com"));
//
//            //Arranging the three groups that are families:
//        Group familySimpson = new Group(new Description("FAMILY SIMPSON"), mockedGroupCreator.getID());
//        Group familyAzevedo = new Group(new Description("FAMILY AZEVEDO"), mockedGroupCreator2.getID());
//        Group familyCardoso = new Group(new Description("FAMILY CARDOSO"), mockedGroupCreator3.getID());
//
//
//            //Arranging the group HashSet:
//        Set <Group> mockedGroupsRepo = new LinkedHashSet<>();
//        mockedGroupsRepo.add(familyCardoso);
//        mockedGroupsRepo.add(familyAzevedo);
//        mockedGroupsRepo.add(familySimpson);
//
//
//        //Arranging Mockito:
//        Mockito.when(groupsRepo.getAllGroups()).thenReturn(mockedGroupsRepo);
//
//        //ACT//
//        //Running the service with the Mocked groups repository to create our actualReturn
//        Set <GroupDTO> actualReturn = service.getFamilyGroups();
//
//        //ASSERT//
//        //Checking if the expectedReturn is equal to the actualReturn:
//        assertEquals(expectedReturn,actualReturn);
//    }
    }
}

