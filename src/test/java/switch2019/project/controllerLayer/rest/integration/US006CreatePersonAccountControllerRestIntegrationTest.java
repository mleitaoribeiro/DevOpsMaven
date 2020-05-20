package switch2019.project.controllerLayer.rest.integration;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.deserializationDTO.CreatePersonAccountInfoDTO;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US006CreatePersonAccountControllerRestIntegrationTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP() {
        super.setUP();
    }

    @DisplayName("Test If User Account is created - Main Scenario") 
    @Test
    void createPersonAccountHappyCase() throws Exception {

        // ARRANGE
        // URI used by the POST
        String uriPost = "/persons/marge@hotmail.com/accounts";

        // URI used by the GET
        String uriGet = "/persons/marge@hotmail.com/accounts/FOOD EXPENSES";

        final String personEmail = "marge@hotmail.com";
        final String accountDenomination = "Food Expenses";
        final String accountDescription = "Money spent on food";

        CreatePersonAccountInfoDTO infoDTO = new CreatePersonAccountInfoDTO();
        infoDTO.setAccountDenomination(accountDenomination);
        infoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson(infoDTO);

        String expectedLinks = "{\"self\":[" +
                "{\"href\":\"http:\\/\\/localhost\\/persons\\/MARGE@HOTMAIL.COM\\/accounts\\/FOOD%20EXPENSES\"}," +
                "{\"href\":\"http:\\/\\/localhost\\/persons\\/MARGE@HOTMAIL.COM\\/accounts\"}]}";

        // Get before the post
        MvcResult mvcResultGetBefore = mvc.perform(MockMvcRequestBuilders.get(uriGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int statusGetBefore = mvcResultGetBefore.getResponse().getStatus();
        JSONObject getBefore = new JSONObject(mvcResultGetBefore.getResponse().getContentAsString());

        // ACT
        MvcResult mvcResultPost = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isCreated())
                .andReturn();

        int status = mvcResultPost.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResultPost.getResponse().getContentAsString());

        // Get after the post
        MvcResult mvcResultGetAfter = mvc.perform(MockMvcRequestBuilders.get(uriGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int statusGetAfter = mvcResultGetAfter.getResponse().getStatus();
        System.out.println("status: " + statusGetAfter);
        JSONObject getAfter = new JSONObject(mvcResultGetAfter.getResponse().getContentAsString());

        // ASSERT
        // Assert Get before Post
        Assertions.assertAll(
                () -> assertEquals(422, statusGetBefore),
                () -> assertEquals ("No account found with that ID.", getBefore.getString("message"))
        );

        // Assert Post
        Assertions.assertAll(
                () -> assertEquals(201, status),
                () -> assertEquals(personEmail.toUpperCase(),result.getString("ownerID")),
                () -> assertEquals(accountDenomination.toUpperCase(), result.getString("denomination")),
                () -> assertEquals(accountDescription.toUpperCase(), result.getString("description")),
                () -> assertEquals(expectedLinks, result.getString("_links"))
        );

        // Assert Get after Post
        Assertions.assertAll(
                () -> assertEquals(200, statusGetAfter),
                () -> assertEquals(personEmail.toUpperCase(),getAfter.getString("ownerID")),
                () -> assertEquals(accountDenomination.toUpperCase(), getAfter.getString("denomination")),
                () -> assertEquals(accountDescription.toUpperCase(), getAfter.getString("description"))
        );
    }

    @DisplayName("Test If User Account was created - Account already on Repository")
    @Test
    void testIfUserAccountWasCreatedAccountAlreadyExists() throws Exception {
        //ARRANGE:
        //URI used to call the controller:
        String uri = "/persons/marge@hotmail.com/accounts";

        //arrangement of the account DTO:
        final String accountDenomination = "Homer Snacks";
        final String accountDescription = "Money spent on snacks for homer";

        //setting information for the DTO:
        CreatePersonAccountInfoDTO infoDTO = new CreatePersonAccountInfoDTO();
        infoDTO.setAccountDenomination(accountDenomination);
        infoDTO.setAccountDescription(accountDescription);

        //arrangement of the input:
        String inputJson = super.mapToJson((infoDTO));

        String expectedResolvedException = new ResourceAlreadyExistsException("This account already exists.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isConflict())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert:
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


    @DisplayName("Test If User Account was created - Person doesn't exist on Repository")
    @Test
    void testIfUserAccountWasCreatedPersonDoesNotExist() throws Exception {
        //ARRANGE:
        //URI used to call the controller:
        String uri = "/persons/blabla@hotmail.com/accounts";

        //arrangement of the account DTO:
        final String accountDenomination = "Food Expenses";
        final String accountDescription = "Money spent on food";

        //setting information for the DTO:
        CreatePersonAccountInfoDTO infoDTO = new CreatePersonAccountInfoDTO();
        infoDTO.setAccountDenomination(accountDenomination);
        infoDTO.setAccountDescription(accountDescription);

        //arrangement of the input:
        String inputJson = super.mapToJson((infoDTO));

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

        //Assert:
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
    @DisplayName("Test If User Account was created  - email invalid - invalid format")
    void testIfUserAccountWasCreatedInvalidEmailFormat() throws Exception {

        //Arrange
        //URI used to call the controller:
        String uri = "/persons/mail.com/accounts";

        //arrangement of the account DTO:
        final String accountDenomination = "Food Expenses";
        final String accountDescription = "Money spent on food";

        //setting information for the DTO:
        CreatePersonAccountInfoDTO infoDTO = new CreatePersonAccountInfoDTO();
        infoDTO.setAccountDenomination(accountDenomination);
        infoDTO.setAccountDescription(accountDescription);

        //arrangement of the input:
        String inputJson = super.mapToJson((infoDTO));

        String expectedResolvedException = new IllegalArgumentException("The email is not valid.").toString();

        //ACT:
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
    @DisplayName("Test If User Account was created  - account invalid - null")
    void testIfUserAccountWasCreatedAccountDenominationNull() throws Exception {

        //Arrange
        //URI used to call the controller:
        String uri = "/persons/marge@hotmail.com/accounts";

        //arrangement of the account DTO:
        final String personEmail = "marge@hotmail.com";
        final String accountDescription = "Money spent on food";

        //setting information for the DTO:
        CreatePersonAccountInfoDTO infoDTO = new CreatePersonAccountInfoDTO();
        infoDTO.setAccountDenomination(null);
        infoDTO.setAccountDescription(accountDescription);

        //arrangement of the input:
        String inputJson = super.mapToJson((infoDTO));

        String expectedResolvedException = new IllegalArgumentException("The denomination can't be null or empty.").toString();

        //ACT:

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
                () -> assertEquals ("The denomination can't be null or empty.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test If User Account was created - account invalid - empty")
    void testIfUserAccountWasCreatedAccountDescriptionNull() throws Exception {

        //Arrange
        //URI used to call the controller:
        String uri = "/persons/marge@hotmail.com/accounts";

        //arrangement of the account DTO:
        final String accountDenomination = "Food Expenses";

        //setting information for the DTO:
        CreatePersonAccountInfoDTO infoDTO = new CreatePersonAccountInfoDTO();
        infoDTO.setAccountDenomination(accountDenomination);
        infoDTO.setAccountDescription(null);

        //arrangement of the input:
        String inputJson = super.mapToJson((infoDTO));

        String expectedResolvedException = new IllegalArgumentException("The description can't be null or empty.").toString();

        //ACT:
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
                () -> assertEquals ("The description can't be null or empty.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    /**
     * Test if an Account can be found by the ID
     */

    @Test
    @DisplayName("Test if an Account can be found by the ID - Happy Case")
    void getAccountByAccountID() throws Exception {

        //ARRANGE:
        String uri = "/persons/MARGE@HOTMAIL.COM/accounts/HOMER SNACKS";

        String personEmail = "MARGE@HOTMAIL.COM";
        String accountDenomination = "HOMER SNACKS";
        String accountDescription = "MONEY SPENT ON SNACKS FOR HOMER";

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(personEmail.toUpperCase(),result.getString("ownerID")),
                () -> assertEquals(accountDenomination.toUpperCase(), result.getString("denomination")),
                () -> assertEquals(accountDescription.toUpperCase(), result.getString("description"))
        );
    }

    @Test
    @DisplayName("Test if an Account can be found by the ID - Person does not exists")
    void getAccountByAccountIDPersonDoesNotExists() throws Exception {

        //ARRANGE:
        String uri = "/persons/blabla@HOTMAIL.COM/accounts/HOMER SNACKS";


        String expectedResolvedException = new ArgumentNotFoundException("No person found with that email.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
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
                () -> assertEquals ("This resource was not found.", result.getString("error")),
                () -> assertEquals ("No person found with that email.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }


    @Test
    @DisplayName("Test if an Account can be found by the ID - Account not found")
    void getAccountByAccountIDAccountNotFound() throws Exception {

        //ARRANGE:
        String uri = "/persons/MARGE@HOTMAIL.COM/accounts/SNACKS";

        String expectedResolvedException = new ArgumentNotFoundException("No account found with that ID.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
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
                () -> assertEquals ("This resource was not found.", result.getString("error")),
                () -> assertEquals ("No account found with that ID.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );

    }

    @Test
    @DisplayName("Test if an Account can be found by the ID - Invalid Email")
    void getAccountByAccountIDInvalidEmail() throws Exception {

        //ARRANGE:
        String uri = "/persons/MARGEHOTMAIL.COM/accounts/SNACKS";

        String expected = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"One of the parameters is invalid or is missing.\"," +
                "\"message\":\"The email is not valid.\"}";

        String expectedResolvedException = new IllegalArgumentException("The email is not valid.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
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

    /**
     * Test to get all accounts by PersonID
     */

    @Test
    @DisplayName("Test to get all the accounts by PersonID - Happy Case")
    void getAccountsByPersonIDHappyCase() throws Exception {

        //ARRANGE:
        String uri = "/persons/1191782@isep.ipp.pt/accounts";

        //Arrange the expected links that will be returned:
        String expectedLink1 = "[{\"rel\":\"self\"," +
                "\"href\":\"http:\\/\\/localhost\\/persons\\/1191782@ISEP.IPP.PT\\/accounts\\/CTT\"}]";
        String expectedLink2 = "[{\"rel\":\"self\"," +
                "\"href\":\"http:\\/\\/localhost\\/persons\\/1191782@ISEP.IPP.PT\\/accounts\\/MBWAY\"}]";
        String expectedLink3 = "[{\"rel\":\"self\"," +
                "\"href\":\"http:\\/\\/localhost\\/persons\\/1191782@ISEP.IPP.PT\\/accounts\\/HOME\"}]";

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        //Actual string that is returned:
        String result = mvcResult.getResponse().getContentAsString();

        JSONArray jArray = (JSONArray) new JSONTokener(result).nextValue();

        //ASSERT: (assert if the status is the expected (200 = OK), and assert if the returned String is equal to the expected one:
        Assertions.assertAll(
                () -> assertEquals(200, status),

                () -> assertEquals("1191782@ISEP.IPP.PT",jArray.getJSONObject(0).getString("ownerID")),
                () -> assertEquals("CTT",jArray.getJSONObject(0).getString("denomination")),
                () -> assertEquals("WORK",jArray.getJSONObject(0).getString("description")),
                () -> assertEquals(expectedLink1, jArray.getJSONObject(0).getString("links")),

                () -> assertEquals("1191782@ISEP.IPP.PT",jArray.getJSONObject(1).getString("ownerID")),
                () -> assertEquals("MBWAY",jArray.getJSONObject(1).getString("denomination")),
                () -> assertEquals("FRIENDS",jArray.getJSONObject(1).getString("description")),
                () -> assertEquals(expectedLink2, jArray.getJSONObject(1).getString("links")),

                () -> assertEquals("1191782@ISEP.IPP.PT",jArray.getJSONObject(2).getString("ownerID")),
                () -> assertEquals("HOME",jArray.getJSONObject(2).getString("denomination")),
                () -> assertEquals("HOME EXPENSES",jArray.getJSONObject(2).getString("description")),
                () -> assertEquals(expectedLink3, jArray.getJSONObject(2).getString("links"))
        );

    }

    @Test
    @DisplayName("Test to get all the accounts by PersonID - Invalid Email")
    void getAccountsByPersonIDInvalidEmail() throws Exception {

        //ARRANGE:
        String uri = "/persons/1191782isep.ipp.pt/accounts";

        String expectedResolvedException = new IllegalArgumentException("The email is not valid.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
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
    @DisplayName("Test to get all the accounts by PersonID - Person Does Not Exists")
    void getAccountsByPersonIDPersonNotExists() throws Exception {

        //ARRANGE:
        String uri = "/persons/raquel@isep.ipp.pt/accounts";

        String expectedResolvedException = new ArgumentNotFoundException("No person found with that email.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
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
                () -> assertEquals ("This resource was not found.", result.getString("error")),
                () -> assertEquals ("No person found with that email.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test to get all the accounts by PersonID - Accounts Not Found")
    void getAccountsByPersonIDAccountsNotFound() throws Exception {

        //ARRANGE:
        String uri = "/persons/1191755@isep.ipp.pt/accounts";

        String expectedResolvedException = new ArgumentNotFoundException("No account found with that ID.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
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
                () -> assertEquals ("This resource was not found.", result.getString("error")),
                () -> assertEquals ("No account found with that ID.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

}

