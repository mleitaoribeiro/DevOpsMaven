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
import switch2019.project.DTO.deserializationDTO.CreateGroupAccountInfoDTO;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupAccountDTO;
import switch2019.project.applicationLayer.US007CreateGroupAccountService;
import switch2019.project.assemblers.AccountDTOAssembler;
import switch2019.project.controllerLayer.rest.US007CreateGroupAccountControllerRest;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest


class US007CreateGroupAccountControllerRestUnitTest {

    @Mock
    private US007CreateGroupAccountService service;

    @InjectMocks
    private US007CreateGroupAccountControllerRest controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * US007
     * As a group Admin, I want to create a group account
     */
    @Test
    @DisplayName("Test if group Account was created - Happy Case")
    void groupAccountWasCreatedHappyCase() {

        //Arrange
        String personEmail = "1110120@isep.ipp.pt";
        String groupDescription = "SWitCH";
        String accountDenomination = "Vet";
        String accountDescription = "Veterinarian dispenses";

        //Arrange of CreateGroupAccountInfoDTO & CreateGroupAccountDTO
        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO.setPersonEmail(personEmail);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.
                transformToCreateGroupAccountDTO(groupDescription, groupAccountInfoDTO);

        //Expected Results: AccountDTO and ResponseEntity
        AccountDTO accountExpectedDTO = new AccountDTO(groupDescription, accountDenomination, accountDescription);
        ResponseEntity responseEntityExpected = new ResponseEntity<>(accountExpectedDTO, HttpStatus.CREATED);

        Mockito.when(service.createGroupAccount(accountControllerDTO)).thenReturn(accountExpectedDTO);

        //Act
        ResponseEntity<AccountDTO> responseEntityResult = controller.createGroupAccount(groupDescription, groupAccountInfoDTO);

        //Assert
        assertEquals(responseEntityExpected, responseEntityResult);
    }

