package switch2019.project.controllerLayer.rest.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import switch2019.project.DTO.deserializationDTO.CreatePersonAccountInfoDTO;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serviceDTO.CreatePersonAccountDTO;
import switch2019.project.applicationLayer.US006CreatePersonAccountService;
import switch2019.project.assemblers.AccountDTOAssembler;
import switch2019.project.controllerLayer.rest.US006CreatePersonAccountControllerRest;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@ActiveProfiles("test")

public class US006CreatePersonAccountControllerRestUnitTest {

    @Mock
    private US006CreatePersonAccountService service;

    @InjectMocks
    private US006CreatePersonAccountControllerRest controller;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

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
        personAccountInfoDTO.setAccountDenomination(accountDenomination);
        personAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO accountControllerDTO = AccountDTOAssembler.
                transformIntoCreatePersonAccountDTO(personEmail, personAccountInfoDTO);

        //Expected Results: AccountDTO and ResponseEntity
        AccountDTO accountExpectedDTO = new AccountDTO(personEmail, accountDenomination, accountDescription);
        ResponseEntity<AccountDTO> responseEntityExpected = new ResponseEntity<>(accountExpectedDTO, HttpStatus.CREATED);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createPersonAccount(accountControllerDTO)).thenReturn(accountExpectedDTO);

        //Act
        ResponseEntity<AccountDTO> responseEntityResult = controller.createPersonAccount(personEmail, personAccountInfoDTO);

        //Assert
        assertEquals(responseEntityExpected, responseEntityResult);
        assertNotNull(responseEntityResult.getStatusCode());
    }

    @DisplayName("Test If User Account was created - Account already exists")
    @Test
    void testIfUserAccountWasCreatedRepeatedAccount() {
        //Arrange
        String personEmail = "morty@gmail.com";
        String accountDenomination = "Revolution";
        String accountDescription = "Revolut Account";

        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setAccountDenomination(accountDenomination);
        createPersonAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.transformIntoCreatePersonAccountDTO(personEmail, createPersonAccountInfoDTO);
        AccountDTO accountCreatedExpected = new AccountDTO(personEmail, accountDenomination, accountDescription);

        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenThrow(new ResourceAlreadyExistsException("This account already exists."));


        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(personEmail, createPersonAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ResourceAlreadyExistsException.class)
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
        createPersonAccountInfoDTO.setAccountDenomination(accountDenomination);
        createPersonAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.transformIntoCreatePersonAccountDTO(personEmail, createPersonAccountInfoDTO);
        AccountDTO accountCreatedExpected = new AccountDTO(personEmail, accountDenomination, accountDescription);

        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenThrow(new ArgumentNotFoundException("No person found with that email."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(personEmail, createPersonAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No person found with that email.");
    }

    @DisplayName("Test If User Account was created - email invalid - null")
    @Test
    void testIfUserAccountWasCreatedEmailNull() {
        //Arrange
        String accountDenomination = "Revolut";
        String accountDescription = "Revolut Account";

        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setAccountDenomination(accountDenomination);
        createPersonAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.transformIntoCreatePersonAccountDTO(null, createPersonAccountInfoDTO);

        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenThrow(
                new IllegalArgumentException("The email can't be null."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(null, createPersonAccountInfoDTO);
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
        createPersonAccountInfoDTO.setAccountDenomination(accountDenomination);
        createPersonAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.transformIntoCreatePersonAccountDTO(personEmail,createPersonAccountInfoDTO);

        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenThrow(new IllegalArgumentException("The email is not valid."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(personEmail,createPersonAccountInfoDTO);
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

        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setAccountDenomination(accountDenomination);
        createPersonAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.transformIntoCreatePersonAccountDTO(personEmail, createPersonAccountInfoDTO);

        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenThrow(new IllegalArgumentException("The email is not valid."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(personEmail, createPersonAccountInfoDTO);
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

        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setAccountDenomination(accountDenomination);
        createPersonAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.transformIntoCreatePersonAccountDTO(personEmail, createPersonAccountInfoDTO);

        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(personEmail, createPersonAccountInfoDTO);
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
        createPersonAccountInfoDTO.setAccountDenomination(accountDenomination);
        createPersonAccountInfoDTO.setAccountDescription(accountDescription);

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.transformIntoCreatePersonAccountDTO(personEmail, createPersonAccountInfoDTO);

        Mockito.when(service.createPersonAccount(createPersonAccountDTO)).thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(personEmail, createPersonAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");

    }


    @DisplayName("Test If User Account is created - Null info DTO")
    @Test
    void testIfUserAccountWasCreatedNullInfoDTO() {
        //Arrange
        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createPersonAccount(null)).thenThrow(new NullPointerException(null));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(null, null);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NullPointerException.class)
                .hasMessage(null);
    }

    /**
     * Test if an Account can be found by the ID
     */

    @Test
    @DisplayName("Test if an Account can be found by the ID - Happy Case")
    void getAccountByAccountID()  {

        AccountDTO accountDTOExpected = new AccountDTO(
                "MARGE@HOTMAIL.COM",
                "HOMER SNACKS",
                "MONEY SPENT ON SNACKS FOR HOMER");

        ResponseEntity <AccountDTO> expectedResponseEntity = new ResponseEntity<>(accountDTOExpected, HttpStatus.OK);

        Mockito.when(service.getAccountByAccountID("HOMER SNACKS","MARGE@HOTMAIL.COM" )).thenReturn(accountDTOExpected);

        //Act
        ResponseEntity<AccountDTO> actualResponseEntity = controller.getAccountByAccountID("HOMER SNACKS","MARGE@HOTMAIL.COM");

        //Assert
        assertEquals(expectedResponseEntity, actualResponseEntity);

    }

    @Test
    @DisplayName("Test if an Account can be found by the ID - Person Does Not Exists")
    void getAccountByAccountIDPersonDoesNotExists()  {

        Mockito.when(service.getAccountByAccountID("HOMER SNACKS","NOT_EXISTING_PERSON@GMAIL.COM" )).thenThrow(new ArgumentNotFoundException("No person found with that email."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.getAccountByAccountID("HOMER SNACKS","NOT_EXISTING_PERSON@GMAIL.COM");
        });

        //Assert
                assertThat(thrown)
                        .isExactlyInstanceOf(ArgumentNotFoundException.class)
                        .hasMessage("No person found with that email.");
    }

    @Test
    @DisplayName("Test if an Account can be found by the ID - Account Does Not Exists")
    void getAccountByAccountIDAccountDoesNotExists()  {

        Mockito.when(service.getAccountByAccountID("HOMERS","MARGE@GMAIL.COM" )).thenThrow(new ArgumentNotFoundException("No account found with that ID."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.getAccountByAccountID("HOMERS","MARGE@GMAIL.COM");
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No account found with that ID.");
    }


    @Test
    @DisplayName("Test if an Account can be found by the ID - Invalid Email")
    void getAccountByAccountIDInvalidEmail()  {


        Mockito.when(service.getAccountByAccountID("HOMER SNACKS","MARGEGMAIL.COM" )).thenThrow(new IllegalArgumentException("The email is not valid."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.getAccountByAccountID("HOMER SNACKS","MARGEGMAIL.COM");
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email is not valid.");
    }


    @Test
    @DisplayName("Test if an Account can be found by the ID - Email null")
    void getAccountByAccountIDEmailNull()  {


        Mockito.when(service.getAccountByAccountID("HOMER SNACKS",null)).thenThrow(new IllegalArgumentException("The email can't be null."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.getAccountByAccountID("HOMER SNACKS",null);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email can't be null.");
    }

    @Test
    @DisplayName("Test if an Account can be found by the ID - Email Empty")
    void getAccountByAccountIDEmailEmpty()  {

        Mockito.when(service.getAccountByAccountID("HOMER SNACKS","")).thenThrow(new IllegalArgumentException("The email is not valid."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.getAccountByAccountID("HOMER SNACKS","");
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email is not valid.");
    }

    @Test
    @DisplayName("Test if an Account can be found by the ID - Account Denomination Null")
    void getAccountByAccountIDDenominationNull()  {

        Mockito.when(service.getAccountByAccountID(null,"MARGE@GMAIL.COM")).thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.getAccountByAccountID(null,"MARGE@GMAIL.COM");
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");
    }

    @Test
    @DisplayName("Test if an Account can be found by the ID - Account Denomination Empty")
    void getAccountByAccountIDDenominationEmpty()  {

        Mockito.when(service.getAccountByAccountID("","MARGE@GMAIL.COM")).thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.getAccountByAccountID("","MARGE@GMAIL.COM");
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");
    }

    /**
     * Test if an Account can be found by the ID
     */

    @Test
    @DisplayName("Test to get all account by person ID - Happy Case")
    void getAccountsByPersonID()  {

        //Arrange:
        String personEmail = "1191782@isep.ipp.pt";

        Set<AccountDTO> expectedAccounts = new LinkedHashSet<>();
        expectedAccounts.add(new AccountDTO(personEmail,"Mbway","Friends"));
        expectedAccounts.add(new AccountDTO(personEmail,"CTT","Work"));
        expectedAccounts.add(new AccountDTO(personEmail,"Home","Home Expenses"));

        ResponseEntity<Object> responseEntityExpected =  new ResponseEntity<>(expectedAccounts, HttpStatus.OK);

        //Act
        Mockito.when(service.getAccountsByPersonID(personEmail)).thenReturn(expectedAccounts);

        ResponseEntity <Object> responseEntityResult = controller.getAccountsByPersonID(personEmail);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(responseEntityExpected, responseEntityResult),
                () -> assertEquals(HttpStatus.OK, responseEntityResult.getStatusCode()),
                () -> assertEquals(expectedAccounts, responseEntityResult.getBody()),
                () -> assertNotNull(responseEntityResult)
        );

    }


}
