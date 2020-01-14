package switch2019.project.model;

import java.util.HashSet;

public class Ledger {
    //Private Ledger variables
    private HashSet<Transaction> ledger;

    /**
     * Overload Ledger Construtor
     */

    public Ledger() {
        ledger = new HashSet<>();
    }

    /**
     * Add Transaction to Ledger
     */
    public boolean addTransactionToLedger(MonetaryValue amount, String description, Category category, Account accountFrom, Account accountTo, Type type) {
        Transaction transaction = new Transaction(amount, description, category, accountFrom, accountTo, type);
        if (transaction.isAValidTransaction()) return ledger.add(transaction);
        else return false;
    }
}
