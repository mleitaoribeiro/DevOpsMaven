package switch2019.project.controllerLayer.rest.integration;

import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.deserializationDTO.CreateGroupAccountInfoDTO;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.NoPermissionException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        String uri = "/groups/Family Simpson/accounts";

        final String personEmail = "homer@hotmail.com";
        final String accountDenomination = "Kwik E Mart";
        final String accountDescription = "Duff Beer Expenses";


        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String expectedResult = "{\"ownerID\":\"FAMILY SIMPSON\"" +
                ","+  "\"denomination\":\"" + accountDenomination.toUpperCase() +
                "\"" + "," + "\"description\":\"" + accountDescription.toUpperCase() +
                "\",\"_links\":{\"self\":[{\"href\":\"http://localhost/groups/FAMILY%20SIMPSON/accounts/KWIK%20E%20MART\"}," +
                "{\"href\":\"http://localhost/groups/FAMILY%20SIMPSON/accounts\"}]}}";

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isCreated())
                .andExpect(content().string(expectedResult))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        //Assert

        Assertions.assertAll(
                () -> assertEquals(201, status),
                () -> assertEquals(expectedResult, result)
        );
    }

    @Test
    @DisplayName("Test Group Account creation - person is not Admin")
    void addGroupAccountPersonIsNotAdmin() throws Exception {

        //Arrange
        String uri = "/groups/Smith Family/accounts";

        final String personEmail = "jerry.smith@gmail.com";
        final String accountDenomination = "House";
        final String accountDescription = "General Expenses";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":403,\"status\":\"FORBIDDEN\"," +
                "\"error\":\"No permission for this operation.\"," +
                "\"message\":\"This person is not admin of this group.\"}";

        String expectedResolvedException = new NoPermissionException ("This person is not admin of this group.").toString();

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
    @DisplayName("Test Group Account creation -  group account already exists - test if output and HTTP response are expected ")
    void addGroupAccountGroupAccountAlreadyExistsException() throws Exception {

        //Arrange
        String uri = "/groups/Family Cardoso/accounts";

        final String personEmail = "1191780@isep.ipp.pt";
        final String accountDenomination = "Revolut";
        final String accountDescription = "Online Expenses";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":409,\"status\":\"CONFLICT\"," +
                "\"error\":\"This resource already exists.\"," +
                "\"message\":\"This account already exists.\"}";


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
    @DisplayName("Test Group Account creation -  person does not exists on Person Repository")
    void addGroupAccountPersonDoesNotExists() throws Exception {

        //Arrange
        String uri = "/groups/Switch/accounts";

        final String personEmail = "raquel@hotmail.com";
        final String accountDenomination = "Gym";
        final String accountDescription = "Fitness Expenses";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

            //arrangement of the input:
        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"This resource was not found.\"," +
                "\"message\":\"No person found with that email.\"}";


        String expectedResolvedException = new ArgumentNotFoundException("No person found with that email.").toString();

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
    @DisplayName("Test Group Account creation - null input")
    void createGroupAndBecomeAdminNullJsonInput() throws Exception {

        //Arrange
        String uri = "/groups/marge@hotmail.com/accounts";

        String inputJson = super.mapToJson((null));

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":400," +
                "\"status\":\"BAD_REQUEST\"," +
                "\"error\":\"The request body needed to perform the operation is missing.\"," +
                "\"message\":\"Required request body is missing.\"}";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        //Assert
        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(400, status),
                () -> assertEquals(expectedErrorMessage, result)
        );

    }

    @Test
    @DisplayName("Test Group Account creation - Invalid POST Method")
    void createGroupAndBecomeAdminInvalidURI() throws Exception {

        //Arrange
        String uri = "/groups//accounts";

        //arrangement of the account DTO:
        final String accountDenomination = "Food Expenses";
        final String accountDescription = "Money spent on food";

        //setting information for the DTO:
        CreateGroupAccountInfoDTO infoDTO = new CreateGroupAccountInfoDTO();
        infoDTO.setAccountDenomination(accountDenomination);
        infoDTO.setAccountDescription(accountDescription);

        //arrangement of the input:
        String inputJson = super.mapToJson((infoDTO));

        String expectedResolvedException = new HttpRequestMethodNotSupportedException("POST").toString();

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":405," +
                "\"status\":\"METHOD_NOT_ALLOWED\"," +
                "\"error\":\"Request method 'POST' not supported\"," +
                "\"message\":\"POST method is not supported for this request. Supported methods are GET \"}";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        //Assert
        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(405, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );

    }

    @Test
    @DisplayName("Test Group Account creation - URI Invalid")
    void createGroupAndBecomeAdminInvalidURIInput() throws Exception {

        //Arrange
        String uri = "/g/marge@hotmail.com/accounts";

        //arrangement of the account DTO:
        final String accountDenomination = "Food Expenses";
        final String accountDescription = "Money spent on food";

        //setting information for the DTO:
        CreateGroupAccountInfoDTO infoDTO = new CreateGroupAccountInfoDTO();
        infoDTO.setAccountDenomination(accountDenomination);
        infoDTO.setAccountDescription(accountDescription);

        //arrangement of the input:
        String inputJson = super.mapToJson((infoDTO));

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn();

        //Assert
        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(404, status),
                () -> assertEquals("", result)
        );

    }

    @Test
    @DisplayName("Test Group Account creation - Invalid MediaType")
    void createGroupAndBecomeAdminInvalidMediaType() throws Exception {

        //Arrange
        String uri = "/groups/Family Simpson/accounts";

        //arrangement of the account DTO:
        final String accountDenomination = "Food Expenses";
        final String accountDescription = "Money spent on food";

        //setting information for the DTO:
        CreateGroupAccountInfoDTO infoDTO = new CreateGroupAccountInfoDTO();
        infoDTO.setAccountDenomination(accountDenomination);
        infoDTO.setAccountDescription(accountDescription);

        //arrangement of the input:
        String inputJson = super.mapToJson((infoDTO));

        String expectedResolvedException = new HttpMediaTypeNotSupportedException("Content type 'application/xml' not supported").toString();

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":415," +
                "\"status\":\"UNSUPPORTED_MEDIA_TYPE\"," +
                "\"error\":\"Content type 'application/xml' not supported\"," +
                "\"message\":\"application/xml media type is not supported.\"}";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .content(inputJson))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        //Assert
        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(415, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );

    }

