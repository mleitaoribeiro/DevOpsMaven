package switch2019.project.utils;

import switch2019.project.model.Account;
import switch2019.project.model.Category;
import switch2019.project.model.Ledger;
import switch2019.project.model.MonetaryValue;

import java.util.Currency;

public class Util_PersonalLedger {

    private Ledger personalLedger;

    /**
     * Personal Ledger for test purposes
     * @return
     */
    public Ledger createPersonalLedger() {

        //Dependencies:
        Ledger PersonaLedger = new Ledger();

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
        PersonaLedger.addTransactionToLedger(monetaryValue1, "payment", null, category1, account1, account5, type1);
        PersonaLedger.addTransactionToLedger(monetaryValue2, "payment", null, category2, account2, account4, type2);
        PersonaLedger.addTransactionToLedger(monetaryValue3, "payment", null, category1, account3, account1, type1);
        PersonaLedger.addTransactionToLedger(monetaryValue4, "payment", null, category2, account3, account4, type2);
        PersonaLedger.addTransactionToLedger(monetaryValue3, "payment", null, category1, account1, account3, type1);
        PersonaLedger.addTransactionToLedger(monetaryValue2, "payment", null, category2, account2, account5, type2);
        PersonaLedger.addTransactionToLedger(monetaryValue1, "payment", null, category1, account3, account1, type1);
        PersonaLedger.addTransactionToLedger(monetaryValue5, "payment", null, category2, account1, account3, type1);
        PersonaLedger.addTransactionToLedger(monetaryValue6, "payment", null, category2, account2, account4, type2);
        PersonaLedger.addTransactionToLedger(monetaryValue7, "payment", null, category1, account3, account1, type1);
        PersonaLedger.addTransactionToLedger(monetaryValue8, "payment", null, category1, account4, account3, type2);
        PersonaLedger.addTransactionToLedger(monetaryValue4, "payment", null, category1, account1, account3, type1);
        PersonaLedger.addTransactionToLedger(monetaryValue6, "payment", null, category2, account2, account2, type1);
        PersonaLedger.addTransactionToLedger(monetaryValue7, "payment", null, category2, account5, account1, type1);

        return PersonaLedger;
    }

    /**
     * Personal Ledger Constructor:
     */
    public Util_PersonalLedger() {
        personalLedger = createPersonalLedger();
    }

    /**
     * Getter to allow the Ledger to be used outside the class
     * @return personalLedger
     */
    public Ledger getLedger(){
        return this.personalLedger;
    }
}
