
package switch2019.project.controllerLayer.rest.integration;

import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.deserializationDTO.CreateGroupInfoDTO;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US002_1CreateGroupControllerRestIntegrationTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP(){
        super.setUP();
    }

    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - Main Scenario")
    void createGroupAndBecomeAdminHappyCase() throws Exception {

        //Arrange
        String uri = "/groups";

        final String groupDescription = "Online Shopping";
        final String personEmail = "1110120@isep.ipp.pt";

        CreateGroupInfoDTO createGroupInfoDTO = new CreateGroupInfoDTO ();
        createGroupInfoDTO.setGroupDescription(groupDescription);
        createGroupInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((createGroupInfoDTO));

        String expectedLinks = "{\"self\":{\"href\":\"http:\\/\\/localhost\\/groups\\/ONLINE%20SHOPPING\"}}";

       //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isCreated())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(201, status),
                () -> assertEquals(groupDescription.toUpperCase(),result.getString("groupDescription")),
                () -> assertEquals (expectedLinks, result.getString("_links"))
        );
    }


    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - person doesn't exist")
    void createGroupAndBecomeAdminPersonDoesNotExists() throws Exception {

        //Arrange
        String uri = "/groups";

        final String groupDescriptionStr = "Expenses";
        final String personEmail = "qwerty@gmail.com";

        CreateGroupInfoDTO createGroupInfoDTO = new CreateGroupInfoDTO();
        createGroupInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((createGroupInfoDTO));

        String expectedResolvedException = new ArgumentNotFoundException("No person found with that email.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("This resource was not found.", result.getString("error")),
                () -> assertEquals ("No person found with that email.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }


  /*  @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - group Already Exists")
    void createGroupAndBecomeAdminGroupAlreadyExists() throws Exception {

        //Arrange
        String uri = "/groups";

        final String groupDescriptionStr = "SWitCH";
        final String personEmail = "morty@gmail.com";

        CreateGroupInfoDTO createGroupInfoDTO = new CreateGroupInfoDTO();
        createGroupInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((createGroupInfoDTO));

        String expectedResolvedException = new ResourceAlreadyExistsException("This group description already exists.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isConflict())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();


        //Assert
        Assertions.assertAll(
                () -> assertEquals(409, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("409", result.getString("statusCode")),
                () -> assertEquals("CONFLICT", result.getString("status")),
                () -> assertEquals ("This resource already exists.", result.getString("error")),
                () -> assertEquals ("This group description already exists.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }
*/

    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - invalid email - null")
    void createGroupAndBecomeAdminInvalidEmailNull() throws Exception {

        //Arrange
        String uri = "/groups";

        final String groupDescriptionStr = "SWitCH";
        final String personEmail = null;

        CreateGroupInfoDTO createGroupInfoDTO = new CreateGroupInfoDTO();
        createGroupInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((createGroupInfoDTO));

        String expectedResolvedException = new IllegalArgumentException("The email can't be null.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals ("The email can't be null.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );

    }

    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - invalid email - empty")
    void createGroupAndBecomeAdminInvalidEmailEmpty() throws Exception {

        //Arrange
        String uri = "/groups";

        final String groupDescriptionStr = "SWitCH";
        final String personEmail = "";

        CreateGroupInfoDTO createGroupInfoDTO = new CreateGroupInfoDTO();
        createGroupInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((createGroupInfoDTO));

        String expectedResolvedException = new IllegalArgumentException("The email is not valid.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals ("The email is not valid.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - invalid email - invalid format")
    void createGroupAndBecomeAdminInvalidEmailFormat() throws Exception {

        //Arrange
        String uri = "/groups";

        final String groupDescriptionStr = "SWitCH";
        final String personEmail = "morty@@gmail.com";

        CreateGroupInfoDTO createGroupInfoDTO = new CreateGroupInfoDTO();
        createGroupInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((createGroupInfoDTO));

        String expectedResolvedException = new IllegalArgumentException("The email is not valid.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals ("The email is not valid.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );

    }

    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - invalid groupDescription - null")
    void createGroupAndBecomeAdminGroupDescriptionNull() throws Exception {

        //Arrange
        String uri = "/groups";

        final String groupDescriptionStr = null;
        final String personEmail = "morty@gmail.com";

        CreateGroupInfoDTO createGroupInfoDTO = new CreateGroupInfoDTO();
        createGroupInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((createGroupInfoDTO));

        String expectedResolvedException = new IllegalArgumentException("The description can't be null or empty.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals ("The description can't be null or empty.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
                );

    }

    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - invalid groupDescription - Empty")
    void createGroupAndBecomeAdminGroupDescriptionEmpty() throws Exception {

        //Arrange
        String uri = "/groups";

        final String groupDescriptionStr = "";
        final String personEmail = "morty@gmail.com";

        CreateGroupInfoDTO createGroupInfoDTO = new CreateGroupInfoDTO();
        createGroupInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((createGroupInfoDTO));

        String expectedResolvedException = new IllegalArgumentException("The description can't be null or empty.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals ("The description can't be null or empty.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - invalid groupDescription - Empty")
    void createGroupAndBecomeAdminNullJsonInput() throws Exception {

        //Arrange
        String uri = "/groups";

        String inputJson = super.mapToJson((null));

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isBadRequest())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(400, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("400", result.getString("statusCode")),
                () -> assertEquals("BAD_REQUEST", result.getString("status")),
                () -> assertEquals ("The request body needed to perform the operation is missing.", result.getString("error")),
                () -> assertEquals ("Required request body is missing.", result.getString("message"))
        );

    }

    /*
     * Test if a groupDTO is returned given its description
     */


    @Test
    @DisplayName("Test if a groupDTO is returned given its description  - Happy Case")
    public void getGroupByDescription() throws Exception {

        //Arrange
        String uri = "/groups/Smith Family";

        String groupDescription = "SMITH FAMILY";

        String expectedLinks = "{\"Admins\":{\"href\":\"http:\\/\\/localhost\\/groups\\/SMITH%20FAMILY\\/admins\"}," +
                "\"Members\":{\"href\":\"http:\\/\\/localhost\\/groups\\/SMITH%20FAMILY\\/members\"}}";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(groupDescription.toUpperCase(),result.getString("groupDescription")),
                () -> assertEquals (expectedLinks, result.getString("_links"))
        );
    }

    @Test
    @DisplayName("Test if a groupDTO is returned given its description - Not Found")
    public void getGroupByDescriptionNotFound() throws Exception {

        //Arrange
        String uri = "/groups/SuicideSquad";

        String expectedResolvedException = new ArgumentNotFoundException("No group found with that ID.").toString();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("This resource was not found.", result.getString("error")),
                () -> assertEquals ("No group found with that ID.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }
}
