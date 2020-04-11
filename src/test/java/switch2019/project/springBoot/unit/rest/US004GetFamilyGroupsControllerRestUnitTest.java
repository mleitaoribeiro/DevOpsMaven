package switch2019.project.springBoot.unit.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2019.project.DTO.SerializationDTO.GroupDTO;
import switch2019.project.applicationLayer.US004GetFamilyGroupsService;
import switch2019.project.controllerLayer.controllersRest.US004GetFamilyGroupsControllerRest;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)

public class US004GetFamilyGroupsControllerRestUnitTest {
    @Mock
    @Autowired
    private US004GetFamilyGroupsService service;
    @Autowired
    private US004GetFamilyGroupsControllerRest controllerRest;

    @Test
    @DisplayName("Get all the groups who are families in the repository")
    public void returnFamilyGroups() {
        //Arrange
        Set<GroupDTO> expectedFamilyGroup = new HashSet<>();

        expectedFamilyGroup.add(new GroupDTO("FAMILY CARDOSO"));
        expectedFamilyGroup.add(new GroupDTO("FAMILY SIMPSON"));
        expectedFamilyGroup.add(new GroupDTO("FAMILY AZEVEDO"));

        ResponseEntity<Object> responseEntityExpected =  new ResponseEntity<>(expectedFamilyGroup, HttpStatus.OK);

        //Act
        MockitoAnnotations.initMocks(this);
        Mockito.when(service.getFamilyGroups()).thenReturn(expectedFamilyGroup);

        ResponseEntity <Object> responseEntityResult = controllerRest.getFamilyGroups();

        //Assert
        assertEquals(responseEntityExpected, responseEntityResult);

    }

}
