package switch2019.project.domain.domainEntities.ledger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.shared.*;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScheduleTest {

    @Test
    @DisplayName("Schedule a transaction - daily")
    void scheduleTransactionDaily() throws InterruptedException {

        //Arrange
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        Description description = new Description("Food");
        CategoryID category = new CategoryID(new Denomination("General"), ownerID);
        AccountID from = new AccountID(new Denomination("Wallet"), ownerID);
        AccountID to = new AccountID(new Denomination("TransportAccount"), ownerID);
        Type type = new Type(false); //debit

        //Act
        new Schedule(ledger, new Periodicity("daily"),
                amount, description, null, category, from, to, type);

        Thread.sleep(2400); // daily = 500

        //Assert
        assertEquals(5, ledger.getLedgerSize());
    }

    @Test
    @DisplayName("Schedule a transaction - working days")
    void scheduleTransactionWorkingDays() throws InterruptedException {

        //Arrange
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        Description description = new Description("Food");
        CategoryID category = new CategoryID(new Denomination("General"), ownerID);
        AccountID from = new AccountID(new Denomination("Wallet"), ownerID);
        AccountID to = new AccountID(new Denomination("TransportAccount"), ownerID);
        Type type = new Type(false); //debit

        //Act
        new Schedule(ledger, new Periodicity("working days"),
                amount, description, null, category, from, to, type);

        Thread.sleep(3900); // working days = 1000

        //Assert
        assertEquals(4, ledger.getLedgerSize());
    }

    @Test
    @DisplayName("Schedule a transaction - weekly")
    void scheduleTransactionWeekly() throws InterruptedException {

        //Arrange
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        Description description = new Description("Food");
        CategoryID category = new CategoryID(new Denomination("General"), ownerID);
        AccountID from = new AccountID(new Denomination("Wallet"), ownerID);
        AccountID to = new AccountID(new Denomination("TransportAccount"), ownerID);
        Type type = new Type(false); //debit

        //Act
        new Schedule(ledger, new Periodicity("weekly"),
                amount, description, null, category, from, to, type);

        Thread.sleep(4000); // weekly = 1500

        //Assert
        assertEquals(3, ledger.getLedgerSize());
    }

    @Test
    @DisplayName("Schedule a transaction - monthly")
    void scheduleTransactionMonthly() throws InterruptedException {

        //Arrange
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledger = new Ledger(ownerID);

        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        Description description = new Description("Food");
        CategoryID category = new CategoryID(new Denomination("General"), ownerID);
        AccountID from = new AccountID(new Denomination("Wallet"), ownerID);
        AccountID to = new AccountID(new Denomination("TransportAccount"), ownerID);
        Type type = new Type(false); //debit

        //Act
        new Schedule(ledger, new Periodicity("monthly"),
                amount, description, null, category, from, to, type);

        Thread.sleep(3000); // monthly = 2000

        //Assert
        assertEquals(2, ledger.getLedgerSize());
    }
}