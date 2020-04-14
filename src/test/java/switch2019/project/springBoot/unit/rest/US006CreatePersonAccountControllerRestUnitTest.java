package switch2019.project.springBoot.unit.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2019.project.DTO.DeserializationDTO.CreatePersonAccountInfoDTO;
import switch2019.project.DTO.SerializationDTO.AccountDTO;
import switch2019.project.DTO.ServiceDTO.CreatePersonAccountDTO;
import switch2019.project.applicationLayer.US006CreatePersonAccountService;
import switch2019.project.assemblers.AccountDTOAssembler;
import switch2019.project.controllerLayer.controllersRest.US006CreatePersonAccountControllerRest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)

public class US006CreatePersonAccountControllerRestUnitTest {

    @Mock
    private US006CreatePersonAccountService service;

    @Autowired
    private US006CreatePersonAccountControllerRest controller;


    /**
     * US006
     * As a user, I want to create a personal account
     */

    //ISSUE 820

    @DisplayName("Test If User Account is created - Main Scenario")
    @Test
    void testIfUserAccountWasCreatedSuccessCase() {
        //Arrange
        String personEmail = "morty@gmail.com";
        String accountDenomination = "Vet";
        String accountDescription = "Veterinarian dispenses";

        CreatePersonAccountInfoDTO personAccountInfoDTO = new CreatePersonAccountInfoDTO();
        personAccountInfoDTO.setPersonEmail(personEmail);
        personAccountInfoDTO.setAccountDenomination(accountDenomination);
        personAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO accountControllerDTO = AccountDTOAssembler.
                transformIntoCreatePersonAccountDTO(personAccountInfoDTO);

        //Expected Results: AccountDTO and ResponseEntity
        AccountDTO accountExpectedDTO = new AccountDTO(personEmail, accountDenomination, accountDescription);
        ResponseEntity responseEntityExpected = new ResponseEntity<>(accountExpectedDTO, HttpStatus.CREATED);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createPersonAccount(accountControllerDTO)).thenReturn(accountExpectedDTO);

        //Act
        ResponseEntity<AccountDTO> responseEntityResult = controller.createPersonAccount(personAccountInfoDTO);

        //Assert
        assertEquals(responseEntityExpected, responseEntityResult);
    }

    @DisplayName("Test If User Account was created - Account already exists")
    @Test
    void testIfUserAccountWasCreatedRepeatedAccount() {
        //Arrange
        String personEmail = "morty@gmail.com";
        String accountDenomination = "Revolution";
        String accountDescription = "Revolut Account";

        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setPersonEmail(personEmail);
        createPersonAccountInfoDTO.setAccountDenomination(accountDenomination);
        createPersonAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.transformIntoCreatePersonAccountDTO (createPersonAccountInfoDTO);
        AccountDTO accountCreatedExpected = new AccountDTO(personEmail, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenReturn(accountCreatedExpected);

        controller.createPersonAccount(createPersonAccountInfoDTO);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(createPersonAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This account already exists.");
    }

    @DisplayName("Test If User Account was created - Person doesn't exist on Repository")
    @Test
    void testIfUserAccountWasCreatedPersonNotInRepo() {
        //Arrange
        String personEmail = "person@email.com";
        String accountDenomination = "Revolut";
        String accountDescription = "Revolut Account";

        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setPersonEmail(personEmail);
        createPersonAccountInfoDTO.setAccountDenomination(accountDenomination);
        createPersonAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.transformIntoCreatePersonAccountDTO (createPersonAccountInfoDTO);
        AccountDTO accountCreatedExpected = new AccountDTO(personEmail, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenReturn(accountCreatedExpected);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(createPersonAccountInfoDTO);
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

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.transformIntoCreatePersonAccountDTO (createPersonAccountInfoDTO);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenThrow(
                new IllegalArgumentException("The email can't be null."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(createPersonAccountInfoDTO);
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

        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setPersonEmail(personEmail);
        createPersonAccountInfoDTO.setAccountDenomination(accountDenomination);
        createPersonAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.transformIntoCreatePersonAccountDTO (createPersonAccountInfoDTO);
        AccountDTO accountCreatedExpected = new AccountDTO(personEmail, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenReturn(accountCreatedExpected);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(createPersonAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email it's not valid.");
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

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.transformIntoCreatePersonAccountDTO (createPersonAccountInfoDTO);
        AccountDTO accountCreatedExpected = new AccountDTO(personEmail, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenReturn(accountCreatedExpected);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(createPersonAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email it's not valid.");
    }

    @Test
    @DisplayName("Test If User Account was created  - account invalid - null")
    void testIfUserAccountWasCreatedAccountDenominationNull() {

        //Arrange
        String personEmail = "morty@gmail.com";
        String accountDenomination = null;
        String accountDescription = "Revolut Account";

        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setPersonEmail(personEmail);
        createPersonAccountInfoDTO.setAccountDenomination(accountDenomination);
        createPersonAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.transformIntoCreatePersonAccountDTO (createPersonAccountInfoDTO);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(createPersonAccountInfoDTO);
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

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.transformIntoCreatePersonAccountDTO (createPersonAccountInfoDTO);
        AccountDTO accountCreatedExpected = new AccountDTO(personEmail, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenReturn(accountCreatedExpected);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(createPersonAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");

    }




}
