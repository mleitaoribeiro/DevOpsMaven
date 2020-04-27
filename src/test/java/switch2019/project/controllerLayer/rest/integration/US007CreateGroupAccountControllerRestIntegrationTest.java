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
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        String uri = "/addGroupAccount";

        final String groupDescription = "Family Simpson";
        final String personEmail = "homer@hotmail.com";
        final String accountDenomination = "Kwik E Mart";
        final String accountDescription = "Duff Beer Expenses";


        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String expected = "{\"ownerID\":\"" + groupDescription.toUpperCase() + "\"" + "," + "\"denomination\":\"" + accountDenomination.toUpperCase() +
                "\"" + "," + "\"description\":\"" + accountDescription.toUpperCase() + "\"}";

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
        /*
        Assertions.assertAll(
                () -> assertEquals(201, status),
                () -> assertEquals(expected, result)
        );

         */
    }

    @Test
    @DisplayName("Test Group Account creation - person is not Admin")
    void addGroupAccountPersonIsNotAdmin() throws Exception {

        //Arrange
        String uri = "/addGroupAccount";

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

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        // Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("This person is not admin of this group."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }

    @Test
    @DisplayName("Test Group Account creation -  person does not exists on Person Repository")
    void addGroupAccountPersonDoesNotExists() throws Exception {

        //Arrange
        String uri = "/addGroupAccount";

        final String groupDescription = "Switch";
        final String personEmail = "raquel@hotmail.com";
        final String accountDenomination = "Gym";
        final String accountDescription = "Fitness Expenses";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });
        //Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("No person found with that email."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }


    @Test
    @DisplayName("Test Group Account creation -  group account already exists - test if output and HTTP response are expected ")
    void addGroupAccountGroupAccountAlreadyExistsException() throws Exception {

        //Arrange
        String uri = "/addGroupAccount";

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

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        // Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("This account already exists."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }

    @Test
    @DisplayName("Test Group Account creation -  group does not exists - test if output and HTTP response are expected ")
    void addGroupAccountTestGroupNotFoundException() throws Exception {

        //Arrange
        String uri = "/addGroupAccount";

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

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        // Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("No group found with that description."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }

    @Test
    @DisplayName("Test Group Account creation - Email Null")
    void addGroupAccountNullEmail() throws Exception {
        //Arrange
        String uri = "/addGroupAccount";

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

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        // Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The email can't be null."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }

    @Test
    @DisplayName("Test Group Account creation - Email Empty")
    void addGroupAccountEmailEmpty() throws Exception {
        //Arrange
        String uri = "/addGroupAccount";

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

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        // Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The email is not valid."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }

    @Test
    @DisplayName("Test Group Account creation - Group ID Null")
    void addGroupAccountGroupIDNull() throws Exception {
        //Arrange
        String uri = "/addGroupAccount";

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

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        // Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The description can't be null or empty."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }

    @Test
    @DisplayName("Test Group Account creation - Group ID empty")
    void addGroupAccountGroupIDEmpty() throws Exception {
        //Arrange
        String uri = "/addGroupAccount";

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

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        // Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The description can't be null or empty."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }

    @Test
    @DisplayName("Test Group Account creation - account denomination null")
    void addGroupAccountDenominationNull() throws Exception {
        //Arrange
        String uri = "/addGroupAccount";

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

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        // Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The denomination can't be null or empty."))
                .isExactlyInstanceOf(NestedServletException.class);

         */

    }

    @Test
    @DisplayName("Test Group Account creation - account denomination empty")
    void addGroupAccountDenominationEmpty() throws Exception {
        //Arrange
        String uri = "/addGroupAccount";

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

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        // Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The denomination can't be null or empty."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }

    @Test
    @DisplayName("Test Group Account creation - account description null")
    void addGroupAccountDescriptionNull() throws Exception {
        //Arrange
        String uri = "/addGroupAccount";

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

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        // Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The description can't be null or empty."))
                .isExactlyInstanceOf(NestedServletException.class);

         */


    }

    @Test
    @DisplayName("Test Group Account creation - account description empty")
    void addGroupAccountDescriptionEmpty() throws Exception {
        //Arrange
        String uri = "/addGroupAccount";

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

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        // Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The description can't be null or empty."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }

}
