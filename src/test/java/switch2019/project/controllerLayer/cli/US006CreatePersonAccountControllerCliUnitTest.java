package switch2019.project.controllerLayer.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serviceDTO.CreatePersonAccountDTO;
import switch2019.project.applicationLayer.US006CreatePersonAccountService;
import switch2019.project.assemblers.AccountDTOAssembler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")

public class US006CreatePersonAccountControllerCliUnitTest {

    @Mock
    private US006CreatePersonAccountService service;

    @InjectMocks
    private US006CreatePersonAccountController controller;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * US006
     * As a user, I want to create a personal account
     */

    //ISSUE 813
    @DisplayName("Test If User Account is created - Main Scenario")
    @Test
    void testIfUserAccountWasCreatedSuccessCase() {
        //Arrange
        String personEmail = "morty@gmail.com";
        String accountDenomination = "Revolut";
        String accountDescription = "Revolut Account";

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes(personEmail, accountDenomination, accountDescription);
        AccountDTO accountCreatedExpected = new AccountDTO(personEmail, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenReturn(accountCreatedExpected);

        AccountDTO accountExpectedDTO = new AccountDTO(personEmail, accountDenomination, accountDescription);

        //Act
        AccountDTO accountCreatedDTO = controller.createPersonAccount(personEmail, accountDenomination, accountDescription);


        //Assert
        assertEquals(accountExpectedDTO, accountCreatedDTO);
    }

    @DisplayName("Test If User Account was created - Account already exists")
    @Test
    void testIfUserAccountWasCreatedRepeatedAccount() {
        //Arrange
        String personEmail = "morty@gmail.com";
        String accountDenomination = "Revolutions";
        String accountDescription = "Revolut Account";

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes(personEmail, accountDenomination, accountDescription);

        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenThrow(new IllegalArgumentException("This account already exists."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(personEmail, accountDenomination, accountDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This account already exists.");
    }


    @DisplayName("Test If User Account was created - Person doesn't exist on Repository")
    @Test
    void testIfUserAccountWasCreatedNoPersonInRepo() {
        //Arrange
        String personEmail = "email@email.com";
        String accountDenomination = "Revolut";
        String accountDescription = "Revolut Account";


        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes(personEmail, accountDenomination, accountDescription);

        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenThrow(new IllegalArgumentException("No person found with that email."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(personEmail, accountDenomination, accountDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No person found with that email.");
    }


    @DisplayName("Test If User Account was created - email invalid - null")
    @Test
    void testIfUserAccountWasCreatedEmailNull() {
        //Arrange
        String accountDenomination = "Revolut";
        String accountDescription = "Revolut Account";

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes(null, accountDenomination, accountDescription);

        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenThrow(
                new IllegalArgumentException("The email can't be null."));


        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(null, accountDenomination, accountDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email can't be null.");
    }

    @Test
    @DisplayName("Test If User Account was created  - email invalid - empty")
    void testIfUserAccountWasCreatedEmailEmpty() {

        //Arrange
        String personEmail = "";
        String accountDenomination = "Revolut";
        String accountDescription = "Revolut Account";

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes(personEmail, accountDenomination, accountDescription);

        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenThrow(new IllegalArgumentException("The email is not valid."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(personEmail, accountDenomination, accountDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email is not valid.");
    }

    @Test
    @DisplayName("Test If User Account was created  - email invalid - invalid format")
    void testIfUserAccountWasCreatedInvalidEmailFormat() {
        //Arrange
        String personEmail = "morty@@gmail.com";
        String accountDenomination = "Revolut";
        String accountDescription = "Revolut Account";

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes(personEmail, accountDenomination, accountDescription);
        AccountDTO accountCreatedExpected = new AccountDTO(personEmail, accountDenomination, accountDescription);

        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenThrow(new IllegalArgumentException("The email is not valid."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(personEmail, accountDenomination, accountDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email is not valid.");
    }

    @Test
    @DisplayName("Test If User Account was created  - account invalid - null")
    void testIfUserAccountWasCreatedAccountDenominationNull() {
        String personEmail = "morty@gmail.com";
        String accountDenomination = null;
        String accountDescription = "Revolut Account";

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes(personEmail, accountDenomination, accountDescription);

        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));
        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(personEmail, accountDenomination, accountDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");
    }

    @Test
    @DisplayName("Test If User Account was created - account invalid - empty")
    void testIfUserAccountWasCreatedAccountDenominationEmpty() {
        //Arrange
        String personEmail = "morty@gmail.com";
        String accountDenomination = "";
        String accountDescription = "Revolut Account";

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes(personEmail, accountDenomination, accountDescription);

        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));


        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(personEmail, accountDenomination, accountDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");

    }
}
