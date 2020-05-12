
package switch2019.project.applicationLayer.integration;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import switch2019.project.DTO.serializationDTO.GroupDTO;
import switch2019.project.applicationLayer.US004GetFamilyGroupsService;
import switch2019.project.assemblers.GroupDTOAssembler;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.domain.repositories.PersonRepository;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class US004GetFamilyGroupsServiceTest {

    @Autowired
    private US004GetFamilyGroupsService service;

    @Autowired
    private GroupRepository groupRepository;
    
    /**
     * Test if a Group is a family
     */

    @Test
    @DisplayName("Validate if a group is a family - All Family")
    void ifFamily() {

        //Arrange

        Group group = groupRepository.getByID(new GroupID(new Description("family Cardoso")));

        //Act & Assert
        assertTrue(service.isFamily(group));

    }

//    @Test
//    @DisplayName("Validate if a group is a family - All except one")
//    void ifGroupIsFamilyAllFamilyExceptOne() {
//        //Arrange
//
//        Group group = groupRepository.getByID(new GroupID(new Description("friends")));
//
//        //Act & Assert
//        assertFalse(service.isFamily(group));
//    }
//
//
//    @Test
//    @DisplayName("Validate if a group is a family - All except one")
//    void all() {
//        List<GroupDTO> expectedFamilyGroup = new ArrayList<>();
//        expectedFamilyGroup.add(new GroupDTO("family Cardoso"));
//        expectedFamilyGroup.add(new GroupDTO("family azevedo"));
//        expectedFamilyGroup.add(new GroupDTO("family Simpson"));
//        //Act
//        List<GroupDTO> realResult = service.getFamilyGroups();
//
//        //Assert
//        assertEquals(expectedFamilyGroup, realResult);
//    }
//

//        Description groupID1description = new Description("FAMILY CARDOSO");
//        GroupID groupID1 = new GroupID(groupID1description);
//        Description groupID2description = new Description("FAMILY AZEVEDO");
//        GroupID groupID2 = new GroupID(groupID2description);
//        Description groupID3description = new Description("FAMILY SIMPSON");
//        GroupID groupID3 = new GroupID(groupID3description);


//        List<GroupDTO> expectedFamilyGroup = new ArrayList<>();
//        expectedFamilyGroup.add(GroupDTOAssembler.createGroupDTO(groupID1));
//        expectedFamilyGroup.add(GroupDTOAssembler.createGroupDTO(groupID2));
//        expectedFamilyGroup.add(GroupDTOAssembler.createGroupDTO(groupID3));

//
//        List<GroupDTO> expectedFamilyGroup = new ArrayList<>();
//        expectedFamilyGroup.add(new GroupDTO("family Cardoso"));
//        expectedFamilyGroup.add(new GroupDTO("family azevedo"));
//        expectedFamilyGroup.add(new GroupDTO("family Simpson"));
//        //Act
//        List<GroupDTO> realResult = service.getFamilyGroups();
//
//        //Assert
//        assertEquals(expectedFamilyGroup, realResult);
//    }
}
