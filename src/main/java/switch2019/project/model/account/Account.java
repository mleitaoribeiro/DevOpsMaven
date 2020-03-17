package switch2019.project.model.account;

import switch2019.project.model.frameworks.Entity;
import switch2019.project.model.frameworks.ID;
import switch2019.project.model.frameworks.OwnerID;
import switch2019.project.model.shared.AccountID;
import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.MonetaryValue;

import java.util.Currency;
import java.util.Objects;

public class Account implements Entity {

    // Private Instance Variables

    private AccountID accountID;
    private Denomination denomination;
    private Description description;
    private MonetaryValue balance;

    /**
     * Constructor of Account (to delete later)
     *
     * @param accountDenomination
     * @param accountDescription
     */

    public Account(Denomination accountDenomination, Description accountDescription) {
        this.denomination = accountDenomination;
        this.description = accountDescription;
        this.balance = new MonetaryValue(0.0,Currency.getInstance("EUR"));
    }

    /**
     * Constructor of Account
     *
     * @param accountDenomination
     * @param accountDescription
     * @param ownerID
     */

    public Account(Denomination accountDenomination, Description accountDescription, OwnerID ownerID) {
        accountID = new AccountID(accountDenomination, ownerID);
        this.balance = new MonetaryValue(0.0,Currency.getInstance("EUR"));
        if(accountDescription != null) this.description = accountDescription;
        else throw new IllegalArgumentException("Account Description can't be null.");
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
        return  description.toString() + ", " + balance.toString() + "€" + ", " + accountID.toString();
    }

    /**
     * Get account by ID
     */
    public ID getID() {
        return accountID;
    }

    /**
     * Get account by Owner ID
     */

    /**
     * Get accountID
     */
    public AccountID getAccountID() {
        return accountID;
    }

    /**
     * Get account by Owner ID
     */

    public OwnerID getOwnerID() { return this.accountID.getOwnerID(); }

    /**
     * Public get for denomination
     *
     * @return denomination
     */

    public String denominationToString() {
        return denomination.toString();
    }

    /**
     * Public get for description
     *
     * @return descritpion
     */
    public String getDescription() {
        return description.getDescription();
    }


    /**
     * Public get to access a clone of Account
     *
     * @return copy of Account
     */

    public Account getCopyOfAccount() {
        return new Account(new Denomination(accountID.getDenomination()), description, accountID.getOwnerID());
    }
}
