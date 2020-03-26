package switch2019.project.assemblers;

import org.graalvm.compiler.asm.Assembler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.AccountDTO;
import switch2019.project.DTO.CreateGroupAccountDTO;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;

import static org.junit.jupiter.api.Assertions.*;

class AccountDTOAssemblerTest {

    @Test
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

    @Test
    void createCreateGroupAccountDTOFromPrimitiveTrypes() {
        //Arrange
        String email = "email@email.pt";
        String denominationString = "Conta do Alex";
        String descriptionString = "Conta poupança";
        String groupDescription = "Grupo";


        CreateGroupAccountDTO expected = new CreateGroupAccountDTO(email, groupDescription, denominationString, descriptionString);
        CreateGroupAccountDTO unexpected = new CreateGroupAccountDTO("email@falso.pt", groupDescription, denominationString, descriptionString);
        //Act
        CreateGroupAccountDTO actual = AccountDTOAssembler.createGroupAccountDTOFromPrimitiveTrypes(email, groupDescription,
                denominationString, descriptionString);

        // Assert
        Assertions.assertAll(
                () ->  assertEquals(expected, actual),
                () ->  assertNotEquals(unexpected, actual)
        );
    }
}