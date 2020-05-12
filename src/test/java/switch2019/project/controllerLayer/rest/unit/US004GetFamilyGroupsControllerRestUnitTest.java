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
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
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
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Get groups - type family - Main Scenario")
    void getGroupsTypeFamily() {

        //Arrange
        List<GroupDTO> expectedFamilyGroup = new ArrayList<>();

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

    @Test
    @DisplayName("Get groups - type family - empty result (no families found on Group Repository")
    void getGroupsTypeFamilyEmptyResult() {

        //Arrange
        List<GroupDTO> expectedFamilyGroup = new ArrayList<>(); // Empty Set

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

    @Test
    @DisplayName("Get groups - type empty - Exception")
    void getGroupsTypeEmpty() {

        // Arrange & Act
        Throwable thrown = catchThrowable(() -> {
            controllerRest.getFamilyGroups("");
        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The type can't be empty.");
    }

    @Test
    @DisplayName("Get groups - type different from family - Exception")
    void getGroupsTypeDifferentFromFamily() {

        // Arrange & Act
        Throwable thrown = catchThrowable(() -> {
            controllerRest.getFamilyGroups("friends");
        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No groups found with that type.");
    }
}
