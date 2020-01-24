package switch2019.project.utils;

import switch2019.project.model.Account;
import switch2019.project.model.Category;
import switch2019.project.model.Ledger;
import switch2019.project.model.MonetaryValue;

import java.util.Currency;

public class Util_PersonalLedger {

    private Ledger personalLedger;

    public Ledger createPersonalLedger() {
        Ledger PersonaLedger = new Ledger();
        Account account1 = new Account("mercearia", "mercearia Continente");
        Account account2 = new Account("transporte", "transporte Metro");
        Category category1 = new Category("grocery");
        Category category2 = new Category("friends");
        Account account3 = new Account("cinema", "filmes ás sextas-feiras");
        Account account4 = new Account("combustivel", "gastos de combustivél");
        boolean type1 = true;
        boolean type2 = false;
        MonetaryValue monetaryValue1 = new MonetaryValue(200, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue2 = new MonetaryValue(100, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue3 = new MonetaryValue(50, Currency.getInstance("EUR"));
        MonetaryValue monetaryValue4 = new MonetaryValue(150, Currency.getInstance("EUR"));

        //Transactions
        PersonaLedger.addTransactionToLedger(monetaryValue1, "payment", null, category1, account1, account3, type1);
        PersonaLedger.addTransactionToLedger(monetaryValue2, "payment", null, category2, account2, account2, type2);
        PersonaLedger.addTransactionToLedger(monetaryValue3, "payment", null, category1, account3, account1, type1);
        PersonaLedger.addTransactionToLedger(monetaryValue4, "payment", null, category2, account4, account4, type2);
        PersonaLedger.addTransactionToLedger(monetaryValue3, "payment", null, category1, account1, account3, type1);
        PersonaLedger.addTransactionToLedger(monetaryValue2, "payment", null, category2, account2, account2, type2);
        PersonaLedger.addTransactionToLedger(monetaryValue1, "payment", null, category1, account3, account1, type1);
        return PersonaLedger;
    }

    public Util_PersonalLedger() {
        personalLedger = createPersonalLedger();
    }

    public Ledger getLedger(){
        return this.personalLedger;
    }
}
