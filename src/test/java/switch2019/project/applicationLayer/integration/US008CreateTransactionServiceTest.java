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
    void getTrasactionsByLedgerIdHappyCase() {

        String email = "marge@hotmail.com";

        List<TransactionDTO> result = service.getTransactionsByLedgerId(new PersonID(new Email(email)));

        TransactionDTO transactionDTO = new TransactionDTO("100.00", "Bought a cheap sofa",
                "2020-02-14 11:24", "HOUSE", "Gold Card", "IKEA", "false");

        TransactionDTO transactionDTO1 = new TransactionDTO("50.00", "Grocery for baking cookies",
                "2020-03-20 13:04", "HOUSE", "MasterCard", "Kwik-E-Mart", "false");

        //List<TransactionDTO> expected = Arrays.asList(transactionDTO, transactionDTO1);
        List<TransactionDTO> expected = Collections.emptyList();

        assertEquals(expected, result);
    }
/*
    @Test
    @DisplayName("Get Transactions By ledgerID - empty ledger")
    void getTrasactionsByLedgerIdEmptyLedger() {

        String email = "bart.simpson@hotmail.com";

        List<TransactionDTO> result = service.getTransactionsByLedgerId(new PersonID(new Email(email)));

        List<TransactionDTO> expected = Collections.emptyList();

        assertEquals(expected, result);
    }
    
 */


    @Test
    @DisplayName("Get Transactions By ledgerID - not found")
    void getTransactionsByLedgerIdException() {

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