package switch2019.project.applicationLayer.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.serializationDTO.TransactionDTO;
import switch2019.project.DTO.serializationDTO.TransactionShortDTO;
import switch2019.project.applicationLayer.US008CreateTransactionService;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


@SpringBootTest
class US008CreateTransactionServiceTest {

    @Autowired
    private US008CreateTransactionService service;


    /**
     * Test to get Transactions by LedgerId
     */

    @Test
    @DisplayName("Get Transactions By ledgerID - happy case")
    void getTransactionsByLedgerIdHappyCase() {

        //Arrange
        String email = "marge@hotmail.com";

        TransactionShortDTO transactionDTO = new TransactionShortDTO("100.0 EUR",
                "GOLD CARD, marge@hotmail.com", "IKEA, marge@hotmail.com", "DEBIT");

        TransactionShortDTO transactionDTO1 = new TransactionShortDTO("50.0 EUR",
                "MASTERCARD, marge@hotmail.com", "KWIK E MART, marge@hotmail.com", "DEBIT");

        List<TransactionShortDTO> expected = Arrays.asList(transactionDTO, transactionDTO1);

        //Act
        List<TransactionShortDTO> result = service.getTransactionsByLedgerId(email);

        //Assert
        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Get Transactions By ledgerID - empty ledger")
    void getTransactionsByLedgerIdEmptyLedger() {

        //Arrange
        String email = "bart.simpson@gmail.com";

        List<TransactionShortDTO> expected = Collections.emptyList();

        //Act
        List<TransactionShortDTO> result = service.getTransactionsByLedgerId(email);

        //Assert
        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Get Transactions By ledgerID - not found")
    void getTransactionsByLedgerIdException() {

        //Arrange
        String email = "pikachu@hotmail.com";

        PersonID personID = new PersonID(new Email(email));

        // Act
        Throwable thrown = catchThrowable(() -> {
            service.getTransactionsByLedgerId(personID.toString());

        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No Ledger found with that ID.");
    }

    @Test
    @DisplayName("Get Transactions By ledgerID - empty ledger")
    void getGroupTransactionsByLedgerEmptyLedger() {

        //Arrange
        String groupDescription = "Switch";

        GroupID groupID = new GroupID(new Description(groupDescription));

        List<TransactionShortDTO> expected = Collections.emptyList();

        List<TransactionShortDTO> result = service.getTransactionsByLedgerId(groupID.toString());

        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Get Transactions By ledgerID - not found")
    void getGroupTransactionsByLedgerIdException() {

        //Arrange
        String groupDescription = "Rick & Rock";

        GroupID groupID = new GroupID(new Description(groupDescription));

        // Act
        Throwable thrown = catchThrowable(() -> {
            service.getTransactionsByLedgerId(groupID.toString());

        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No Ledger found with that ID.");
    }


}
