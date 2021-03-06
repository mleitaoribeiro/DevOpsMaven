package switch2019.project.domain.domainEntities.account;

import switch2019.project.domain.domainEntities.frameworks.Entity;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.shared.AccountID;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.MonetaryValue;

import java.util.Currency;
import java.util.Objects;

public class Account implements Entity {

    // Private Instance Variables

    private AccountID accountID;
    private Description description;
    private MonetaryValue balance;

    public Account(Denomination accountDenomination, Description accountDescription, OwnerID ownerID) {
        if (accountDescription == null) throw new IllegalArgumentException("Account description can't be null.");
        else {
            accountID = new AccountID(accountDenomination, ownerID);
            description = accountDescription;
            balance = new MonetaryValue(0.0, Currency.getInstance("EUR"));
        }
    }

    public Account(Denomination accountDenomination, Description accountDescription, OwnerID ownerID, MonetaryValue monetaryValue) {
        if (accountDescription == null) throw new IllegalArgumentException("Account description can't be null.");
        else {
            accountID = new AccountID(accountDenomination, ownerID);
            description = accountDescription;
            balance = monetaryValue;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account oneAccount = (Account) o;
        return Objects.equals(accountID, oneAccount.accountID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountID);
    }

    @Override
    public String toString() {
        return description.toString() + ", " + balance.toString() + "€" + ", " + accountID.toString();
    }

    public AccountID getID() {
        return accountID;
    }

    public OwnerID getOwnerID() {
        return this.accountID.getOwnerID();
    }

    public MonetaryValue getBalance() {
        return balance;
    }

    public String denominationToString() {
        return this.getID().getDenominationToString();
    }

    public String descriptionToString() {
        return description.toString();
    }

}
