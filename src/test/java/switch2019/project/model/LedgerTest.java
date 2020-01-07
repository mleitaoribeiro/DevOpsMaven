package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;


class LedgerTest {
    /**
     *Validate if a transaction was added to ledger list
     */

    @Test
    @DisplayName("Test for validating add a new transaction")
    void addTransactionToLedger() {
        //Arrange
        Account account1=new Account("mercearia", "mercearia Continente");
        Account account2= new Account("transporte","transporte Metro");
        Category category1= new Category("grossery");
        Type type1= new Type(true);
        MonetaryValue monetaryValue1= new MonetaryValue(200, Currency.getInstance("EUR"));
        Transaction transaction= new Transaction(monetaryValue1,"payment",category1,account1, account2,type1);
       Ledger ledger1= new Ledger();

         //Act
        ledger1.addTransactionToLedger(transaction);

        //Assert
        assertEquals(1, ledger1.getLedgerList().size());
    }
    @Test
    @DisplayName("Test for validating for several new transactions")
    void addTransactionToLedger_several() {
        //Arrange
        Account account1=new Account("mercearia", "mercearia Continente");
        Account account2= new Account("transporte","transporte Metro");
        Category category1= new Category("grossery");
        Category category2= new Category("transport");
        Type type1= new Type(true);
        MonetaryValue monetaryValue1= new MonetaryValue(200, Currency.getInstance("EUR"));
        Transaction transaction1= new Transaction(monetaryValue1,"payment",category1,account1, account2,type1);
        Transaction transaction2= new Transaction(monetaryValue1,"payment",category2,account1, account2,type1);
        Ledger ledger1= new Ledger();

        //Act
        ledger1.addTransactionToLedger(transaction1);
        ledger1.addTransactionToLedger(transaction2);


        //Assert
        assertEquals(2, ledger1.getLedgerList().size());
    }
}