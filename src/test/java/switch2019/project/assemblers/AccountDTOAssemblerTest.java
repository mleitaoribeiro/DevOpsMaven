package switch2019.project.assemblers;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupAccountDTO;
import switch2019.project.DTO.serviceDTO.CreatePersonAccountDTO;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;

import static org.junit.jupiter.api.Assertions.*;

class AccountDTOAssemblerTest {

    /**
     * Tests for validate creation of DTO from Domain Object:
     *
     */

    @Test
    @DisplayName("Test create Group AccountDTO From Domain Object - Happy Case")
    void createAccountDTO() {
        //Arrange
        String email = "email@email.pt";
        String denominationString = "Conta do Alex";
        String descriptionString = "Conta poupança";

        Denomination denomination = new Denomination(denominationString);
        PersonID personID = new PersonID(new Email(email));
        Description description = new Description(descriptionString);

        Account account = new Account(denomination, description, personID);
        AccountDTO expected = new AccountDTO(email, denominationString, descriptionString);
        AccountDTO unexpected = new AccountDTO("email@falso.pt", denominationString, descriptionString);

        //Act
        AccountDTO actual = AccountDTOAssembler.createAccountDTOFromDomainObject(account);

        // Assert
        Assertions.assertAll(
                () ->  assertEquals(expected, actual),
                () ->  assertNotEquals(unexpected, actual)
        );
    }

    /**
     * Tests for validate creation of DTO for a Group Account
     *
     */

    @Test
    @DisplayName("Test create Group AccountDTO From PrimitiveTypes")
    void createCreateGroupAccountDTOFromPrimitiveTypes() {
        //Arrange
        String email = "email@email.pt";
        String denominationString = "Conta do Alex";
        String descriptionString = "Conta poupança";
        String groupDescription = "Grupo";


        CreateGroupAccountDTO expected = new CreateGroupAccountDTO(email, groupDescription, denominationString, descriptionString);
        CreateGroupAccountDTO unexpected = new CreateGroupAccountDTO("email@falso.pt", groupDescription, denominationString, descriptionString);
        //Act
        CreateGroupAccountDTO actual = AccountDTOAssembler.createGroupAccountDTOFromPrimitiveTypes(email, groupDescription,
                denominationString, descriptionString);

        // Assert
        Assertions.assertAll(
                () ->  assertEquals(expected, actual),
                () ->  assertNotEquals(unexpected, actual)
        );
    }

    /**
     * Tests for validate creation of DTO for a Person Account:
     *
     */

    @Test
    @DisplayName("Test to validate creation of Person AccountDTO From PrimitiveTypes - Happy Case")
    void createPersonAccountDTOFromPrimitiveTypes() {
        //Arrange
        String email = "raquel@xpto.com";
        String accountDenomination = "House Account";
        String accountDescription = "Expenses";

        CreatePersonAccountDTO expected = new CreatePersonAccountDTO(email, accountDenomination, accountDescription);

        //Act
        CreatePersonAccountDTO real = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes(email,
                accountDenomination, accountDescription);

        // Assert
        assertEquals(expected, real);
    }


    @Test
    @DisplayName("Test create Person AccountDTO From PrimitiveTypes - Different email")
    void createPersonAccountDTOFromPrimitiveTypesDifferentEmail() {
        //Arrange
        String email = "raquel@xpto.com";
        String accountDenomination = "House Account";
        String accountDescription = "Expenses";

        CreatePersonAccountDTO expected = new CreatePersonAccountDTO(email, accountDenomination, accountDescription);
        CreatePersonAccountDTO unexpected = new CreatePersonAccountDTO("email@falso.pt", accountDenomination, accountDescription);

        //Act
        CreatePersonAccountDTO actual = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes(email,
                accountDenomination, accountDescription);

        // Assert
        Assertions.assertAll(
                () ->  assertEquals(expected, actual),
                () ->  assertNotEquals(unexpected, actual)
        );
    }
}