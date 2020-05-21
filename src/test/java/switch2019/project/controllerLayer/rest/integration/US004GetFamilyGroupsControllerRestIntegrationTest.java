package switch2019.project.controllerLayer.rest.integration;

import org.json.JSONArray;
import org.json.JSONTokener;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2019.project.AbstractTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US004GetFamilyGroupsControllerRestIntegrationTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP() {
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

        String expected = "FAMILY SIMPSON";
        String expected1 = "FAMILY CARDOSO";
        String expected2 = "FAMILY AZEVEDO";
        String expectedLink0 = "[{\"rel\":\"self\",\"href\":\"http:\\/\\/localhost\\/groups\\/FAMILY%20SIMPSON\"}]";
        String expectedLink1 = "[{\"rel\":\"self\",\"href\":\"http:\\/\\/localhost\\/groups\\/FAMILY%20CARDOSO\"}]";
        String expectedLink2 = "[{\"rel\":\"self\",\"href\":\"http:\\/\\/localhost\\/groups\\/FAMILY%20AZEVEDO\"}]";


        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        // Status Response
        int status = mvcResult.getResponse().getStatus();

        // OutputDTO
        String result = mvcResult.getResponse().getContentAsString();

        JSONArray jArray = (JSONArray) new JSONTokener(result).nextValue();

        // Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expected, jArray.getJSONObject(0).getString("groupDescription")),
                () -> assertEquals(expected1, jArray.getJSONObject(1).getString("groupDescription")),
                () -> assertEquals(expected2, jArray.getJSONObject(2).getString("groupDescription")),
                () -> assertEquals(expectedLink0, jArray.getJSONObject(0).getString("links")),
                () -> assertEquals(expectedLink1, jArray.getJSONObject(1).getString("links")),
                () -> assertEquals(expectedLink2, jArray.getJSONObject(2).getString("links"))
                );

    }


    @Test
    @DisplayName("Get groups - type empty - Exception")
    void returnGroupsTypeEmpty() throws Exception {

        // Status Request
        String uri = "/groups?type=";

        String expected = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) + "\"," +
                "\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"One of the parameters is invalid or is missing.\",\"message\":\"The type can't be empty.\"}";

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
                () -> assertEquals(422, status),
                () -> assertEquals(expected, result)
        );
    }

    @Test
    @DisplayName("Get groups - type different from family - Exception")
    void returnGroupsTypeDifferentFromFamily() throws Exception {

        // Status Request
        String uri = "/groups?type=friends";

        String expected = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) + "\"," +
                "\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"This resource was not found.\",\"message\":\"No groups found with that type.\"}";
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
                () -> assertEquals(422, status),
                () -> assertEquals(expected, result)
        );
    }
}