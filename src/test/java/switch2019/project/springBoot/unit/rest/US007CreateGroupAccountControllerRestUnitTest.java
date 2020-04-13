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
import switch2019.project.DTO.DeserializationDTO.CreateGroupAccountInfoDTO;
import switch2019.project.DTO.SerializationDTO.AccountDTO;
import switch2019.project.DTO.ServiceDTO.CreateGroupAccountDTO;
import switch2019.project.applicationLayer.US007CreateGroupAccountService;
import switch2019.project.assemblers.AccountDTOAssembler;
import switch2019.project.controllerLayer.controllersRest.US007CreateGroupAccountControllerRest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class US007CreateGroupAccountControllerRestUnitTest {

    @Mock
    private US007CreateGroupAccountService service;
    @Autowired
    private US007CreateGroupAccountControllerRest controller;

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
        groupAccountInfoDTO.setGroupDescription(groupDescription);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.
                transformToCreateGroupAccountDTO(groupAccountInfoDTO);

        //Expected Results: AccountDTO and ResponseEntity
        AccountDTO accountExpectedDTO = new AccountDTO(groupDescription, accountDenomination, accountDescription);
        ResponseEntity responseEntityExpected = new ResponseEntity<>(accountExpectedDTO, HttpStatus.CREATED);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroupAccount(accountControllerDTO)).thenReturn(accountExpectedDTO);

        //Act
        ResponseEntity<AccountDTO> responseEntityResult = controller.addGroupAccount(groupAccountInfoDTO);

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
        groupAccountInfoDTO.setGroupDescription(groupDescription);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.
                transformToCreateGroupAccountDTO(groupAccountInfoDTO);

        //Expected Results: AccountDTO and ResponseEntity
        AccountDTO accountExpectedDTO = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroupAccount(accountControllerDTO)).thenReturn(accountExpectedDTO);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addGroupAccount(groupAccountInfoDTO);
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
        groupAccountInfoDTO.setGroupDescription(groupDescription);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.
                transformToCreateGroupAccountDTO(groupAccountInfoDTO);

        //Expected Results: AccountDTO and ResponseEntity
        AccountDTO accountExpectedDTO = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroupAccount(accountControllerDTO)).thenReturn(accountExpectedDTO);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addGroupAccount(groupAccountInfoDTO);
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
        groupAccountInfoDTO.setGroupDescription(groupDescription);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.createGroupAccountDTOFromPrimitiveTypes(personEmail,
                groupDescription, accountDenomination, accountDescription);

        AccountDTO accountExpectedDTO = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroupAccount(accountControllerDTO)).thenReturn(accountExpectedDTO);

        controller.addGroupAccount(groupAccountInfoDTO);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addGroupAccount(groupAccountInfoDTO);
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
        groupAccountInfoDTO.setGroupDescription(groupDescription);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.createGroupAccountDTOFromPrimitiveTypes(personEmail,
                groupDescription, accountDenomination, accountDescription);

        AccountDTO accountExpectedDTO = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroupAccount(accountControllerDTO)).thenReturn(accountExpectedDTO);


        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addGroupAccount(groupAccountInfoDTO);
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
        groupAccountInfoDTO.setGroupDescription(groupDescription);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.createGroupAccountDTOFromPrimitiveTypes(personEmail,
                groupDescription, accountDenomination, accountDescription);

        AccountDTO accountExpectedDTO = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroupAccount(accountControllerDTO)).thenReturn(accountExpectedDTO);


        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addGroupAccount(groupAccountInfoDTO);
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
        groupAccountInfoDTO.setGroupDescription(groupDescription);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.
                transformToCreateGroupAccountDTO(groupAccountInfoDTO);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroupAccount(accountControllerDTO)).
                thenThrow(new IllegalArgumentException("The email can't be null."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addGroupAccount(groupAccountInfoDTO);
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
        groupAccountInfoDTO.setGroupDescription(groupDescription);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.
                transformToCreateGroupAccountDTO(groupAccountInfoDTO);

        //Expected Results: AccountDTO and ResponseEntity
        AccountDTO accountExpectedDTO = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroupAccount(accountControllerDTO)).thenReturn(accountExpectedDTO);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addGroupAccount(groupAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email it's not valid.");
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
        groupAccountInfoDTO.setGroupDescription(groupDescription);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.
                transformToCreateGroupAccountDTO(groupAccountInfoDTO);

        //Expected Results: AccountDTO and ResponseEntity
        AccountDTO accountExpectedDTO = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroupAccount(accountControllerDTO)).thenReturn(accountExpectedDTO);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addGroupAccount(groupAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email it's not valid.");
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
        groupAccountInfoDTO.setGroupDescription(groupDescription);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.
                transformToCreateGroupAccountDTO(groupAccountInfoDTO);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroupAccount(accountControllerDTO)).
                thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addGroupAccount(groupAccountInfoDTO);
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
        groupAccountInfoDTO.setGroupDescription(groupDescription);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.createGroupAccountDTOFromPrimitiveTypes(personEmail,
                groupDescription, accountDenomination, accountDescription);

        AccountDTO accountExpectedDTO = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroupAccount(accountControllerDTO)).thenReturn(accountExpectedDTO);


        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addGroupAccount(groupAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");


    }

    @Test
    @DisplayName("Test if group Account was created - groupDescription invalid - null")
    void groupAccountWasCreatedGroupDescriptionNull() {

        //Arrange
        String personEmail = "1191755@isep.ipp.pt";
        String groupDescription = null;
        String accountDenomination = "Grocery";
        String accountDescription = "Grocery dispenses";

        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        groupAccountInfoDTO.setPersonEmail(personEmail);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.createGroupAccountDTOFromPrimitiveTypes(personEmail,
                groupDescription, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroupAccount(accountControllerDTO)).
                thenThrow(new IllegalArgumentException("The description can't be null or empty."));


        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addGroupAccount(groupAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");

    }

    @Test
    @DisplayName("Test if group Account was created - groupDescription invalid - empty")
    void groupAccountWasCreatedGroupDescriptionEmpty() {

        //Arrange
        String personEmail = "1191755@isep.ipp.pt";
        String groupDescription = "";
        String accountDenomination = "Grocery";
        String accountDescription = "Grocery dispenses";

        CreateGroupAccountInfoDTO groupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        groupAccountInfoDTO.setPersonEmail(personEmail);
        groupAccountInfoDTO.setAccountDenomination(accountDenomination);
        groupAccountInfoDTO.setAccountDescription(accountDescription);

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.createGroupAccountDTOFromPrimitiveTypes(personEmail,
                groupDescription, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroupAccount(accountControllerDTO)).
                thenThrow(new IllegalArgumentException("The description can't be null or empty."));


        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.addGroupAccount(groupAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");

    }
}