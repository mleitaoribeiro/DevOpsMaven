package switch2019.project.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
     * US011/US012: Get the transactions in a given specific date range
     *
     * @param initialDate
     * @param finalDate
     */

    public ArrayList<Transaction> getTransactionsInDateRange(LocalDateTime initialDate, LocalDateTime finalDate) throws IllegalArgumentException  {

        sortLedgerByTransactionDate();

        if (initialDate == null || finalDate == null)
            throw new IllegalArgumentException("The dates can´t be null");

        else if (initialDate.isAfter(LocalDateTime.now()) || finalDate.isAfter(LocalDateTime.now()))
            throw new IllegalArgumentException("One of the submitted dates is not valid.");

        //Validate if Date is in the correct order
        else if (initialDate.isAfter(finalDate)) {
            LocalDateTime aux = initialDate;
            initialDate = finalDate;
            finalDate = aux;
        }

        ArrayList<Transaction> myTransactions = new ArrayList<>();
        for (Transaction transactions : ledgerTransactions) {
            if ((transactions.getDate().isAfter(initialDate) && transactions.getDate().isBefore(finalDate)) ||
                    (transactions.getDate().equals(initialDate) && transactions.getDate().equals(finalDate)))
                myTransactions.add(transactions);
        }

        return myTransactions;
    }

    /**
     *  Sort Ledger By Transaction Date
     */

    public void sortLedgerByTransactionDate () {
        List <Transaction> newLedger = ledgerTransactions;
        newLedger.sort(Comparator.comparing(Transaction::getDate));
    }

    /**
     *  Get All Ledger transactions
     */
    public List <Transaction> getLedgerTransactions () {
        List <Transaction> newLedger = ledgerTransactions;
        return newLedger;
    }

    /**
     * Method that checks if a transaction is contained within a Ledger
     */

    public boolean isTransactionInLedger(Transaction transactionInLedger) {
        return ledgerTransactions.contains(transactionInLedger);
    }

    /**
     * US017/18 - Get the balance of the transactions given a specific date range
     *
     * @param initialDate
     * @param finalDate
     */

    public double getBalanceInDateRange(LocalDateTime initialDate, LocalDateTime finalDate) throws IllegalArgumentException {
        double balance = 0;
        if (initialDate == null || finalDate == null)
            throw new IllegalArgumentException("One of the submitted dates is not valid.");

        else if (initialDate.isAfter(LocalDateTime.now()) || finalDate.isAfter(LocalDateTime.now()))
            throw new IllegalArgumentException("One of the submitted dates is not valid.");

        else if(ledgerTransactions.isEmpty())
            throw new IllegalArgumentException("The ledger is Empty.");

        //Validate if Date is in the correct order
        else if (initialDate.isAfter(finalDate)) {
            LocalDateTime aux = initialDate;
            initialDate = finalDate;
            finalDate = aux;
        }
        //Check if transaction is in that range
        for (Transaction transactions : ledgerTransactions) {
            if (transactions.getDate().isAfter(initialDate) && transactions.getDate().isBefore(finalDate)) {
                if (transactions.getType() == true) {
                    balance = balance + transactions.getAmount();
                } else if (transactions.getType() == false) {
                    balance = balance - transactions.getAmount();
                }
            }
        }
        return (double)Math.round(balance*1000)/1000; // balance rounded to three decimal places
    }
}