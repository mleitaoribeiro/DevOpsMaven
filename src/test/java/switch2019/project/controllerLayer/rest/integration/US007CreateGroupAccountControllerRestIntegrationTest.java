package switch2019.project.controllerLayer.rest.integration;

import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.deserializationDTO.CreateGroupAccountInfoDTO;
import switch2019.project.customExceptions.ArgumentNotFoundException;
import switch2019.project.customExceptions.NoPermissionException;
import switch2019.project.customExceptions.ResourceAlreadyExistsException;

import java.util.Objects;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US007CreateGroupAccountControllerRestIntegrationTest extends AbstractTest {


    @Override
    @BeforeEach
    public void setUP() {
        super.setUP();
    }

    @Test
    @DisplayName("Test Group Account creation - test if outputDTO and HTTP response are expected")
    void addGroupAccountMainScenario() throws Exception {

        //Arrange
        String uri = "/accounts";

        final String groupDescription = "Family Simpson";
        final String personEmail = "homer@hotmail.com";
        final String accountDenomination = "Kwik E Mart";
        final String accountDescription = "Duff Beer Expenses";


        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String expected = "{\"ownerID\":\"" + groupDescription.toUpperCase() +
                "\"" + "," + "\"denomination\":\"" + accountDenomination.toUpperCase() +
                "\"" + "," + "\"description\":\"" + accountDescription.toUpperCase() +
                "\",\"_links\":{\"self\":{\"href\":\"http://localhost/groups/FAMILY%20SIMPSON/accounts/KWIK%20E%20MART\"}}}";

        //Act
        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        RequestBuilder postRequest = MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson);

        ResultActions resultAction = mvc.perform(postRequest);
        MvcResult mvcResult = resultAction.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        int status = response.getStatus();
        String result = response.getContentAsString();

        //Assert

        Assertions.assertAll(
                () -> assertEquals(201, status),
                () -> assertEquals(expected, result)
        );


    }

    @Test
    @DisplayName("Test Group Account creation - person is not Admin")
    void addGroupAccountPersonIsNotAdmin() throws Exception {

        //Arrange
        String uri = "/accounts";

        final String groupDescription = "Smith Family";
        final String personEmail = "jerry.smith@gmail.com";
        final String accountDenomination = "House";
        final String accountDescription = "General Expenses";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"status\":\"FORBIDDEN\"," +
                "\"message\":\"No permission for this group operation.\"," +
                "\"errors\":[\"This person is not admin of this group.\"]}";

        String expectedResolvedException = "switch2019.project.customExceptions.NoPermissionException: This person is not admin of this group.";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isForbidden())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(403, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );

    }

    @Test
    @DisplayName("Test Group Account creation -  person does not exists on Person Repository")
    void addGroupAccountPersonDoesNotExists() throws Exception {

        //Arrange
        String uri = "/accounts";

        final String groupDescription = "Switch";
        final String personEmail = "raquel@hotmail.com";
        final String accountDenomination = "Gym";
        final String accountDescription = "Fitness Expenses";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

            //arrangement of the input:
        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"This resource was not found.\"," +
                "\"errors\":[\"No person found with that email.\"]}";

        String expectedResolvedException = "switch2019.project.customExceptions.ArgumentNotFoundException: No person found with that email.";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }


    @Test
    @DisplayName("Test Group Account creation -  group account already exists - test if output and HTTP response are expected ")
    void addGroupAccountGroupAccountAlreadyExistsException() throws Exception {

        //Arrange
        String uri = "/accounts";

        final String groupDescription = "Family Cardoso";
        final String personEmail = "1191780@isep.ipp.pt";
        final String accountDenomination = "Revolut";
        final String accountDescription = "Online Expenses";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"status\":\"CONFLICT\"," +
                "\"message\":\"This resource already exists.\"," +
                "\"errors\":[\"This account already exists.\"]}";

        String expectedResolvedException = new ResourceAlreadyExistsException("This account already exists.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isConflict())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(409, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Group Account creation -  group does not exists - test if output and HTTP response are expected ")
    void addGroupAccountTestGroupNotFoundException() throws Exception {

        //Arrange
        String uri = "/accounts";

        final String groupDescription = "West World";
        final String personEmail = "homer@hotmail.com";
        final String accountDenomination = "Clone AI from park";
        final String accountDescription = "Dolores several copies";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"This resource was not found.\"," +
                "\"errors\":[\"No group found with that description.\"]}";

        String expectedResolvedException = new ArgumentNotFoundException("No group found with that description.").toString();

        //Act:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Group Account creation - Email Null")
    void addGroupAccountNullEmail() throws Exception {
        //Arrange
        String uri = "/accounts";

        final String groupDescription = "Friends";
        final String personEmail = null;
        final String accountDenomination = "Summer Party";
        final String accountDescription = "Supermaket Continente";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The email can't be null.\"]}";

        String expectedResolvedException = new IllegalArgumentException("The email can't be null.").toString();

        //Act:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Group Account creation - Email Empty")
    void addGroupAccountEmailEmpty() throws Exception {
        //Arrange
        String uri = "/accounts";

        final String groupDescription = "Smith Family";
        final String personEmail = "";
        final String accountDenomination = "Gym";
        final String accountDescription = "Fitness";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The email is not valid.\"]}";

        String expectedResolvedException = new IllegalArgumentException("The email is not valid.").toString();

        //Act:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Group Account creation - Group ID Null")
    void addGroupAccountGroupIDNull() throws Exception {
        //Arrange
        String uri = "/accounts";

        final String groupDescription = null;
        final String personEmail = "beatriz.azevedo@gmail.com";
        final String accountDenomination = "Daily Vault";
        final String accountDescription = "Savings";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The description can't be null or empty.\"]}";

        String expectedResolvedException = new IllegalArgumentException("The description can't be null or empty.").toString();

        //Act:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Group Account creation - Group ID empty")
    void addGroupAccountGroupIDEmpty() throws Exception {
        //Arrange
        String uri = "/accounts";

        final String groupDescription = "";
        final String personEmail = "rick@gmail.com";
        final String accountDenomination = "Gym";
        final String accountDescription = "Fitness";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The description can't be null or empty.\"]}";

        String expectedResolvedException = new IllegalArgumentException("The description can't be null or empty.").toString();

        //Act:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Group Account creation - account denomination null")
    void addGroupAccountDenominationNull() throws Exception {
        //Arrange
        String uri = "/accounts";

        final String groupDescription = "SWITCH";
        final String personEmail = "1191755@isep.ipp.pt";
        final String accountDenomination = null;
        final String accountDescription = "Youtube Premium Subscription";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The denomination can't be null or empty.\"]}";

        String expectedResolvedException = new IllegalArgumentException("The denomination can't be null or empty.").toString();

        //Act:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );

    }

    @Test
    @DisplayName("Test Group Account creation - account denomination empty")
    void addGroupAccountDenominationEmpty() throws Exception {
        //Arrange
        String uri = "/accounts";

        final String groupDescription = "Smith Family";
        final String personEmail = "rick@gmail.com";
        final String accountDenomination = "";
        final String accountDescription = "Fitness";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The denomination can't be null or empty.\"]}";

        String expectedResolvedException = new IllegalArgumentException("The denomination can't be null or empty.").toString();

        //Act:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Group Account creation - account description null")
    void addGroupAccountDescriptionNull() throws Exception {
        //Arrange
        String uri = "/accounts";

        final String groupDescription = "SWITCH";
        final String personEmail = "1191762@isep.ipp.pt";
        final String accountDenomination = "Metro Porto";
        final String accountDescription = null;

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The description can't be null or empty.\"]}";

        String expectedResolvedException = new IllegalArgumentException("The description can't be null or empty.").toString();

        //Act:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );


    }

    @Test
    @DisplayName("Test Group Account creation - account description empty")
    void addGroupAccountDescriptionEmpty() throws Exception {
        //Arrange
        String uri = "/accounts";

        final String groupDescription = "Smith Family";
        final String personEmail = "rick@gmail.com";
        final String accountDenomination = "Fitness";
        final String accountDescription = "";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The description can't be null or empty.\"]}";

        String expectedResolvedException = new IllegalArgumentException("The description can't be null or empty.").toString();

        //Act:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    /**
     * Test if an Account can be found by the ID
     */

    @Test
    @DisplayName("Test if an Account can be found by the ID - Happy Case")
    void getAccountByGroupID() throws Exception {

        //ARRANGE:
        String uri = "/groups/FAMILY CARDOSO/accounts/REVOLUT";

        String expected = "{\"ownerID\":\"FAMILY CARDOSO\"," +
                "\"denomination\":\"REVOLUT\"," +
                "\"description\":\"ONLINE EXPENSES\"}";

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expected, result)
        );

    }

}