    @Test
    @DisplayName("Test if group Account was created - Person is Member but not Admin")
    void groupAccountWasCreatedNotAdmin() {

        //Arrange
        String personEmail = "beatriz.azevedo@gmail.com";
        String groupDescription = "Friends";
        String accountDenomination = "Vet";
        String accountDescription = "Veterinarian dispenses";

        //Arrange of CreateGroupAccountInfoDTO & CreateGroupAccountDTO
        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO.setPersonEmail(personEmail);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.
                transformToCreateGroupAccountDTO(groupDescription, groupAccountInfoDTO);

        //Expected Results: AccountDTO and ResponseEntity
        AccountDTO accountExpectedDTO = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        Mockito.when(service.createGroupAccount(accountControllerDTO)).
                thenThrow(new IllegalArgumentException("This person is not admin of this group."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupAccount(groupDescription, groupAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This person is not admin of this group.");
    }

    @Test
    @DisplayName("Test if group Account was created - Person is Admin but not of this group")
    void groupAccountWasCreatedNotAdminOfRightGroup() {

        //Arrange
        String personEmail = "hugo.azevedo@gmail.com";
        String groupDescription = "Family Simpson";
        String accountDenomination = "Vet";
        String accountDescription = "Veterinarian dispenses";

        //Arrange of CreateGroupAccountInfoDTO & CreateGroupAccountDTO
        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO.setPersonEmail(personEmail);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.
                transformToCreateGroupAccountDTO(groupDescription, groupAccountInfoDTO);

        //Expected Results: AccountDTO and ResponseEntity
        AccountDTO accountExpectedDTO = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        Mockito.when(service.createGroupAccount(accountControllerDTO)).
                thenThrow(new IllegalArgumentException("This person is not member of this group."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupAccount(groupDescription, groupAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This person is not member of this group.");
    }

    @Test
    @DisplayName("Test if group Account was created - Group account already exists")
    void groupAccountWasCreatedGroupAccountAlreadyExists() {

        //Arrange
        String personEmail = "1191755@isep.ipp.pt";
        String groupDescription = "SWitCH";
        String accountDenomination = "Grocery";
        String accountDescription = "Grocery dispenses";

        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        groupAccountInfoDTO.setPersonEmail(personEmail);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.createGroupAccountDTOFromPrimitiveTypes(personEmail,
                groupDescription, accountDenomination, accountDescription);

        Mockito.when(service.createGroupAccount(accountControllerDTO)).
                thenThrow(new IllegalArgumentException("This account already exists."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupAccount(groupDescription, groupAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This account already exists.");

    }


    @Test
    @DisplayName("Test if group Account was created - Person doesn't exist in the Person Repository")
    void groupAccountWasCreatedPersonExistsInThePersonRepository() {

        //Arrange
        String personEmail = "maria.silva@gmail.com";
        String groupDescription = "SWitCH";
        String accountDenomination = "Grocery";
        String accountDescription = "Grocery dispenses";

        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        groupAccountInfoDTO.setPersonEmail(personEmail);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.createGroupAccountDTOFromPrimitiveTypes(personEmail,
                groupDescription, accountDenomination, accountDescription);

        Mockito.when(service.createGroupAccount(accountControllerDTO)).
                thenThrow(new IllegalArgumentException("No person found with that email."));


        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupAccount(groupDescription, groupAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No person found with that email.");

    }

    @Test
    @DisplayName("Test if group Account was created - Group doesn't exist in the Groups Repository")
    void groupAccountWasCreatedGroupExistsInTheGroupRepository() {

        //Arrange
        String personEmail = "1191755@isep.ipp.pt";
        String groupDescription = "Amigos";
        String accountDenomination = "Grocery";
        String accountDescription = "Grocery dispenses";

        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        groupAccountInfoDTO.setPersonEmail(personEmail);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.createGroupAccountDTOFromPrimitiveTypes(personEmail,
                groupDescription, accountDenomination, accountDescription);

        Mockito.when(service.createGroupAccount(accountControllerDTO)).
                thenThrow(new IllegalArgumentException("No group found with that description."));


        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupAccount(groupDescription, groupAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No group found with that description.");

    }

    @Test
    @DisplayName("Test if group Account was created - email invalid - null")
    void groupAccountWasCreatedEmailNull() {

        //Arrange
        String personEmail = null;
        String groupDescription = "Friends";
        String accountDenomination = "Vet";
        String accountDescription = "Veterinarian dispenses";

        //Arrange of CreateGroupAccountInfoDTO & CreateGroupAccountDTO
        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO.setPersonEmail(personEmail);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.
                transformToCreateGroupAccountDTO(groupDescription, groupAccountInfoDTO);

        Mockito.when(service.createGroupAccount(accountControllerDTO)).
                thenThrow(new IllegalArgumentException("The email can't be null."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupAccount(groupDescription, groupAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email can't be null.");
    }

    @Test
    @DisplayName("Test if group Account was created - email invalid - empty")
    void groupAccountWasCreatedEmailEmpty() {

        //Arrange
        String personEmail = "";
        String groupDescription = "Friends";
        String accountDenomination = "Vet";
        String accountDescription = "Veterinarian dispenses";

        //Arrange of CreateGroupAccountInfoDTO & CreateGroupAccountDTO
        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO.setPersonEmail(personEmail);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.
                transformToCreateGroupAccountDTO(groupDescription, groupAccountInfoDTO);

        Mockito.when(service.createGroupAccount(accountControllerDTO)).
                thenThrow(new IllegalArgumentException("The email is not valid."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupAccount(groupDescription, groupAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email is not valid.");
    }

    @Test
    @DisplayName("Test if group Account was created - email invalid - invalid format")
    void groupAccountWasCreatedEmailInvalidFormat() {

        //Arrange
        String personEmail = "beatriz.azevedogmail.com";
        String groupDescription = "Friends";
        String accountDenomination = "Vet";
        String accountDescription = "Veterinarian dispenses";

        //Arrange of CreateGroupAccountInfoDTO & CreateGroupAccountDTO
        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO.setPersonEmail(personEmail);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.
                transformToCreateGroupAccountDTO(groupDescription, groupAccountInfoDTO);

        Mockito.when(service.createGroupAccount(accountControllerDTO)).
                thenThrow(new IllegalArgumentException("The email is not valid."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupAccount(groupDescription, groupAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email is not valid.");
    }

    @Test
    @DisplayName("Test if group Account was created - account invalid - null")
    void groupAccountWasCreatedAccountNull() {

        //Arrange
        String personEmail = "beatriz.azevedo@gmail.com";
        String groupDescription = "Friends";
        String accountDenomination = null;
        String accountDescription = "Veterinarian dispenses";

        //Arrange of CreateGroupAccountInfoDTO & CreateGroupAccountDTO
        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();
        groupAccountInfoDTO.setPersonEmail(personEmail);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.
                transformToCreateGroupAccountDTO(groupDescription, groupAccountInfoDTO);

        Mockito.when(service.createGroupAccount(accountControllerDTO)).
                thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupAccount(groupDescription, groupAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");
    }

    @Test
    @DisplayName("Test if group Account was created - account invalid - empty")
    void groupAccountWasCreatedAccountEmpty() {

        //Arrange
        String personEmail = "1191755@isep.ipp.pt";
        String groupDescription = "Switch";
        String accountDenomination = "";
        String accountDescription = "Grocery dispenses";

        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        groupAccountInfoDTO.setPersonEmail(personEmail);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.
                transformToCreateGroupAccountDTO(groupDescription, groupAccountInfoDTO);

        Mockito.when(service.createGroupAccount(accountControllerDTO)).
                thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));


        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupAccount(groupDescription, groupAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");


    }

    /**
     * Testing if all accounts associated with a GroupID can be retrieved
     */
    @Test
    @DisplayName("Test getting all accounts associated with a GroupID")
    void getAccountsByGroupID() {

        //ARRANGE:
        //Arrange the Group Description:
        String groupDescription = "Rick and Morty";

        //Arrange the expected set of AccountDTOs with the associated accounts:
        Set<AccountDTO> expectedGroupAccounts = new LinkedHashSet<>();
        expectedGroupAccounts.add(new AccountDTO(groupDescription, "Money for Morty", "Money to compensate morty"));
        expectedGroupAccounts.add(new AccountDTO(groupDescription, "Fuel", "Ship fuel station"));
        expectedGroupAccounts.add(new AccountDTO(groupDescription, "Alcohol", "Important for adventures"));

        //Arrange the expected response entity
        ResponseEntity<Object> responseEntityExpected = new ResponseEntity<>(expectedGroupAccounts, HttpStatus.OK);

        //ACT:
        Mockito.when(service.getAccountsByGroupID(groupDescription)).thenReturn(expectedGroupAccounts);

        ResponseEntity<Object> responseEntityResult = controller.getAccountsByGroupID(groupDescription);

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(responseEntityExpected, responseEntityResult),
                () -> assertEquals(HttpStatus.OK, responseEntityResult.getStatusCode())
        );
    }


}