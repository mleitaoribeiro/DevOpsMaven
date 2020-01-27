package switch2019.project.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Ledger {
    //Private Ledger variables
    private Set<Transaction> ledgerTransactions;

    /**
     * Ledger Constructor
     */

    public Ledger() {
        ledgerTransactions = new HashSet<>();
    }

    /**
     * toString
     */


    @Override
    public String toString() {
        return "Ledger:" + ledgerTransactions +
                ".";
    }

    /**
     * Add Transaction to Ledger
     */
    public boolean addTransactionToLedger(MonetaryValue amount, String description, LocalDateTime localDate, Category category, Account accountFrom, Account accountTo, boolean type) {
        Transaction transaction = new Transaction(amount, description, localDate, category, accountFrom, accountTo, type);
        return ledgerTransactions.add(transaction);
    }

    /**
     * US11: Get the balance of the transactions given a specific date range
     * @param initialDate
     * @param finalDate
     */

    public int getPersonalBalanceInDateRange(LocalDate initialDate, LocalDate finalDate) {
        return 0;
    }
    /**
     * US011: Get the balance of the transactions given a specific date range
     * @param initialDate
     * @param finalDate
     */

    public HashSet<Transaction> getTransactionsFromPeriod ( LocalDateTime initialDate, LocalDateTime finalDate) {
        //Validate if Date is in the correct order
        if(initialDate.isAfter(finalDate)){
            LocalDateTime aux = initialDate;
            initialDate = finalDate;
            finalDate = aux;
        }

        HashSet<Transaction> myTransactions = new HashSet<Transaction>();
        for(Transaction transactions : ledgerTransactions) {
            if (transactions.getDate().isAfter(initialDate) && transactions.getDate().isBefore(finalDate))
                myTransactions.add(transactions);
        }
        return myTransactions;
    }


    /**
     * US012 - Como utilizador membro de grupo, quero obter os movimentos do grupo  num dado período.
     *
     * @param initialDate
     * @param finalDate
     */

    public HashSet<Transaction> getLedgerTransactionsInPeriod ( LocalDateTime initialDate, LocalDateTime finalDate) {
        return new HashSet<>();
    }

}
