package switch2019.project.applicationLayer.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serviceDTO.CreatePersonAccountDTO;
import switch2019.project.applicationLayer.US006CreatePersonAccountService;
import switch2019.project.customExceptions.ArgumentNotFoundException;
import switch2019.project.customExceptions.ResourceAlreadyExistsException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class US006CreatePersonAccountServiceTest {

    @Autowired
    private US006CreatePersonAccountService service;

    @Test
    @DisplayName("Test If several accounts are created for an existing Person - Main Scenario")
    void testIfPersonAccountIsCreatedMainScenario() {
        //Arrange
        String personEmail = "jerry.smith@gmail.com";
        String accountDenomination1 = "Revolut";
        String accountDescription1 = "OnlineShopping";
        String accountDenomination2 = "Active";
        String accountDescription2 = "For sharing expenses";
        String accountDenomination3 = "CXG";
        String accountDescription3 = "Allowance paychecks";

        CreatePersonAccountDTO createPersonAccountDTO1 = new CreatePersonAccountDTO(personEmail, accountDenomination1, accountDescription1);
        AccountDTO accountDTOExpected1 = new AccountDTO(personEmail, accountDenomination1, accountDescription1);

        CreatePersonAccountDTO createPersonAccountDTO2 = new CreatePersonAccountDTO(personEmail, accountDenomination2, accountDescription2);
        AccountDTO accountDTOExpected2 = new AccountDTO(personEmail, accountDenomination2, accountDescription2);

        CreatePersonAccountDTO createPersonAccountDTO3 = new CreatePersonAccountDTO(personEmail, accountDenomination3, accountDescription3);
        AccountDTO accountDTOExpected3 = new AccountDTO(personEmail, accountDenomination3, accountDescription3);

        //Act
        AccountDTO accountDTOCreated1 = service.createPersonAccount(createPersonAccountDTO1);
        AccountDTO accountDTOCreated2 = service.createPersonAccount(createPersonAccountDTO2);
        AccountDTO accountDTOCreated3 = service.createPersonAccount(createPersonAccountDTO3);

        //Assert

        Assertions.assertAll(
                () -> assertEquals(accountDTOExpected1, accountDTOCreated1),
                () -> assertEquals(accountDTOExpected2, accountDTOCreated2),
                () -> assertEquals(accountDTOExpected3, accountDTOCreated3)
        );

    }

    @Test
    @DisplayName("Test If User Account is Created - person ID does not exists in Repository")
    void testIfAccountIsCreateNonExistingID() {
        //Arrange
        String personEmail = "carolina.dias@live.com.pt";
        String accountDenomination = "Revolut";
        String accountDescription = "OnlineShopping";

        CreatePersonAccountDTO createPersonAccountDTO1 = new CreatePersonAccountDTO(personEmail, accountDenomination, accountDescription);

        //Act
        try {
            service.createPersonAccount(createPersonAccountDTO1);
        }

        //Assert
        catch (ArgumentNotFoundException invalid) {
            assertEquals("No person found with that email.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If User Account is Created - account already exists on Repository")
    void testIfAccountIsNotCreatedWhenAlreadyExists() {
        //Arrange
        String personEmail = "1191780@isep.ipp.pt";
        String accountDenomination = "Mbway";
        String accountDescription = "Rides";
        String result = "";

        CreatePersonAccountDTO createPersonAccountDTO1 = new CreatePersonAccountDTO(personEmail, accountDenomination, accountDescription);

        try {
            service.createPersonAccount(createPersonAccountDTO1);
        } catch (ResourceAlreadyExistsException invalid) {
            result = invalid.getMessage();
        }
        //Assert
        assertEquals("This account already exists.", result);

    }


    @Test
    @DisplayName("Test If User Account is created with an existing Person - Main Scenario")
    void testIfPersonAccountIsCreated() {
        //Arrange
        String personEmail = "1191780@isep.ipp.pt";
        String accountDenomination = "ISEP Santander";
        String accountDescription = "Papelaria";

        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(personEmail, accountDenomination, accountDescription);

        AccountDTO accountDTOExpected = new AccountDTO(personEmail, accountDenomination, accountDescription);

        //Act
        AccountDTO accountDTOCreated = service.createPersonAccount(createPersonAccountDTO);

        //Assert
        assertEquals(accountDTOExpected, accountDTOCreated);
    }

    @Test
    @DisplayName("Test If User Account is Created - person ID null")
    void testIfAccountIsCreateNullID() {
        //Arrange
        String accountDenomination = "Revolut";
        String accountDescription = "OnlineShopping";

        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(null, accountDenomination, accountDescription);

        //Act
        try {
            service.createPersonAccount(createPersonAccountDTO);
        }

        //Assert
        catch (IllegalArgumentException invalid) {
            assertEquals("The email can't be null.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If User Account is Created - Email is Empty")
    void testIfAccountIsCreateEmptyID() {
        //Arrange
        String personEmail = "";
        String accountDenomination = "Revolut";
        String accountDescription = "OnlineShopping";

        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(personEmail, accountDenomination, accountDescription);

        //Act
        try {
            service.createPersonAccount(createPersonAccountDTO);
        }

        //Assert
        catch (IllegalArgumentException invalid) {
            assertEquals("The email is not valid.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If User Account is Created - Null Denomination")
    void testIfAccountIsCreateNullDenomination() {
        //Arrange
        String personEmail = "roberto@isep.ipp.pt";
        String accountDescription = "OnlineShopping";

        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(personEmail, null, accountDescription);

        //Act
        try {
            service.createPersonAccount(createPersonAccountDTO);
        }

        //Assert
        catch (IllegalArgumentException invalid) {
            assertEquals("The denomination can't be null or empty.", invalid.getMessage());
        }
    }

    @Test
    @DisplayName("Test If User Account is Created - Null Description")
    void testIfAccountIsCreateNullDescription() {
        //Arrange
        String personEmail = "roberto@isep.ipp.pt";
        String accountDenomination = "Revolut";

        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(personEmail, accountDenomination, null);

        //Act
        try {
            service.createPersonAccount(createPersonAccountDTO);

            //Assert
        } catch (IllegalArgumentException invalid) {
            assertEquals("The description can't be null or empty.", invalid.getMessage());
        }
    }

    /**
     * Test if an Account can be found by the ID
     */

    @Test
    @DisplayName("Test if an Account can be found by the ID - Happy Case")
    void getAccountByAccountID() {
        //Arrange:
        String personEmail = "marge@hotmail.com";
        String accountDenomination = "Homer Snacks";
        String accountDescription = "Money spent on snacks for homer";

        //Arrangement of the output DTO:
        AccountDTO expectedOutput = new AccountDTO(personEmail, accountDenomination, accountDescription);

        //Act:
        AccountDTO realOutput = service.getAccountByAccountID(accountDenomination, personEmail);

        //Assert:
        assertEquals(expectedOutput, realOutput);
    }




}

