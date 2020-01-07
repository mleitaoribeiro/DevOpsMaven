package switch2019.project.model;

import java.util.HashSet;

public class Ledger {
    //Private Ledger variables
    private HashSet<Transaction> ledgerList;

    /**
     * Overload Ledger Construtor
     */

    public Ledger() {
        ledgerList = new HashSet<Transaction>();
    }

    /**
     * Add Transaction to Ledger
     */
    public void addTransactionToLedger(Transaction transaction) {
        ledgerList.add(transaction);

    }

    /**
     * Get function for the ledger List
     *
     * @return ledgerlist
     */

    public HashSet<Transaction> getLedgerList() {
        return new HashSet<>(ledgerList);
    }
}