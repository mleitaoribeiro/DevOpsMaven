package switch2019.project.utils;

import switch2019.project.model.Account;
import switch2019.project.model.Category;
import switch2019.project.model.Ledger;
import switch2019.project.model.MonetaryValue;

import java.time.LocalDateTime;
import java.util.Currency;

public class Util_PersonalLedger {

    private Ledger personalLedger;

    /**
     * Personal Ledger Constructor:
     */

    public Util_PersonalLedger() {

        //Categories:
        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");

        //Accounts:
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Account account3 = new Account("cinema", "filmes ás sextas-feiras");
        Account account4 = new Account("combustivel", "gastos de combustivél");
        Account account5 = new Account("comida de gato", "comida para a gatinha");

        //Type:
        boolean type1 = true;
        boolean type2 = false;

        //Monetary Value:
        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue3 = new MonetaryValue(50, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue4 = new MonetaryValue(150, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue5 = new MonetaryValue(30, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue6 = new MonetaryValue(25, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue7 = new MonetaryValue(75, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue8 = new MonetaryValue(175, Currency.getInstance("EUR"));


        //Transactions
        personalLedger.addTransactionToLedger(monetaryValue1, "payment", LocalDateTime.of(2020, 1, 14, 13, 05), category1, account1, account5, type1);
        personalLedger.addTransactionToLedger(monetaryValue2, "payment", LocalDateTime.of(2020, 1, 15, 10, 07), category2, account2, account4, type2);
        personalLedger.addTransactionToLedger(monetaryValue3, "payment", LocalDateTime.of(2020, 1, 3, 14, 10), category1, account3, account1, type1);
        personalLedger.addTransactionToLedger(monetaryValue4, "payment", LocalDateTime.of(2020, 1, 5, 3, 15), category2, account3, account4, type2);
        personalLedger.addTransactionToLedger(monetaryValue3, "payment", LocalDateTime.of(2019, 12, 31, 21, 01), category1, account1, account3, type1);
        personalLedger.addTransactionToLedger(monetaryValue2, "payment", LocalDateTime.of(2019, 12, 25, 12, 15), category2, account2, account5, type2);
        personalLedger.addTransactionToLedger(monetaryValue1, "payment", LocalDateTime.of(2019, 11, 15, 15, 04), category1, account3, account1, type1);
        personalLedger.addTransactionToLedger(monetaryValue5, "payment", LocalDateTime.of(2020, 1, 1, 12, 05), category2, account1, account3, type1);
        personalLedger.addTransactionToLedger(monetaryValue6, "payment", LocalDateTime.of(2020, 1, 4, 12, 54), category2, account2, account4, type2);
        personalLedger.addTransactionToLedger(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 5, 11, 29), category1, account3, account1, type1);
        personalLedger.addTransactionToLedger(monetaryValue8, "payment", LocalDateTime.of(2020, 1, 5, 13, 33), category1, account4, account3, type2);
        personalLedger.addTransactionToLedger(monetaryValue4, "payment", LocalDateTime.of(2020, 1, 15, 12, 54), category1, account1, account3, type1);
        personalLedger.addTransactionToLedger(monetaryValue6, "payment", LocalDateTime.of(2020, 1, 16, 15, 53), category2, account2, account2, type1);
        personalLedger.addTransactionToLedger(monetaryValue7, "payment", LocalDateTime.of(2020, 1, 20, 17, 22), category2, account5, account1, type1);

    }

    /**
     * Getter to allow the Ledger to be used outside the class
     *
     * @return personalLedger
     */
    public Ledger getLedger() {
        return personalLedger;
    }
}
