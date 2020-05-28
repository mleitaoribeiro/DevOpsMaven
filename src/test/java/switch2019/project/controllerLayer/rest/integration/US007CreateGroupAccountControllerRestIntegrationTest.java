package switch2019.project.controllerLayer.rest.integration;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class US007CreateGroupAccountControllerRestIntegrationTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP() {
        super.setUP();
    }

    @Test
    @DisplayName("Test Group Account creation - test if outputDTO, HTTP response are expected. Test if account was persisted in Db")
    void addGroupAccountMainScenario() throws Exception {
        //GET - Before account is created
        String uriGet = "/groups/Family Simpson/accounts/Kwik E Mart";

        MvcResult mvcResultGetBefore = mvc.perform(MockMvcRequestBuilders.get(uriGet)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int statusBefore = mvcResultGetBefore.getResponse().getStatus();
        JSONObject getBefore = new JSONObject(mvcResultGetBefore.getResponse().getContentAsString());


        //POST - Create new account
        String uriPost = "/groups/Family Simpson/accounts";

        //Create input DTO
        final String groupDescription = "Family Simpson";
        final String personEmail = "homer@hotmail.com";
        final String accountDenomination = "Kwik E Mart";
        final String accountDescription = "Duff Beer Expenses";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        //Serialize input Json
        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        //Expected Links
        String expectedLinks = "{\"self\":{\"href\":\"http:\\/\\/localhost\\/groups\\/FAMILY%20SIMPSON\\/accounts\\/KWIK%20E%20MART\"}," +
                "\"accounts\":{\"href\":\"http:\\/\\/localhost\\/groups\\/FAMILY%20SIMPSON\\/accounts\"}}";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isCreated())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        //Get - After account is created
        MvcResult mvcResultGetAfter = mvc.perform(MockMvcRequestBuilders.get(uriGet)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int statusAfter = mvcResultGetAfter.getResponse().getStatus();
        JSONObject getAfter = new JSONObject(mvcResultGetAfter.getResponse().getContentAsString());

        //Assert
        Assertions.assertAll(
                //Get before account is created
                () -> assertEquals(422, statusBefore),
                () -> assertEquals("No account found with that ID.", getBefore.getString("message")),
                //Create new account
                () -> assertEquals(201, status),
                () -> assertEquals(groupDescription.toUpperCase(),result.getString("ownerID")),
                () -> assertEquals(accountDenomination.toUpperCase(), result.getString("denomination")),
                () -> assertEquals(accountDescription.toUpperCase(), result.getString("description")),
                () -> assertEquals (expectedLinks, result.getString("_links")),
                // Get after account is created
                () -> assertEquals(200, statusAfter),
                () -> assertEquals(groupDescription.toUpperCase(),getAfter.getString("ownerID")),
                () -> assertEquals(accountDenomination.toUpperCase(), getAfter.getString("denomination")),
                () -> assertEquals(accountDescription.toUpperCase(), getAfter.getString("description"))
        );
    }

    @Test
    @DisplayName("Test Group Account creation - person is not Admin")
    void addGroupAccountPersonIsNotAdmin() throws Exception {

        //ARRANGE
        String uri = "/groups/Smith Family/accounts";

        final String personEmail = "jerry.smith@gmail.com";
        final String accountDenomination = "House";
        final String accountDescription = "General Expenses";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedResolvedException = new NoPermissionException ("This person is not admin of this group.").toString();

        //ACT
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isForbidden())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT
        Assertions.assertAll(
                () -> assertEquals(403, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("403", result.getString("statusCode")),
                () -> assertEquals("FORBIDDEN", result.getString("status")),
                () -> assertEquals ("No permission for this operation.", result.getString("error")),
                () -> assertEquals ("This person is not admin of this group.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Group Account creation -  group account already exists - test if output and HTTP response are expected ")
    void addGroupAccountGroupAccountAlreadyExistsException() throws Exception {

        //ARRANGE
        String uri = "/groups/Family Cardoso/accounts";

        final String personEmail = "1191780@isep.ipp.pt";
        final String accountDenomination = "Revolut";
        final String accountDescription = "Online Expenses";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        String expectedResolvedException = new ResourceAlreadyExistsException("This account already exists.").toString();

        //ACT
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isConflict())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT
        Assertions.assertAll(
                () -> assertEquals(409, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("409", result.getString("statusCode")),
                () -> assertEquals("CONFLICT", result.getString("status")),
                () -> assertEquals ("This resource already exists.", result.getString("error")),
                () -> assertEquals ("This account already exists.", result.getString("message")),
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

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

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

    @Test
    @DisplayName("Test Group Account creation - null input")
    void createGroupAndBecomeAdminNullJsonInput() throws Exception {

        //Arrange
        String uri = "/groups/marge@hotmail.com/accounts";

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

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isMethodNotAllowed())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT
        Assertions.assertAll(
                () -> assertEquals(405, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("405", result.getString("statusCode")),
                () -> assertEquals("METHOD_NOT_ALLOWED", result.getString("status")),
                () -> assertEquals ("Request method 'POST' not supported", result.getString("error")),
                () -> assertEquals ("POST method is not supported for this request. Supported methods are GET ", result.getString("message")),
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

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT
        Assertions.assertAll(
                () -> assertEquals(415, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("415", result.getString("statusCode")),
                () -> assertEquals("UNSUPPORTED_MEDIA_TYPE", result.getString("status")),
                () -> assertEquals ("Content type 'application/xml' not supported", result.getString("error")),
                () -> assertEquals ("application/xml media type is not supported.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );

    }


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

        String expectedResolvedException = new ArgumentNotFoundException("No group found with that description.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("This resource was not found.", result.getString("error")),
                () -> assertEquals ("No group found with that description.", result.getString("message")),
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

        //ASSERT
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

        //ASSERT
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

        String expectedResolvedException = new IllegalArgumentException("The denomination can't be null or empty.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals ("The denomination can't be null or empty.", result.getString("message")),
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

        String expectedResolvedException = new IllegalArgumentException("The denomination can't be null or empty.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals ("The denomination can't be null or empty.", result.getString("message")),
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

        //ASSERT
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

        //ASSERT
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

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals("FAMILY CARDOSO",result.getString("ownerID")),
                () -> assertEquals("REVOLUT", result.getString("denomination")),
                () -> assertEquals("ONLINE EXPENSES", result.getString("description"))
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

        //Arrange the expected links that will be returned:
        String expectedLink1 = "[{\"rel\":\"self\",\"href\":\"http:\\/\\/localhost\\/groups\\/RICK%20AND%20MORTY\\/accounts\\/FUEL\"}]";
        String expectedLink2 = "[{\"rel\":\"self\",\"href\":\"http:\\/\\/localhost\\/groups\\/RICK%20AND%20MORTY\\/accounts\\/ALCOHOL\"}]";
        String expectedLink3 = "[{\"rel\":\"self\",\"href\":\"http:\\/\\/localhost\\/groups\\/RICK%20AND%20MORTY\\/accounts\\/MONEY%20FOR%20MORTY\"}]";

        //ACT:
        //Getting the MvcResult:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Actual status that is returned
        int status = mvcResult.getResponse().getStatus();

        //Actual string that is returned:
        String result = mvcResult.getResponse().getContentAsString();

        JSONArray jArray = (JSONArray) new JSONTokener(result).nextValue();

        //ASSERT: (assert if the status is the expected (200 = OK), and assert if the returned String is equal to the expected one:
        Assertions.assertAll(
                () -> assertEquals(200, status),

                () -> assertEquals("RICK AND MORTY",jArray.getJSONObject(0).getString("ownerID")),
                () -> assertEquals("FUEL",jArray.getJSONObject(0).getString("denomination")),
                () -> assertEquals("SHIP FUEL STATION",jArray.getJSONObject(0).getString("description")),
                () -> assertEquals(expectedLink1, jArray.getJSONObject(0).getString("links")),

                () -> assertEquals("RICK AND MORTY",jArray.getJSONObject(1).getString("ownerID")),
                () -> assertEquals("ALCOHOL",jArray.getJSONObject(1).getString("denomination")),
                () -> assertEquals("IMPORTANT FOR ADVENTURES",jArray.getJSONObject(1).getString("description")),
                () -> assertEquals(expectedLink2, jArray.getJSONObject(1).getString("links")),

                () -> assertEquals("RICK AND MORTY",jArray.getJSONObject(2).getString("ownerID")),
                () -> assertEquals("MONEY FOR MORTY",jArray.getJSONObject(2).getString("denomination")),
                () -> assertEquals("MONEY TO COMPENSATE MORTY",jArray.getJSONObject(2).getString("description")),
                () -> assertEquals(expectedLink3, jArray.getJSONObject(2).getString("links"))
        );

    }

    @Test
    @DisplayName("Test getting all the accounts associated with a GroupID - Different String")
    void getAccountsByGroupIDFailCase() throws Exception {

        //ARRANGE
        //Arrange the uri used to consult all the accounts associated with a group:
        String uri = "/groups/Rick and Morty/accounts";

        //Arrange the expected string that will be returned:
        String expected = "[{\"ownerID\":\"RICK AND MORTY\"," +
                "\"denomination\":\"FUEL\"," + "\"description\":\"SHIP FUEL STATION\"," +
                "\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/groups/RICK%20AND%20MORTY/accounts/FUEL\"}]}," +
                "{\"ownerID\":\"RICK AND MORTY\"," +
                "\"denomination\":\"ALCOHOL\"," + "\"description\":\"IMPORTANT FOR ADVENTURES\"," +
                "\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/groups/RICK%20AND%20MORTY/accounts/ALCOHOL\"}]}]";

        //ACT
        //Getting the MvcResult:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Actual string that is returned:
        String result = mvcResult.getResponse().getContentAsString();

        //ASSERT
        assertNotSame(expected, result);
    }
}
