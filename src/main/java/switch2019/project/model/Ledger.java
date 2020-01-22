package switch2019.project.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

public class Ledger {
    //Private Ledger variables
    private HashSet<Transaction> ledgerTransactions;

    /**
     * Ledger Constructor
     */

    public Ledger() {
        ledgerTransactions = new HashSet<>();
    }

    /**
     * Add Transaction to Ledger
     */
    public boolean addTransactionToLedger(MonetaryValue amount, String description, LocalDateTime localDate, Category category, Account accountFrom, Account accountTo, boolean type) {
        Transaction transaction = new Transaction(amount, description, localDate, category, accountFrom, accountTo, type);
        return ledgerTransactions.add(transaction);
    }

}
