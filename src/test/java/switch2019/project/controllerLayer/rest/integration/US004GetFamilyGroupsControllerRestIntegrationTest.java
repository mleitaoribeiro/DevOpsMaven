package switch2019.project.controllerLayer.rest.integration;

import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2019.project.AbstractTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US004GetFamilyGroupsControllerRestIntegrationTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP(){
        super.setUP();
    }

    /**
     * US004
     * As system manager I want to know which groups are families
     *
     * @throws Exception
     */
    @Test
    @DisplayName("Get groups - type family - Main Scenario")
    void getGroupsTypeFamily() throws Exception {

        // Status Request
        String uri = "/groups?type=family";

        String expected = "[{\"groupDescription\":\"" + "FAMILY SIMPSON\",\"links\":" +
                "[{\"rel\":\"self\",\"href\":\"http://localhost/groups/FAMILY%20SIMPSON\"}]}," +
                "{\"groupDescription\":\"FAMILY CARDOSO\",\"links\":" +
                "[{\"rel\":\"self\",\"href\":\"http://localhost/groups/FAMILY%20CARDOSO\"}]}," +
                "{\"groupDescription\":\"FAMILY AZEVEDO\",\"links\":" +
                "[{\"rel\":\"self\",\"href\":\"http://localhost/groups/FAMILY%20AZEVEDO\"}]}]";

        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        // Status Response
        int status = mvcResult.getResponse().getStatus();

        // OutputDTO
        String result = mvcResult.getResponse().getContentAsString();

        // Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expected, result)
        );
    }

    @Test
    @DisplayName("Get groups - type empty - empty result")
    void returnGroupsTypeEmpty() throws Exception {

        // Status Request
        String uri = "/groups?type=";

        String expected = "[]";

        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        // Status Response
        int status = mvcResult.getResponse().getStatus();

        // OutputDTO
        String result = mvcResult.getResponse().getContentAsString();

        // Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expected, result)
        );
    }

    @Test
    @DisplayName("Get groups - type different from family - empty result")
    void returnGroupsTypeDifferentFromFamily() throws Exception {

        // Status Request
        String uri = "/groups?type=friends";

        String expected = "[]";

        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        // Status Response
        int status = mvcResult.getResponse().getStatus();

        // OutputDTO
        String result = mvcResult.getResponse().getContentAsString();

        // Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expected, result)
        );
    }
}