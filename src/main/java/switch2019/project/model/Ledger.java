package switch2019.project.model;

import java.util.HashSet;

public class Ledger {
    //Private Ledger variables
    private HashSet<Transaction> ledger;

    /**
     * Overload Ledger Construtor
     */

    public Ledger() {
        ledger = new HashSet<Transaction>();
    }

    /**
     * Add Transaction to Ledger
     */
    public boolean addTransactionToLedger(Transaction transaction) {
        if(transaction.isAValidTransaction()) return ledger.add(transaction);
        else return false;
    }

    /**
     * Get function for the ledger
     *
     * @return ledger
     */

    public HashSet<Transaction> getLedger() {
        return new HashSet<>(ledger);
    }
}