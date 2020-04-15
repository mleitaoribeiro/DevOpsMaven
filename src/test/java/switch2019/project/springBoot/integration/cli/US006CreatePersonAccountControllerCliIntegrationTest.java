package switch2019.project.springBoot.integration.cli;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.SerializationDTO.AccountDTO;
import switch2019.project.DTO.ServiceDTO.CreatePersonAccountDTO;
import switch2019.project.assemblers.AccountDTOAssembler;
import switch2019.project.controllerLayer.controllersCli.US006CreatePersonAccountController;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class US006CreatePersonAccountControllerCliIntegrationTest {

    @Autowired
    private US006CreatePersonAccountController controller;

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
        String accountDenomination = "MBCP";
        String accountDescription = "SAVINGS";

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes (personEmail, accountDenomination, accountDescription );

        AccountDTO accountExpectedDTO = new AccountDTO(createPersonAccountDTO.getPersonEmail(),
                createPersonAccountDTO.getAccountDenomination(), createPersonAccountDTO.getAccountDescription());

        //Act
        AccountDTO accountCreatedDTO = controller.createPersonAccount(personEmail, accountDenomination, accountDescription);

        //Assert
        assertEquals(accountExpectedDTO, accountCreatedDTO);
    }

    @DisplayName("Test If User Account was created - Account already exists")
    @Test
    void testIfUserAccountWasCreatedRepeatedAccount() {
        //Arrange
        String personEmail = "marge@hotmail.com";
        String accountDenomination = "Homer Snacks";
        String accountDescription = "Money spent on snacks for homer";

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
        //Arrange
        String personEmail = "morty@gmail.com";
        String accountDenomination = null;
        String accountDescription = "Revolut Account";

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
