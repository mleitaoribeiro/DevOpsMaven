package switch2019.project.assemblers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.AccountDTO;
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

        AccountDTO expected = new AccountDTO(email, denominationString.toUpperCase(), descriptionString.toUpperCase());
        AccountDTO unexpected = new AccountDTO("email@falso.pt", denominationString.toUpperCase(), descriptionString.toUpperCase());

        //Act
        AccountDTO actual = AccountDTOAssembler.createAccountDTO(personID, denomination, description);

        // Assert
        Assertions.assertAll(
                () ->  assertEquals(expected, actual),
                () ->  assertNotEquals(unexpected, actual)
        );
    }
}