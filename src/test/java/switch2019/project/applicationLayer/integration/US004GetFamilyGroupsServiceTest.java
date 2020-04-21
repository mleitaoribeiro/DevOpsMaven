
package switch2019.project.applicationLayer.integration;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import switch2019.project.DTO.SerializationDTO.GroupDTO;
import switch2019.project.applicationLayer.US004GetFamilyGroupsService;

import java.util.LinkedHashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class US004GetFamilyGroupsServiceTest {

    @Autowired
    private US004GetFamilyGroupsService service;

    @Test
    @DisplayName("Get all the groups who are families in the repository")
    void getFamilyGroups() {

        //Arrange
        Set<GroupDTO> expectedFamilyGroup = new LinkedHashSet<>();
        expectedFamilyGroup.add(new GroupDTO("FAMILY CARDOSO"));
        expectedFamilyGroup.add(new GroupDTO("FAMILY SIMPSON"));
        expectedFamilyGroup.add(new GroupDTO("FAMILY AZEVEDO"));


        //Act
        Set <GroupDTO> realResult = service.getFamilyGroups();

        //Assert
        assertEquals(expectedFamilyGroup, realResult);
    }
}
