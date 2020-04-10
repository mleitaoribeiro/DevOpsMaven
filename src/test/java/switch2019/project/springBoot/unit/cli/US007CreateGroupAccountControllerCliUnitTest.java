package switch2019.project.springBoot.unit.cli;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2019.project.DTO.SerializationDTO.AccountDTO;
import switch2019.project.DTO.ServiceDTO.CreateGroupAccountDTO;
import switch2019.project.assemblers.AccountDTOAssembler;
import switch2019.project.controllerLayer.controllersCli.US007CreateGroupAccountController;
import switch2019.project.applicationLayer.US007CreateGroupAccountService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class US007CreateGroupAccountControllerCliUnitTest {

    @Mock
    @Autowired
    private US007CreateGroupAccountService service;
    @Autowired
    private US007CreateGroupAccountController controller;

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
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        //Arrange of CreateGroupAccountDTO
        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.createGroupAccountDTOFromPrimitiveTypes
                (personEmail,groupDescription, accountDenomination, accountDescription);

        //Expected Result AccountDTO
        AccountDTO accountExpectedDTO = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroupAccount(accountControllerDTO)).thenReturn(accountExpectedDTO);

        //Act
        AccountDTO accountCreatedDTO = controller.createGroupAccount(personEmail, groupDescription,
               accountDenomination, accountDescription);

        //Assert
        assertEquals(accountExpectedDTO, accountCreatedDTO);
    }

    @Test
    @DisplayName("Test if group Account was created - Person is Member but not Admin")
    void groupAccountWasCreatedNotAdmin() {

        //Arrange
        String personEmail = "beatriz.azevedo@gmail.com";
        String groupDescription = "Friends";
        String accountDenomination = "Vet";
        String accountDescription = "Veterinarian dispenses";

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.createGroupAccountDTOFromPrimitiveTypes
                (personEmail,groupDescription, accountDenomination, accountDescription);

        AccountDTO accountExpectedDTO = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroupAccount(accountControllerDTO)).thenReturn(accountExpectedDTO);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupAccount(personEmail, groupDescription,
                    accountDenomination, accountDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This person is not Admin of this group");
    }

    @Test
    @DisplayName("Test if group Account was created - Person is Admin but not of this group")
    void groupAccountWasCreatedNotAdminOfRightGroup() {

        //Arrange
        String personEmail = "hugo.azevedo@gmail.com";
        String groupDescription = "Family Simpson";
        String accountDenomination = "Vet";
        String accountDescription = "Veterinarian dispenses";

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.createGroupAccountDTOFromPrimitiveTypes
                (personEmail,groupDescription, accountDenomination, accountDescription);

        AccountDTO accountExpectedDTO = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroupAccount(accountControllerDTO)).thenReturn(accountExpectedDTO);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupAccount(personEmail, groupDescription,
                    accountDenomination, accountDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This person is not Member of this group");
    }

    @Test
    @DisplayName("Test if group Account was created - Group account already exists")
    void groupAccountWasCreatedGroupAccountAlreadyExists() {

        //Arrange
        String personEmail = "1110120@isep.ipp.pt";
        String groupDescription = "Family Cardoso";
        String accountDenomination = "Revolut";
        String accountDescription = "Online Expenses";

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.createGroupAccountDTOFromPrimitiveTypes
                (personEmail, groupDescription, accountDenomination, accountDescription);

        AccountDTO accountExpectedDTO = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroupAccount(accountControllerDTO)).thenReturn(accountExpectedDTO);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupAccount(personEmail, groupDescription,
                    accountDenomination, accountDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("This account already exists.");
    }

    @Test
    @DisplayName("Test if group Account was created - Person doesn't exist in the Person Repository")
    void groupAccountWasCreatedPersonExistsInThePersonRepository(){

        //Arrange
        String personEmail = "maria.silva@gmail.com";
        String groupDescription = "SWitCH";
        String accountDenomination = "Grocery";
        String accountDescription = "Grocery dispenses";

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.createGroupAccountDTOFromPrimitiveTypes
                (personEmail,groupDescription, accountDenomination, accountDescription);

        AccountDTO accountExpectedDTO = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroupAccount(accountControllerDTO)).thenReturn(accountExpectedDTO);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupAccount(personEmail, groupDescription,
                    accountDenomination, accountDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No person found with that email.");
    }

    @Test
    @DisplayName("Test if group Account was created - Group doesn't exist in the Groups Repository")
    void groupAccountWasCreatedGroupExistsInTheGroupRepository(){

        //Arrange
        String personEmail = "1191755@isep.ipp.pt";
        String groupDescription = "Amigos";
        String accountDenomination = "Grocery";
        String accountDescription = "Grocery dispenses";

        CreateGroupAccountDTO accountControllerDTO = AccountDTOAssembler.createGroupAccountDTOFromPrimitiveTypes
                (personEmail,groupDescription, accountDenomination, accountDescription);

        AccountDTO accountExpectedDTO = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        MockitoAnnotations.initMocks(this);
        Mockito.when(service.createGroupAccount(accountControllerDTO)).thenReturn(accountExpectedDTO);

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupAccount(personEmail, groupDescription,
                    accountDenomination, accountDescription);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No group found with that description.");
    }
}