package switch2019.project.applicationLayer.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.serializationDTO.TransactionDTO;
import switch2019.project.applicationLayer.US008CreateTransactionService;
import switch2019.project.domain.domainEntities.person.Email;
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

        TransactionDTO transactionDTO = new TransactionDTO("100.0 EUR", "Bought a cheap sofa".toUpperCase(),
                "2020-02-14 11:24", "HOUSE, marge@hotmail.com", "GOLD CARD, marge@hotmail.com",
                "IKEA, marge@hotmail.com", "DEBIT");

        TransactionDTO transactionDTO1 = new TransactionDTO("50.0 EUR", "Grocery for baking cookies".toUpperCase(),
                "2020-03-20 13:04", "HOUSE, marge@hotmail.com", "MASTERCARD, marge@hotmail.com",
                "KWIK E MART, marge@hotmail.com", "DEBIT");

        List<TransactionDTO> expected = Arrays.asList(transactionDTO, transactionDTO1);

        //Act
        List<TransactionDTO> result = service.getTransactionsByLedgerId(new PersonID(new Email(email)));

        //Assert
        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Get Transactions By ledgerID - empty ledger")
    void getTransactionsByLedgerIdEmptyLedger() {

        //Arrange
        String email = "bart.simpson@gmail.com";

        List<TransactionDTO> expected = Collections.emptyList();

        //Act
        List<TransactionDTO> result = service.getTransactionsByLedgerId(new PersonID(new Email(email)));

        //Assert
        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Get Transactions By ledgerID - not found")
    void getTransactionsByLedgerIdException() {

        //Arrange
        String email = "pikachu@hotmail.com";

        // Act
        Throwable thrown = catchThrowable(() -> {
            service.getTransactionsByLedgerId(new PersonID(new Email(email)));

        });

        // Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No Ledger found with that ID.");
    }
}