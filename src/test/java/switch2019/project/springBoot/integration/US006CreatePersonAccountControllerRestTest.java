package switch2019.project.springBoot.integration;


import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.DeserializationDTO.CreatePersonAccountInfoDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US006CreatePersonAccountControllerRestTest extends AbstractTest {

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
        String uri = "/createPersonAccount";

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
        String expected = "{\"ownerID\":\"" + personEmail.toUpperCase() + "\"" + "," + "\"denomination\":\"" + accountDenomination.toUpperCase() +
                "\"" + "," + "\"description\":\"" + accountDescription.toUpperCase() + "\"}";

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
        String uri = "/createPersonAccount";

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

        //ACT:
        Throwable exception = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //ASSERT:
        assertThat(exception)
                .hasCause(new IllegalArgumentException("This account already exists."))
                .isExactlyInstanceOf(NestedServletException.class);

    }


    @DisplayName("Test If User Account was created - Person doesn't exist on Repository")
    @Test
    void testIfUserAccountWasCreatedPersonDoesNotExist() throws Exception {
        //ARRANGE:
            //URI used to call the controller:
        String uri = "/createPersonAccount";

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

        //ACT:
        Throwable exception = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //ASSERT:
        assertThat(exception)
                .hasCause(new IllegalArgumentException("No person found with that email."))
                .isExactlyInstanceOf(NestedServletException.class);

    }

    @DisplayName("Test If User Account was created - email invalid - null")
    @Test
    void testIsUserAccountWasCreatedEmailNull() throws Exception {
        //ARRANGE:
            //URI used to call the controller:
        String uri = "/createPersonAccount";

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

        //ACT:
        Throwable exception = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //ASSERT:
        assertThat(exception)
                .hasCause(new IllegalArgumentException("The email can't be null."))
                .isExactlyInstanceOf(NestedServletException.class);

    }


    @Test
    @DisplayName("Test If User Account was created  - email invalid - empty")
    void testIfUserAccountWasCreatedEmailEmpty() throws Exception {

        //Arrange
        //URI used to call the controller:
        String uri = "/createPersonAccount";

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


        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The email it´s not valid"))
                .isExactlyInstanceOf(NestedServletException.class);
    }

    @Test
    @DisplayName("Test If User Account was created  - email invalid - invalid format")
    void testIfUserAccountWasCreatedInvalidEmailFormat() throws Exception {

        //Arrange
        //URI used to call the controller:
        String uri = "/createPersonAccount";

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


        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The email it´s not valid"))
                .isExactlyInstanceOf(NestedServletException.class);
    }



    @Test
    @DisplayName("Test If User Account was created  - account invalid - null")
    void testIfUserAccountWasCreatedAccountDenominationNull() throws Exception {

        //Arrange
        //URI used to call the controller:
        String uri = "/createPersonAccount";

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


        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The denomination can't be null or empty."))
                .isExactlyInstanceOf(NestedServletException.class);
    }



    @Test
    @DisplayName("Test If User Account was created - account invalid - empty")
    void testIfUserAccountWasCreatedAccountDescriptionNull() throws Exception {

        //Arrange
        //URI used to call the controller:
        String uri = "/createPersonAccount";

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


        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The description can't be null or empty."))
                .isExactlyInstanceOf(NestedServletException.class);
    }


}

