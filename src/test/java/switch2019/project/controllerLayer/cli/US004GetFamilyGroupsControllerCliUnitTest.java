package switch2019.project.controllerLayer.cli;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2019.project.DTO.SerializationDTO.GroupDTO;
import switch2019.project.applicationLayer.US004GetFamilyGroupsService;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")

public class US004GetFamilyGroupsControllerCliUnitTest {
    @Mock
    private US004GetFamilyGroupsService service;
    @Autowired
    private US004GetFamilyGroupsController controller;

    @Test
    @DisplayName("Get all the groups who are families in the repository")
    public void returnFamilyGroups() {

        //Arrange
        Set<GroupDTO> expectedFamilyGroup = new LinkedHashSet<>();
        expectedFamilyGroup.add(new GroupDTO("FAMILY CARDOSO"));
        expectedFamilyGroup.add(new GroupDTO("FAMILY SIMPSON"));
        expectedFamilyGroup.add(new GroupDTO("FAMILY AZEVEDO"));

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.getFamilyGroups()).thenReturn(expectedFamilyGroup);

        //Act
        Set <GroupDTO> realResult = controller.getFamilyGroups();

        //Assert
        assertEquals(expectedFamilyGroup, realResult);

    }
}
