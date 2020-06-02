package switch2019.project.applicationLayer.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import switch2019.project.DTO.serializationDTO.GroupDTO;
import switch2019.project.applicationLayer.US004GetFamilyGroupsService;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.repositories.GroupRepository;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class US004GetFamilyGroupsServiceTest {

    @Autowired
    private US004GetFamilyGroupsService service;

    @Autowired
    private GroupRepository groupRepository;

    /**
     * Test if a Group is a family
     */

    @Test
    @DisplayName("Validate if a group is a family - True")
    void ifFamily() {

        //Arrange

        Group group = groupRepository.getByID(new GroupID(new Description("family Cardoso")));

        //Act & Assert
        assertTrue(service.isFamily(group));

    }

    @Test
    @DisplayName("Validate if a group is a family - False")
    void ifGroupIsFamilyAllFamilyExceptOne() {
        //Arrange

        Group group = groupRepository.getByID(new GroupID(new Description("friends")));

        //Act & Assert
        assertFalse(service.isFamily(group));
    }


    @Test
    @DisplayName("Validate if a group is a family - Get all")
    void all() {
        Set<GroupDTO> expectedFamilyGroup = new LinkedHashSet<>();
        expectedFamilyGroup.add(new GroupDTO("family Cardoso"));
        expectedFamilyGroup.add(new GroupDTO("family azevedo"));
        expectedFamilyGroup.add(new GroupDTO("family Simpson"));

        //Act
        Set<GroupDTO> realResult = service.getFamilyGroups();

        //Assert
        assertEquals(expectedFamilyGroup, realResult);
    }
}