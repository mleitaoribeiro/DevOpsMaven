package switch2019.project.controllerLayer.rest.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import switch2019.project.DTO.serializationDTO.GroupDTO;
import switch2019.project.applicationLayer.US004GetFamilyGroupsService;
import switch2019.project.controllerLayer.rest.US004GetFamilyGroupsControllerRest;
import java.util.LinkedHashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")

public class US004GetFamilyGroupsControllerRestUnitTest {

    @Mock
    private US004GetFamilyGroupsService service;

    @InjectMocks
    private US004GetFamilyGroupsControllerRest controllerRest;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("Get all the groups who are families in the repository")
    public void returnFamilyGroups() {
        //Arrange
        Set<GroupDTO> expectedFamilyGroup = new LinkedHashSet<>();

        expectedFamilyGroup.add(new GroupDTO("FAMILY CARDOSO"));
        expectedFamilyGroup.add(new GroupDTO("FAMILY SIMPSON"));
        expectedFamilyGroup.add(new GroupDTO("FAMILY AZEVEDO"));

        ResponseEntity<Object> responseEntityExpected =  new ResponseEntity<>(expectedFamilyGroup, HttpStatus.OK);

        //Act
        Mockito.when(service.getFamilyGroups()).thenReturn(expectedFamilyGroup);

        ResponseEntity <Object> responseEntityResult = controllerRest.getFamilyGroups("family");

        //Assert
        Assertions.assertAll(
                () -> assertEquals(responseEntityExpected, responseEntityResult),
                () -> assertEquals(HttpStatus.OK, responseEntityResult.getStatusCode()),
                () -> assertEquals(expectedFamilyGroup, responseEntityResult.getBody()),
                () -> assertNotNull(responseEntityResult)
        );

    }

}
