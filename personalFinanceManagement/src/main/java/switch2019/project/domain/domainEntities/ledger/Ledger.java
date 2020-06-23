package switch2019.project.domain.domainEntities.ledger;

import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.utils.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Ledger {

    //Private Ledger variables
    private final LedgerID ledgerID;
    private final DateAndTime creationDate;
    private final List<Transaction> ledgerTransactions;
    private final ScheduledTasksList scheduledTasksList;

    //String literals should not be duplicated
    private static final String DATE_NOT_VALID = "One of the specified dates is not valid.";
    private static final String DATE_CANT_NULL = "The specified dates cannot be null.";

    public Ledger(OwnerID ownerID) {
        ledgerID = new LedgerID(ownerID);
        ledgerTransactions = new ArrayList<>();
        scheduledTasksList = new ScheduledTasksList();
        creationDate = new DateAndTime();
    }

    public Ledger(OwnerID ownerID, ArrayList<Transaction> transactions, DateAndTime creationDate) {
        ledgerID = new LedgerID(ownerID);
        ledgerTransactions = new ArrayList<>(transactions);
        scheduledTasksList = new ScheduledTasksList(); // change later
        this.creationDate = creationDate;
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

    public LedgerID getID() {
        return ledgerID;
    }

    public List<Transaction> getLedgerTransactions() {
        List<Transaction> newLedger = new ArrayList<>();
        newLedger.addAll(ledgerTransactions);
        return newLedger;
    }

    public int getLedgerSize() {
        return this.ledgerTransactions.size();
    }

    public String getCreationDateToString() {return this.creationDate.yearMonthDayToString(); }

    public boolean isTransactionInLedger(Transaction transactionInLedger) {
        return ledgerTransactions.contains(transactionInLedger);
    }

    public Transaction addTransactionToLedger(MonetaryValue amount, Description description, DateAndTime localDate,
                                              CategoryID category, AccountID accountFrom, AccountID accountTo, Type type) {
        Transaction transaction = new Transaction(amount, description, localDate, category, accountFrom, accountTo, type);
        ledgerTransactions.add(transaction);
        sortLedgerByTransactionDateDescending();
        return transaction;
    }

    public boolean scheduleNewTransaction(Periodicity periodicity, MonetaryValue amount, Description description, DateAndTime date,
                                          CategoryID category, AccountID accountFrom, AccountID accountTo, Type type) {
        return scheduledTasksList.addNewSchedule(this, periodicity, amount, description, date,
                category, accountFrom, accountTo, type);
    }

    public void sortLedgerByTransactionDateAscending() {
        Collections.reverse(ledgerTransactions);
    }

    public void sortLedgerByTransactionDateDescending() {
        ledgerTransactions.sort((transaction1, transaction2) -> transaction2.getDate().getYearMonthDayHourMinute()
                .compareTo(transaction1.getDate().getYearMonthDayHourMinute()));
    }

    public List<Transaction> getTransactionsInDateRange(LocalDateTime initialDate, LocalDateTime finalDate) {

        if (initialDate == null || finalDate == null)
            throw new IllegalArgumentException(DATE_CANT_NULL);

        else if (!StringUtils.isCorrectDateRange(
                initialDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                finalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))))
            throw new IllegalArgumentException(DATE_NOT_VALID);

        List<Transaction> myTransactions = new ArrayList<>();
        for (Transaction transactions : ledgerTransactions) {
            if ((transactions.getDate().isInTheFuture(initialDate) && transactions.getDate().isInThePast(finalDate)) ||
                    (transactions.getDate().getYearMonthDayHourMinute().equals(initialDate) ||
                            transactions.getDate().getYearMonthDayHourMinute().equals(finalDate)))
                myTransactions.add(transactions);
        }
        return myTransactions;
    }

    public List<Transaction> getTransactionsFromOneAccount(AccountID account1, List<Transaction> listOfTransactions) {
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
            //POR AQUI UMA EXCEÇÃO!!!!!!!
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
