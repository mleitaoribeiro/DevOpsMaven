package switch2019.project.domain.domainEntities.ledger;

import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.LedgerID;
import switch2019.project.domain.domainEntities.shared.MonetaryValue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Ledger {

    //Private Ledger variables
    private LedgerID ledgerID;
    private final DateAndTime creationDate;
    private List<Transaction> ledgerTransactions;
    private ScheduledTasksList scheduledTasksList;

    //String literals should not be duplicated
    private static final String DATE_NOT_VALID = "One of the specified dates is not valid.";
    private static final String DATE_CANT_NULL = "The specified dates cannot be null.";

    /**
     * Ledger Constructor
     */

    public Ledger(OwnerID ownerID) {
        ledgerID = new LedgerID(ownerID);
        ledgerTransactions = new ArrayList<>();
        scheduledTasksList = new ScheduledTasksList();
        creationDate = new DateAndTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ledger)) return false;
        Ledger ledger = (Ledger) o;
        return Objects.equals(ledgerID, ledger.ledgerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ledgerID);
    }

    @Override
    public String toString() {
        return "Ledger:" + ledgerTransactions + ".";
    }

    /**
     * Get LedgerID
     *
     * @return ledger ID
     */

    public LedgerID getID() {
        return ledgerID;
    }

    /**
     * Get All Ledger transactions
     *
     * @return ledger Clone
     */

    public List<Transaction> getLedgerTransactions() {
        List<Transaction> newLedger = new ArrayList<>();
        newLedger.addAll(ledgerTransactions);
        return newLedger;
    }

    /**
     * Get the size of the legder
     * <p>
     * return int legderSize
     */
    public int getLedgerSize() {
        return this.ledgerTransactions.size();
    }

    /**
     * Get the creationDate of the Ledger as String
     */
    public String getCreationDateToString() {return this.creationDate.yearMonthDayToString(); }

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

    public boolean addTransactionToLedger(MonetaryValue amount, Description description, DateAndTime localDate, Category category, Account accountFrom, Account accountTo, Type type) {
        Transaction transaction = new Transaction(amount, description, localDate, category, accountFrom, accountTo, type);
        boolean transactionAdded = ledgerTransactions.add(transaction);
        sortLedgerByTransactionDateDescending();
        return transactionAdded;
    }

    /**
     * Develop method to create a new schedule (USER STORY)
     *
     * @param amount
     * @param description
     * @param date
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     * @return
     */

    public boolean scheduleNewTransaction(Periodicity periodicity, MonetaryValue amount, Description description, DateAndTime date,
                                          Category category, Account accountFrom, Account accountTo, Type type) {
        return scheduledTasksList.addNewSchedule(this, periodicity, amount, description, date,
                category, accountFrom, accountTo, type);
    }

    /**
     * Sort Ledger Transactions By Transaction Date in Ascending Order
     */

    public void sortLedgerByTransactionDateAscending() {
        Collections.reverse(ledgerTransactions);
    }

    /**
     * Sort Ledger Transactions By Transaction Date in Descending Order
     */

    public void sortLedgerByTransactionDateDescending() {
        ledgerTransactions.sort((transaction1, transaction2) -> transaction2.getDate().getYearMonthDayHourMinute()
                .compareTo(transaction1.getDate().getYearMonthDayHourMinute()));
    }

    /**
     * US011/US012: Get the transactions in a given specific date range
     *
     * @param initialDate
     * @param finalDate
     * @return ArrayList<Transaction> myTransactions
     */

    public List<Transaction> getTransactionsInDateRange(LocalDateTime initialDate, LocalDateTime finalDate) {

        if (initialDate == null || finalDate == null)
            throw new IllegalArgumentException(DATE_CANT_NULL);

        else if (initialDate.isAfter(LocalDateTime.now()) || finalDate.isAfter(LocalDateTime.now()))
            throw new IllegalArgumentException(DATE_NOT_VALID);

            //Validate if Date is in the correct order
        else if (initialDate.isAfter(finalDate)) {
            LocalDateTime aux = initialDate;
            initialDate = finalDate;
            finalDate = aux;
        }

        List<Transaction> myTransactions = new ArrayList<>();
        for (Transaction transactions : ledgerTransactions) {
            if ((transactions.getDate().isInTheFuture(initialDate) && transactions.getDate().isInThePast(finalDate)) ||
                    (transactions.getDate().getYearMonthDayHourMinute().equals(initialDate) ||
                            transactions.getDate().getYearMonthDayHourMinute().equals(finalDate)))
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
            throw new IllegalArgumentException("The account cannot be null.");
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
            throw new IllegalArgumentException(DATE_NOT_VALID);

        else if (initialDate.isAfter(LocalDateTime.now()) || finalDate.isAfter(LocalDateTime.now()))
            throw new IllegalArgumentException(DATE_NOT_VALID);

        else if (ledgerTransactions.isEmpty())
            throw new IllegalArgumentException("The ledger has no Transactions.");

            //Validate if Date is in the correct order
        else if (initialDate.isAfter(finalDate)) {
            LocalDateTime aux = initialDate;
            initialDate = finalDate;
            finalDate = aux;
        }
        //Check if transaction is in that range
        for (Transaction transactions : ledgerTransactions) {
            if (transactions.getDate().isInTheFuture(initialDate) && transactions.getDate().isInThePast(finalDate)) {
                if (transactions.getType()) {
                    balance = balance + transactions.getAmount();
                } else if (!transactions.getType()) {
                    balance = balance - transactions.getAmount();
                }
            }
        }
        return (double) Math.round(balance * 1000) / 1000; // balance rounded to three decimal places
    }
}
