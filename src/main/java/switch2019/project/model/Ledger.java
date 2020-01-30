package switch2019.project.model;

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
     * To String
     *
     * @return
     */

    @Override
    public String toString() {
        return "Ledger:" + ledgerTransactions + ".";
    }

    /**
     * Get All Ledger transactions
     *
     * @return ledger Clone
     */

    public List<Transaction> getLedgerTransactions() {
        List<Transaction> newLedger = new ArrayList<Transaction>();
        newLedger.addAll(ledgerTransactions);
        return newLedger;
}

    /**
     * Method that checks if a transaction is contained within a Ledger
     *
     * @param transactionInLedger
     * @return
     */

    public boolean isTransactionInLedger(Transaction transactionInLedger) {
        return ledgerTransactions.contains(transactionInLedger);
    }

    /**
     * Method to Add Transactions to Ledger
     *
     * @param amount
     * @param description
     * @param localDate
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     * @return
     */

    public boolean addTransactionToLedger(MonetaryValue amount, String description, LocalDateTime localDate, Category category, Account accountFrom, Account accountTo, boolean type) {
        Transaction transaction = new Transaction(amount, description, localDate, category, accountFrom, accountTo, type);
        boolean transactionAdded = ledgerTransactions.add(transaction);
        sortLedgerByTransactionDateDescending();
        return transactionAdded;
    }

    /**
     * Sort Ledger Transactions By Transaction Date in Ascending Order
     */

    public void sortLedgerByTransactionDateAscending() {
        List<Transaction> newLedger = ledgerTransactions;
        newLedger.sort(Comparator.comparing(Transaction::getDate));
    }

    /**
     * Sort Ledger Transactions By Transaction Date in Descending Order
     */

    public void sortLedgerByTransactionDateDescending() {
        List<Transaction> newLedger = ledgerTransactions;
        newLedger.sort((transaction1, transaction2) -> transaction2.getDate().compareTo(transaction1.getDate()));
    }

    /**
     * US011/US012: Get the transactions in a given specific date range
     *
     * @param initialDate
     * @param finalDate
     * @return ArrayList<Transaction> myTransactions
     */

    public ArrayList<Transaction> getTransactionsInDateRange(LocalDateTime initialDate, LocalDateTime finalDate) {

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
                    (transactions.getDate().equals(initialDate) || transactions.getDate().equals(finalDate)))
                myTransactions.add(transactions);
        }

        return myTransactions;
    }


    /**
     * Method to get the movements from a specific account
     *
     * @param account1
     * @param listOfTransactions
     */
    public List<Transaction> getTransactionsFromOneAccount(Account account1, List<Transaction> listOfTransactions) {
        List<Transaction> listOfTransactionsFromOneAccount = new ArrayList<>();
        if (account1 != null) {
            for (Transaction transaction : listOfTransactions) {
                if (transaction.getAccountFrom().equals(account1) || transaction.getAccountTo().equals(account1)) {
                    listOfTransactionsFromOneAccount.add(transaction);
                }
            }
            return listOfTransactionsFromOneAccount;
        } else
            throw new IllegalArgumentException("The account can't be null");
    }


    /**
     * US017/18 - Get the balance of the transactions given a specific date range
     *
     * @param initialDate
     * @param finalDate
     */

    public double getBalanceInDateRange(LocalDateTime initialDate, LocalDateTime finalDate) {
        double balance = 0;
        if (initialDate == null || finalDate == null)
            throw new IllegalArgumentException("One of the submitted dates is not valid.");

        else if (initialDate.isAfter(LocalDateTime.now()) || finalDate.isAfter(LocalDateTime.now()))
            throw new IllegalArgumentException("One of the submitted dates is not valid.");

        else if (ledgerTransactions.isEmpty())
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
        return (double) Math.round(balance * 1000) / 1000; // balance rounded to three decimal places
    }

    /**
     * Get the size of the legder
     * <p>
     * return int legderSize
     */
    public int getLedgerSize() {
        return this.ledgerTransactions.size();
    }
}