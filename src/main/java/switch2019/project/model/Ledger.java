package switch2019.project.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Ledger {
    //Private Ledger variables
    private List<Transaction> ledgerTransactions;

    /**
     * Ledger Constructor
     */

    public Ledger() {
        ledgerTransactions = new ArrayList<>();
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
     * US011/US012: Get the transactions in a given specific date range
     * @param initialDate
     * @param finalDate
     */

    public ArrayList<Transaction> getTransactionsFromPeriod ( LocalDateTime initialDate, LocalDateTime finalDate) {

        if (initialDate == null || finalDate == null)
            throw new IllegalArgumentException("The dates can´t be null");

        if (initialDate.isAfter(LocalDateTime.now()) || finalDate.isAfter(LocalDateTime.now()))
            throw new IllegalArgumentException("One of the submitted dates is not valid");

        //Validate if Date is in the correct order
        if(initialDate.isAfter(finalDate)){
            LocalDateTime aux = initialDate;
            initialDate = finalDate;
            finalDate = aux;
        }

        ArrayList<Transaction> myTransactions = new ArrayList<>();
        for(Transaction transactions : ledgerTransactions) {
            if ((transactions.getDate().isAfter(initialDate) && transactions.getDate().isBefore(finalDate)) || (transactions.getDate().equals(initialDate) && transactions.getDate().equals(finalDate)))
                myTransactions.add(transactions);
        }
        return myTransactions;
    }


    /**
     * Method that checks if a transaction is contained within a Ledger
     */

    public boolean isTransactionInLedger(Transaction transactionInLedger){
        return this.ledgerTransactions.contains(transactionInLedger);
    }

}