///////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("Test Group Account creation -  group does not exists - test if output and HTTP response are expected ")
    void addGroupAccountTestGroupNotFoundException() throws Exception {

        //Arrange
        String uri = "/groups/West World/accounts";

        final String personEmail = "homer@hotmail.com";
        final String accountDenomination = "Clone AI from park";
        final String accountDescription = "Dolores several copies";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"This resource was not found.\"," +
                "\"message\":\"No group found with that description.\"}";


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

        //Assert:

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
        String uri = "/groups/Friends/accounts";

        final String personEmail = null;
        final String accountDenomination = "Summer Party";
        final String accountDescription = "Supermaket Continente";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"One of the parameters is invalid or is missing.\"," +
                "\"message\":\"The email can't be null.\"}";


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

        //Assert:

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
        String uri = "/groups/Smith Family/accounts";

        final String personEmail = "";
        final String accountDenomination = "Gym";
        final String accountDescription = "Fitness";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"One of the parameters is invalid or is missing.\"," +
                "\"message\":\"The email is not valid.\"}";


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

        //Assert:

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
        String uri = "/groups/Switch/accounts";

        final String personEmail = "1191755@isep.ipp.pt";
        final String accountDenomination = null;
        final String accountDescription = "Youtube Premium Subscription";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"One of the parameters is invalid or is missing.\"," +
                "\"message\":\"The denomination can't be null or empty.\"}";


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

        //Assert:

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
        String uri = "/groups/Smith Family/accounts";

        final String personEmail = "rick@gmail.com";
        final String accountDenomination = "";
        final String accountDescription = "Fitness";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"One of the parameters is invalid or is missing.\"," +
                "\"message\":\"The denomination can't be null or empty.\"}";



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

        //Assert:

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
        String uri = "/groups/Switch/accounts";

        final String personEmail = "1191762@isep.ipp.pt";
        final String accountDenomination = "Metro Porto";
        final String accountDescription = null;

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"One of the parameters is invalid or is missing.\"," +
                "\"message\":\"The description can't be null or empty.\"}";


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

        //Assert:

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
        String uri = "/groups/Smith Family/accounts";

        final String personEmail = "rick@gmail.com";
        final String accountDenomination = "Fitness";
        final String accountDescription = "";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"One of the parameters is invalid or is missing.\"," +
                "\"message\":\"The description can't be null or empty.\"}";


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

        //Assert:

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

    /**
     * Test to get all accounts associated with a GroupID
     */

    @Test
    @DisplayName("Test getting all the accounts associated with a GroupID - Happy Case")
    void getAccountsByGroupIDHappyCase() throws Exception {

        //ARRANGE:
        //Arrange the uri used to consult all the accounts associated with a group:
        String uri = "/groups/Rick and Morty/accounts";

        //Arrange the expected string that will be returned:
        String expected = "[{\"ownerID\":\"RICK AND MORTY\"," +
                "\"denomination\":\"FUEL\"," + "\"description\":\"SHIP FUEL STATION\"," +
                "\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/groups/RICK%20AND%20MORTY/accounts/FUEL\"}]}," +
                "{\"ownerID\":\"RICK AND MORTY\"," +
                "\"denomination\":\"ALCOHOL\"," + "\"description\":\"IMPORTANT FOR ADVENTURES\"," +
                "\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/groups/RICK%20AND%20MORTY/accounts/ALCOHOL\"}]}," +
                "{\"ownerID\":\"RICK AND MORTY\"," +
                "\"denomination\":\"MONEY FOR MORTY\"," + "\"description\":\"MONEY TO COMPENSATE MORTY\"," +
                "\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/groups/RICK%20AND%20MORTY/accounts/MONEY%20FOR%20MORTY\"}]}]";

        //ACT:
        //Getting the MvcResult:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Actual status that is returned
        int status = mvcResult.getResponse().getStatus();

        //Actual string that is returned:
        String result = mvcResult.getResponse().getContentAsString();

        //ASSERT: (assert if the status is the expected (200 = OK), and assert if the returned String is equal to the expected one:
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expected, result)
        );
    }

    @Test
    @DisplayName("Test getting all the accounts associated with a GroupID - Different String")
    void getAccountsByGroupIDFailCase() throws Exception {

        //ARRANGE:
        //Arrange the uri used to consult all the accounts associated with a group:
        String uri = "/groups/Rick and Morty/accounts";

        //Arrange the expected string that will be returned:
        String expected = "[{\"ownerID\":\"RICK AND MORTY\"," +
                "\"denomination\":\"FUEL\"," + "\"description\":\"SHIP FUEL STATION\"," +
                "\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/groups/RICK%20AND%20MORTY/accounts/FUEL\"}]}," +
                "{\"ownerID\":\"RICK AND MORTY\"," +
                "\"denomination\":\"ALCOHOL\"," + "\"description\":\"IMPORTANT FOR ADVENTURES\"," +
                "\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/groups/RICK%20AND%20MORTY/accounts/ALCOHOL\"}]}]";

        //ACT:
        //Getting the MvcResult:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Actual string that is returned:
        String result = mvcResult.getResponse().getContentAsString();

        //ASSERT: (assert if the status is the expected (200 = OK), and assert if the returned String is equal to the expected one:
        assertFalse(expected == result);
    }
}
