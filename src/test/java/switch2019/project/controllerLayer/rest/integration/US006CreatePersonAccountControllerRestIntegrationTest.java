package switch2019.project.controllerLayer.rest.integration;


import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.deserializationDTO.CreatePersonAccountInfoDTO;
import switch2019.project.customExceptions.ArgumentNotFoundException;
import switch2019.project.customExceptions.ResourceAlreadyExistsException;

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
        //ARRANGE:
            //URI used to call the controller:
        String uri = "/accounts/createPersonAccount";

            //arrangement of the account DTO:
        final String personEmail = "marge@hotmail.com";
        final String accountDenomination = "Food Expenses";
        final String accountDescription = "Money spent on food";

            //setting information for the DTO:
        CreatePersonAccountInfoDTO infoDTO = new CreatePersonAccountInfoDTO();
        infoDTO.setPersonEmail(personEmail);
        infoDTO.setAccountDenomination(accountDenomination);
        infoDTO.setAccountDescription(accountDescription);

        //arrangement of the input:
        String inputJson = super.mapToJson((infoDTO));

        //arrangement of the expected output:
        String expected = "{\"ownerID\":\"" +personEmail.toUpperCase() +
                "\",\"denomination\":\"" +accountDenomination.toUpperCase() +
                "\",\"description\":\"" +accountDescription.toUpperCase() +
                "\",\"_links\":{\"self\":{\"href\":\"http://localhost/accounts/MARGE@HOTMAIL.COM/FOOD%20EXPENSES\"}}}";;

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isCreated())
                .andExpect(content().string(expected))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(201, status),
                () -> assertEquals(expected, result)
        );
    }

    @DisplayName("Test If User Account was created - Account already on Repository")
    @Test
    void testIfUserAccountWasCreatedAccountAlreadyExists() throws Exception {
        //ARRANGE:
            //URI used to call the controller:
        String uri = "/accounts/createPersonAccount";

            //arrangement of the account DTO:
        final String personEmail = "marge@hotmail.com";
        final String accountDenomination = "Homer Snacks";
        final String accountDescription = "Money spent on snacks for homer";

            //setting information for the DTO:
        CreatePersonAccountInfoDTO infoDTO = new CreatePersonAccountInfoDTO();
        infoDTO.setPersonEmail(personEmail);
        infoDTO.setAccountDenomination(accountDenomination);
        infoDTO.setAccountDescription(accountDescription);

            //arrangement of the input:
        String inputJson = super.mapToJson((infoDTO));

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


    @DisplayName("Test If User Account was created - Person doesn't exist on Repository")
    @Test
    void testIfUserAccountWasCreatedPersonDoesNotExist() throws Exception {
        //ARRANGE:
            //URI used to call the controller:
        String uri = "/accounts/createPersonAccount";

            //arrangement of the account DTO:
        final String personEmail = "blabla@hotmail.com";
        final String accountDenomination = "Food Expenses";
        final String accountDescription = "Money spent on food";

            //setting information for the DTO:
        CreatePersonAccountInfoDTO infoDTO = new CreatePersonAccountInfoDTO();
        infoDTO.setPersonEmail(personEmail);
        infoDTO.setAccountDenomination(accountDenomination);
        infoDTO.setAccountDescription(accountDescription);

            //arrangement of the input:
        String inputJson = super.mapToJson((infoDTO));

        String expectedErrorMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"This resource was not found.\"," +
                "\"errors\":[\"No person found with that email.\"]}";

        String expectedResolvedException = new ArgumentNotFoundException("No person found with that email.").toString();

        //ACT:
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

    @DisplayName("Test If User Account was created - email invalid - null")
    @Test
    void testIsUserAccountWasCreatedEmailNull() throws Exception {
        //ARRANGE:
            //URI used to call the controller:
        String uri = "/accounts/createPersonAccount";

            //arrangement of the account DTO:
        final String personEmail = null;
        final String accountDenomination = "Food Expenses";
        final String accountDescription = "Money spent on food";

            //setting information for the DTO:
        CreatePersonAccountInfoDTO infoDTO = new CreatePersonAccountInfoDTO();
        infoDTO.setPersonEmail(personEmail);
        infoDTO.setAccountDenomination(accountDenomination);
        infoDTO.setAccountDescription(accountDescription);

            //arrangement of the input:
        String inputJson = super.mapToJson((infoDTO));

        String expectedErrorMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The email can't be null.\"]}";

        String expectedResolvedException = new IllegalArgumentException("The email can't be null.").toString();

        //ACT:
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
    @DisplayName("Test If User Account was created  - email invalid - empty")
    void testIfUserAccountWasCreatedEmailEmpty() throws Exception {

        //Arrange
        //URI used to call the controller:
        String uri = "/accounts/createPersonAccount";

        //arrangement of the account DTO:
        final String personEmail = "";
        final String accountDenomination = "Food Expenses";
        final String accountDescription = "Money spent on food";

        //setting information for the DTO:
        CreatePersonAccountInfoDTO infoDTO = new CreatePersonAccountInfoDTO();
        infoDTO.setPersonEmail(personEmail);
        infoDTO.setAccountDenomination(accountDenomination);
        infoDTO.setAccountDescription(accountDescription);

        //arrangement of the input:
        String inputJson = super.mapToJson((infoDTO));

        String expectedErrorMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The email is not valid.\"]}";

        String expectedResolvedException = new IllegalArgumentException("The email is not valid.").toString();

        //ACT:
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
    @DisplayName("Test If User Account was created  - email invalid - invalid format")
    void testIfUserAccountWasCreatedInvalidEmailFormat() throws Exception {

        //Arrange
        //URI used to call the controller:
        String uri = "/accounts/createPersonAccount";

        //arrangement of the account DTO:
        final String personEmail = "mail.com";
        final String accountDenomination = "Food Expenses";
        final String accountDescription = "Money spent on food";

        //setting information for the DTO:
        CreatePersonAccountInfoDTO infoDTO = new CreatePersonAccountInfoDTO();
        infoDTO.setPersonEmail(personEmail);
        infoDTO.setAccountDenomination(accountDenomination);
        infoDTO.setAccountDescription(accountDescription);

        //arrangement of the input:
        String inputJson = super.mapToJson((infoDTO));

        String expectedErrorMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The email is not valid.\"]}";

        String expectedResolvedException = new IllegalArgumentException("The email is not valid.").toString();

        //ACT:
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
    @DisplayName("Test If User Account was created  - account invalid - null")
    void testIfUserAccountWasCreatedAccountDenominationNull() throws Exception {

        //Arrange
        //URI used to call the controller:
        String uri = "/accounts/createPersonAccount";

        //arrangement of the account DTO:
        final String personEmail = "marge@hotmail.com";
        final String accountDescription = "Money spent on food";

        //setting information for the DTO:
        CreatePersonAccountInfoDTO infoDTO = new CreatePersonAccountInfoDTO();
        infoDTO.setPersonEmail(personEmail);
        infoDTO.setAccountDenomination(null);
        infoDTO.setAccountDescription(accountDescription);

        //arrangement of the input:
        String inputJson = super.mapToJson((infoDTO));

        String expectedErrorMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The denomination can't be null or empty.\"]}";

        String expectedResolvedException = new IllegalArgumentException("The denomination can't be null or empty.").toString();

        //ACT:
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
    @DisplayName("Test If User Account was created - account invalid - empty")
    void testIfUserAccountWasCreatedAccountDescriptionNull() throws Exception {

        //Arrange
        //URI used to call the controller:
        String uri = "/accounts/createPersonAccount";

        //arrangement of the account DTO:
        final String personEmail = "marge@hotmail.com";
        final String accountDenomination = "Food Expenses";

        //setting information for the DTO:
        CreatePersonAccountInfoDTO infoDTO = new CreatePersonAccountInfoDTO();
        infoDTO.setPersonEmail(personEmail);
        infoDTO.setAccountDenomination(accountDenomination);
        infoDTO.setAccountDescription(null);

        //arrangement of the input:
        String inputJson = super.mapToJson((infoDTO));

        String expectedErrorMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The description can't be null or empty.\"]}";

        String expectedResolvedException = new IllegalArgumentException("The description can't be null or empty.").toString();

        //ACT:
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
    @DisplayName("Test if an existing person creates a Group and becomes Admin - null input")
    void createGroupAndBecomeAdminNullJsonInput() throws Exception {

        //Arrange
        String uri = "/accounts/createPersonAccount";

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
     * Test if an Account can be found by the ID
     */

    @Test
    @DisplayName("Test if an Account can be found by the ID - Happy Case")
    void getAccountByAccountID() throws Exception {

        //ARRANGE:
        String uri = "/accounts/MARGE@HOTMAIL.COM/HOMER SNACKS";

        String expected = "{\"ownerID\":\"MARGE@HOTMAIL.COM\"," +
                "\"denomination\":\"HOMER SNACKS\"," +
                "\"description\":\"MONEY SPENT ON SNACKS FOR HOMER\"}";

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

    @Test
    @DisplayName("Test if an Account can be found by the ID - Person does not exists")
    void getAccountByAccountIDPersonDoesNotExists() throws Exception {

        //ARRANGE:
        String uri = "/accounts/blabla@HOTMAIL.COM/HOMER SNACKS";

        String expected = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"This resource was not found.\"," +
                "\"errors\":[\"No person found with that email.\"]}";

        String expectedResolvedException = new ArgumentNotFoundException("No person found with that email.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expected, result),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );

    }


    @Test
    @DisplayName("Test if an Account can be found by the ID - Account not found")
    void getAccountByAccountIDAccountNotFound() throws Exception {

        //ARRANGE:
        String uri = "/accounts/MARGE@HOTMAIL.COM/SNACKS";

        String expected = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"This resource was not found.\"," +
                "\"errors\":[\"No account found with that ID.\"]}";

        String expectedResolvedException = new ArgumentNotFoundException("No account found with that ID.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expected, result),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );

    }

    @Test
    @DisplayName("Test if an Account can be found by the ID - Invalid Email")
    void getAccountByAccountIDInvalidEmail() throws Exception {

        //ARRANGE:
        String uri = "/accounts/MARGEHOTMAIL.COM/SNACKS";

        String expected = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The email is not valid.\"]}";

        String expectedResolvedException = new IllegalArgumentException("The email is not valid.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expected, result),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );

    }

}

