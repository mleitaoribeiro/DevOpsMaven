package switch2019.project.applicationLayer.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupAccountDTO;
import switch2019.project.applicationLayer.US007CreateGroupAccountService;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.NoPermissionException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class US007CreateGroupAccountServiceTest {

    @Autowired
    private US007CreateGroupAccountService service;

    @Test
    @DisplayName("Test If group Account is created - Happy Case - Main Scenario")
    void testIfGroupAccountWasCreatedHappyCase() {

        //Arrange
        String creatorEmail = "rick@gmail.com";
        String groupDescription = "Smith Family";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        AccountDTO expected = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        //Act
        AccountDTO accountCreated = service.createGroupAccount(createGroupAccountDTO);

        //Assert
        assertEquals(expected, accountCreated);
    }

    /**
     * Test If group Account is created - Failing scenarios
     */

    @Test
    @DisplayName("Test if Group Account is created - Person doesn't exit in Person Repository")
    void testIfGroupAccountWasCreatedPersonNotExists() {

        //Arrange
        String creatorEmail = "miguel@gmail.com";
        String groupDescription = "Isep";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        try {
            service.createGroupAccount(createGroupAccountDTO);
        } catch (ArgumentNotFoundException invalid) {
            //Assert
            assertEquals("No person found with that email.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test if Group Account is created - Group doesn't exist in Group Repository")
    void testIfSeveralGroupAccountsWereCreatedGroupDoNotExists() {

        //Arrange
        String creatorEmail = "beth.smith@gmail.com";
        String oneDescription = "xpto";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, oneDescription,
                accountDenomination, accountDescription);

        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);
        } catch (ArgumentNotFoundException invalid) {
            //Assert
            assertEquals("No group found with that description.", invalid.getMessage());
        }
    }

    /**
     * Test If group Account is created - Several accounts added - Null & Empty Values
     */

    @Test
    @DisplayName("Test If group Account is created - Email null")
    void testIfGroupAccountWasCreatedPersonIDNull() {

        //Arrange
        String groupDescription = "SWitCH";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(null, groupDescription,
                accountDenomination, accountDescription);

        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The email can't be null.", invalid.getMessage());

        }
    }

    @Test
    @DisplayName("Test If group Account is created - Email Empty")
    void testIfGroupAccountWasCreatedPersonEmailEmpty() {

        //Arrange
        String creatorEmail = "";
        String groupDescription = "SWitCH";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The email is not valid.", invalid.getMessage());

        }
    }

    @Test
    @DisplayName("Test If group Account is created - Group ID null")
    void testIfGroupAccountWasCreatedGroupIDNull() {

        //Arrange
        String creatorEmail = "1191765@isep.ipp.pt";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, null,
                accountDenomination, accountDescription);

        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The description can't be null or empty.", invalid.getMessage());
        }

    }

    @Test
    @DisplayName("Test If group Account is created - Account denomination null")
    void testIfGroupAccountWasCreatedAccountDenominationNull() {

        //Arrange
        String creatorEmail = "1191765@isep.ipp.pt";
        String groupDescription = "Split Expenses";
        String accountDescription = "Pokemon Game";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                null, accountDescription);

        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The denomination can't be null or empty.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Account denomination Empty")
    void testIfGroupAccountWasCreatedAccountDenominationEmpty() {

        //Arrange
        String creatorEmail = "maria.cardoso_1@gmail.com";
        String groupDescription = "Family Cardoso";
        String accountDenomination = "";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The denomination can't be null or empty.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Account description null")
    void testIfGroupAccountWasCreatedAccountDescriptionNull() {

        //Arrange
        String creatorEmail = "roberto@gmail.com";
        String groupDescription = "Family Azevedo";
        String accountDenomination = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, null);

        //Act
        try {

            service.createGroupAccount(createGroupAccountDTO);
        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The description can't be null or empty.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Account description Empty")
    void testIfGroupAccountWasCreated_AccountDescriptionEmpty() {

        //Arrange
        String creatorEmail = "roberto@gmail.com";
        String groupDescription = "Family Azevedo";
        String accountDenomination = "Online Shopping";
        String accountDescription = "";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);

        } catch (IllegalArgumentException invalid) {
            //Assert
            assertEquals("The description can't be null or empty.", invalid.getMessage());
        }
    }


    /**
     * Test If group Account is created -  Failing scenarios - Simple Tests
     */

    @Test
    @DisplayName("Test If group Account is created - Person it´s Member but not Admin")
    void testIfGroupAccountWasCreatedNotAdmin() {

        //Arrange
        String creatorEmail = "maria@gmail.com";
        String groupDescription = "Family Azevedo";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);
        } catch (NoPermissionException invalid) {
            //Assert
            assertEquals("This person is not admin of this group.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If group Account is created - Person it´s not a Member")
    void testIfGroupAccountWasCreatedNotGroupMember() {

        //Arrange
        String creatorEmail = "rick@gmail.com";
        String groupDescription = "Family Simpson";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);
        } catch (NoPermissionException invalid) {
            //Assert
            assertEquals("This person is not member of this group.", invalid.getMessage());
        }

    }


    @Test
    @DisplayName("Test If group Account is created - False - Account already exists")
    void testIfGroupAccountWasCreatedAccountAlreadyExists() {

        //Arrange
        String creatorEmail = "homer@hotmail.com";
        String groupDescription = "Family Simpson";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(creatorEmail, groupDescription,
                accountDenomination, accountDescription);

        service.createGroupAccount(createGroupAccountDTO);

        //Act
        try {
            service.createGroupAccount(createGroupAccountDTO);
        } catch (ResourceAlreadyExistsException invalid) {
            //Assert
            assertEquals("This account already exists.", invalid.getMessage());
        }
    }

    /**
     * Test if an Account can be found by the ID
     */

    @Test
    @DisplayName("Test if an Account can be found by the ID - Happy Case")
    void getAccountByAccountID() {
        //Arrange:
        String groupDescription = "family cardoso";
        String accountDenomination = "Revolut";
        String accountDescription = "Online Expenses";

        //Arrangement of the output DTO:
        AccountDTO expectedOutput = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        //Act:
        AccountDTO realOutput = service.getAccountByAccountID(accountDenomination, groupDescription);

        //Assert:
        assertEquals(expectedOutput, realOutput);
    }

    /**
     * Test if all the Accounts associated with a GroupID can be retrieved
     */
    @Test
    @DisplayName("Happy Case")
    void getAllAccountsByGroupIDHappyCaseTest() {

        //ARRANGE:
        //arrange the account description:
        String groupDescription = "Rick and Morty";

        //Arrange all the required DTOs in the expected list:
        Set<AccountDTO> expectedResult = new LinkedHashSet<>();
        expectedResult.add(new AccountDTO(groupDescription, "Money for Morty", "Money to compensate morty"));
        expectedResult.add(new AccountDTO(groupDescription, "Fuel", "Ship fuel station"));
        expectedResult.add(new AccountDTO(groupDescription, "Alcohol", "Important for adventures"));

        //ACT:
        Set<AccountDTO> actualResult = service.getAccountsByGroupID(groupDescription);

        //ASSERT:
        assertEquals(expectedResult,actualResult);
    }



}