package switch2019.project.controllerLayer.rest.integration;

import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.deserializationDTO.CreateGroupInfoDTO;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

        final String groupDescriptionStr = "Online Shopping";
        final String personEmail = "1110120@isep.ipp.pt";

        CreateGroupInfoDTO createGroupInfoDTO = new CreateGroupInfoDTO ();
        createGroupInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((createGroupInfoDTO));

        String expected = "{\"groupDescription\":\"" +groupDescriptionStr.toUpperCase() +
                "\",\"_links\":{\"self\":{\"href\":\"http://localhost/groups/ONLINE%20SHOPPING\"}}}";

       //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isCreated())
                .andExpect(content().string(expected))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(201, status),
                () -> assertEquals(expected, result)
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

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"This resource was not found.\"," +
                "\"message\":\"No person found with that email.\"}";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        //Assert

        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result)
        );
    }


    @Test
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

        String expectedErrorMessage =  "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":409,\"status\":\"CONFLICT\"," +
                "\"error\":\"This resource already exists.\"," +
                "\"message\":\"This group description already exists.\"}";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isConflict())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();


        //Assert
        Assertions.assertAll(
                () -> assertEquals(409, status),
                () -> assertEquals(expectedErrorMessage, result)
        );
    }


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

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"One of the parameters is invalid or is missing.\"," +
                "\"message\":\"The email can't be null.\"}";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result)
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

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"One of the parameters is invalid or is missing.\"," +
                "\"message\":\"The email is not valid.\"}";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result)
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

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"One of the parameters is invalid or is missing.\"," +
                "\"message\":\"The email is not valid.\"}";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        //Assert

        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result)
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

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"One of the parameters is invalid or is missing.\"," +
                "\"message\":\"The description can't be null or empty.\"}";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result)
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

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"One of the parameters is invalid or is missing.\"," +
                "\"message\":\"The description can't be null or empty.\"}";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result)
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
                .andExpect(content().string(""))
                .andReturn();

        //Assert
        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(400, status),
                () -> assertEquals("", result)
        );

    }

    /**
     * Test if a groupDTO is returned given its description
     */

    @Test
    @DisplayName("Test if a groupDTO is returned given its description  Hapyy Case")
    public void getGroupByDescription() throws Exception {

        //Arrange
        String uri = "/groups/Smith Family";

        String groupDescription = "SMITH FAMILY";

        String expectedJson = "{\"groupDescription\":\"" + groupDescription + "\"}";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String resultJson = mvcResult.getResponse().getContentAsString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expectedJson, resultJson)
        );
    }

    @Test
    @DisplayName("Test if a groupDTO is returned given its description - Not Found")
    public void getGroupByDescriptionNotFound() throws Exception {

        //Arrange
        String uri = "/groups/SuicideSquad";

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"This resource was not found.\"," +
                "\"message\":\"No group found with that description.\"}";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result)

        );
    }
}