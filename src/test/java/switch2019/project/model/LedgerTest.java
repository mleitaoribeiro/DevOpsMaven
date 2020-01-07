package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LedgerTest {
    /**
     *Validate if a transaction was added to ledger list
     */

    @Test
    @DisplayName("Test for validating add a new transaction")
    void addTransactionToLedger() {
        //Arrange
        Transaction transaction= new Transaction(200,"payment","grossery",new Account("account1"),new Account("account2"),true);

    }
}