package switch2019.project.controllerLayer.rest.integration;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.deserializationDTO.AddMemberInfoDTO;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.NoPermissionException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US003AddMemberToGroupControllerRestIntegrationTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP() {
        super.setUP();
    }

    @Test
    @DisplayName("Test if a person is added to a Group - Main Scenario")
    void addMemberToGroup() throws Exception {

        // Arrange
        String personEmail = "rick@gmail.com";
        String groupDescription = "switch";

        String uri = "/groups/" + groupDescription + "/members";

        AddMemberInfoDTO addMemberInfoDTO = new AddMemberInfoDTO();
        addMemberInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson(addMemberInfoDTO);

        String expected = personEmail + " was added to group " + groupDescription;
        String link = "/groups/" + groupDescription + "/members/" + personEmail;

        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isCreated())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        // Assert
        Assertions.assertAll(
                () -> assertEquals(201, status),
                () -> assertEquals(expected, result.getString("memberAdded")),
                () -> assertEquals(link, result.getString("_links").substring(35)
                        .replace("\"}}", "")
                        .replace("\\", ""))
        );
    }

    @Test
    @DisplayName("Test if a person is added to a Group - Person is already in the Group")
    void addMemberToGroupAlreadyIn() throws Exception {

        // Arrange
        String personEmail = "1191743@isep.ipp.pt";
        String groupDescription = "switch";

        String uri = "/groups/" + groupDescription + "/members";

        AddMemberInfoDTO addMemberInfoDTO = new AddMemberInfoDTO();
        addMemberInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson(addMemberInfoDTO);

        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isConflict())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String expectedException = new ResourceAlreadyExistsException("1191743@isep.ipp.pt is already on group switch").toString();
        String realException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        // Assert
        Assertions.assertAll(
                () -> assertEquals(409, status),
                () -> assertEquals("409", result.getString("statusCode")),
                () -> assertEquals("CONFLICT", result.getString("status")),
                () -> assertEquals("This resource already exists.", result.getString("error")),
                () -> assertEquals("1191743@isep.ipp.pt is already on group switch", result.getString("message")),
                () -> assertEquals(expectedException, realException)
        );
    }

    @Test
    @DisplayName("Test if a person is added to a Group - Person doesn't exist on Repository")
    void addMemberToGroupNotExist() throws Exception {

        // Act
        String personEmail = "404unkown@gmail.com";
        String groupDescription = "switch";

        String uri = "/groups/" + groupDescription + "/members";

        AddMemberInfoDTO addMemberInfoDTO = new AddMemberInfoDTO();
        addMemberInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((addMemberInfoDTO));

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String expectedException = new ArgumentNotFoundException("No person found with that email.").toString();
        String realException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("This resource was not found.", result.getString("error")),
                () -> assertEquals("No person found with that email.", result.getString("message")),
                () -> assertEquals(expectedException, realException)
        );
    }

    @Test
    @DisplayName("Test if a person is added to a Group - Group doesn't exist on Group Repository")
    void addMemberToGroupThatDoesNotExist() throws Exception {

        // Arrange
        String personEmail = "rick@gmail.com";
        String groupDescription = "amigos";

        String uri = "/groups/" + groupDescription + "/members";

        AddMemberInfoDTO addMemberInfoDTO = new AddMemberInfoDTO();
        addMemberInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson(addMemberInfoDTO);

        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String expectedException = new ArgumentNotFoundException("No group found with that description.").toString();
        String realException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        // Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("This resource was not found.", result.getString("error")),
                () -> assertEquals("No group found with that description.", result.getString("message")),
                () -> assertEquals(expectedException, realException)
        );
    }

    @Test
    @DisplayName("Test if a person is added to a Group - PersonEmail is null")
    void addMemberToGroupNullEmail() throws Exception {

        // Arrange
        String personEmail = null;
        String groupDescription = "switch";

        String uri = "/groups/" + groupDescription + "/members";

        AddMemberInfoDTO addMemberInfoDTO = new AddMemberInfoDTO();
        addMemberInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson(addMemberInfoDTO);

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String expectedException = new IllegalArgumentException("The email can't be null.").toString();
        String realException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        // Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals("The email can't be null.", result.getString("message")),
                () -> assertEquals(expectedException, realException)
        );
    }

    @Test
    @DisplayName("Test for get Group Member - Main Scenario")
    void getPersonByIDHappyCase() throws Exception {
        // Arrange
        String personEmail = "1191778@isep.ipp.pt";
        String groupDescription = "switch";

        String uri = "/groups/" + groupDescription + "/members/" + personEmail;

        String expected = "1191778@isep.ipp.pt";

        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        // Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expected, result.getString("personID"))
        );
    }

    @Test
    @DisplayName("Test for get Group Member - Exception - Person is not a Member of the Group")
    void getPersonByID() throws Exception {

        // Arrange
        String personEmail = "rick@gmail.com";
        String groupDescription = "switch";

        String uri = "/groups/" + groupDescription + "/members/" + personEmail;

        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String expectedException = new NoPermissionException("This person is not member of this group.").toString();
        String realException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        // Assert
        Assertions.assertAll(
                () -> assertEquals(403, status),
                () -> assertEquals("403", result.getString("statusCode")),
                () -> assertEquals("FORBIDDEN", result.getString("status")),
                () -> assertEquals("No permission for this operation.", result.getString("error")),
                () -> assertEquals("This person is not member of this group.", result.getString("message")),
                () -> assertEquals(expectedException, realException)
        );
    }

    @Test
    @DisplayName("Test for getMembersByGroupDescription - Main Scenario")
    void getMembersByGroupDescription() throws Exception {

        // Arrange
        String groupDescription = "Rick And Morty";
        String uri = "/groups/" + groupDescription + "/members/";

        String mortyID = "morty@gmail.com";
        String rickID = "rick@gmail.com";

        String mortyLink = "groups/Rick%20And%20Morty/members/" + mortyID;
        String rickLink = "groups/Rick%20And%20Morty/members/" + rickID;

        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        JSONArray jArray = (JSONArray) new JSONTokener(result).nextValue();

        // Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),

                () -> assertEquals(mortyID,jArray.getJSONObject(0).getString("personID")),
                () -> assertEquals(mortyLink, jArray.getJSONObject(0).getString("links")
                        .replace("[{\"rel\":\"self\",\"href\":\"http:\\/\\/localhost\\/", "")
                        .replace("\"}}", "")
                        .replace("\\", "")
                        .replace("\"}]", "")),

                () -> assertEquals(rickID,jArray.getJSONObject(1).getString("personID")),
                () -> assertEquals(rickLink, jArray.getJSONObject(1).getString("links")
                        .replace("[{\"rel\":\"self\",\"href\":\"http:\\/\\/localhost\\/", "")
                        .replace("\"}}", "")
                        .replace("\\", "")
                        .replace("\"}]", ""))
        );
    }

    @Test
    @DisplayName("Test for getMembersByGroupDescription - Exception - No Group found with that Description")
    void getMembersByGroupDescriptionException() throws Exception {

        //Status Request
        String groupDescription = "High School buddies";
        String uri = "/groups/" + groupDescription + "/members/";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String expectedException = new ArgumentNotFoundException("No group found with that description.").toString();
        String realException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        // Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("This resource was not found.", result.getString("error")),
                () -> assertEquals("No group found with that description.", result.getString("message")),
                () -> assertEquals(expectedException, realException)
        );
    }

    @Test
    @DisplayName("Test for getAdminsByGroupDescription - Main Scenario")
    void getAdminsByGroupDescription() throws Exception {

        // Arrange
        String groupDescription = "Rick And Morty";
        String uri = "/groups/" + groupDescription + "/admins/";

        String rickID = "rick@gmail.com";

        String rickLink = "groups/Rick%20And%20Morty/members/" + rickID;

        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        JSONArray jArray = (JSONArray) new JSONTokener(result).nextValue();

        // Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),

                () -> assertEquals(rickID,jArray.getJSONObject(0).getString("personID")),
                () -> assertEquals(rickLink, jArray.getJSONObject(0).getString("links")
                        .replace("[{\"rel\":\"self\",\"href\":\"http:\\/\\/localhost\\/", "")
                        .replace("\"}}", "")
                        .replace("\\", "")
                        .replace("\"}]", ""))
        );
    }

    @Test
    @DisplayName("Test for getAdminsByGroupDescription - Exception - No Group found with that Description")
    void getAdminsByGroupDescriptionException() throws Exception {

        //Status Request
        String groupDescription = "High School buddies";
        String uri = "/groups/" + groupDescription + "/admins/";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String expectedException = new ArgumentNotFoundException("No group found with that description.").toString();
        String realException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        // Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("This resource was not found.", result.getString("error")),
                () -> assertEquals("No group found with that description.", result.getString("message")),
                () -> assertEquals(expectedException, realException)
        );
    }
}