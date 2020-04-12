package switch2019.project.springBoot.unit.cli;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2019.project.DTO.DeserializationDTO.CreatePersonAccountInfoDTO;
import switch2019.project.DTO.SerializationDTO.AccountDTO;
import switch2019.project.DTO.ServiceDTO.CreatePersonAccountDTO;
import switch2019.project.applicationLayer.US006CreatePersonAccountService;
import switch2019.project.assemblers.AccountDTOAssembler;
import switch2019.project.controllerLayer.controllersCli.US006CreatePersonAccountController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)

public class US006CreatePersonAccountControllerCliUnitTest {

    @Mock @Autowired private US006CreatePersonAccountService service;
    @Autowired private US006CreatePersonAccountController controller;


    //ISSUE 813

    @DisplayName("Test If User Account is created - Main Scenario")
    @Test
    void testIfUserAccountWasCreatedSuccessCase() {
        //Arrange
        String personEmail = "morty@gmail.com";
        String accountDenomination = "Revolut";
        String accountDescription = "Revolut Account";

        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setPersonEmail(personEmail);
        createPersonAccountInfoDTO.setAccountDenomination(accountDenomination);
        createPersonAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes (personEmail, accountDenomination, accountDescription );
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
        String accountDenomination = "Revolut";
        String accountDescription = "Revolut Account";

        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setPersonEmail(personEmail);
        createPersonAccountInfoDTO.setAccountDenomination(accountDenomination);
        createPersonAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes (personEmail, accountDenomination, accountDescription );
        AccountDTO accountCreatedExpected = new AccountDTO(personEmail, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenReturn(accountCreatedExpected);

        controller.createPersonAccount(personEmail, accountDenomination, accountDescription);

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

        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setPersonEmail(personEmail);
        createPersonAccountInfoDTO.setAccountDenomination(accountDenomination);
        createPersonAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes (personEmail, accountDenomination, accountDescription );
        AccountDTO accountCreatedExpected = new AccountDTO(personEmail, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenReturn(accountCreatedExpected);

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

        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setPersonEmail(null);
        createPersonAccountInfoDTO.setAccountDenomination(accountDenomination);
        createPersonAccountInfoDTO.setAccountDescription(accountDescription);


        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(null, accountDenomination, accountDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email can´t be null!");
    }

    @Test
    @DisplayName("Test If User Account was created  - email invalid - empty")
    void testIfUserAccountWasCreatedEmailEmpty() {

        //Arrange
        String personEmail = "";
        String accountDenomination = "Revolut";
        String accountDescription = "Revolut Account";

        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setPersonEmail(personEmail);
        createPersonAccountInfoDTO.setAccountDenomination(accountDenomination);
        createPersonAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes (personEmail, accountDenomination, accountDescription );
        AccountDTO accountCreatedExpected = new AccountDTO(personEmail, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenReturn(accountCreatedExpected);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(personEmail, accountDenomination, accountDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email it´s not valid");
    }

    @Test
    @DisplayName("Test If User Account was created  - email invalid - invalid format")
    void testIfUserAccountWasCreatedInvalidEmailFormat() {
        //Arrange
        String personEmail = "morty@@gmail.com";
        String accountDenomination = "Revolut";
        String accountDescription = "Revolut Account";

        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setPersonEmail(personEmail);
        createPersonAccountInfoDTO.setAccountDenomination(accountDenomination);
        createPersonAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes (personEmail, accountDenomination, accountDescription );
        AccountDTO accountCreatedExpected = new AccountDTO(personEmail, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenReturn(accountCreatedExpected);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(personEmail, accountDenomination, accountDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email it´s not valid");
    }

    @Test
    @DisplayName("Test If User Account was created  - account invalid - null")
    void testIfUserAccountWasCreatedAccountDenominationNull() {
        String personEmail = "morty@gmail.com";
        String accountDenomination = null;
        String accountDescription = "Revolut Account";

        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setPersonEmail(personEmail);
        createPersonAccountInfoDTO.setAccountDenomination(accountDenomination);
        createPersonAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes (personEmail, accountDenomination, accountDescription );
        AccountDTO accountCreatedExpected = null;

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenReturn(accountCreatedExpected);
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

        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setPersonEmail(personEmail);
        createPersonAccountInfoDTO.setAccountDenomination(accountDenomination);
        createPersonAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes (personEmail, accountDenomination, accountDescription );
        AccountDTO accountCreatedExpected = new AccountDTO(personEmail, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenReturn(accountCreatedExpected);


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
